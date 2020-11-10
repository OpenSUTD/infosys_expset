package menu.western;

import events.ItemSaleEvent;
import menu.MenuItem;
import menu.western.mains.AglioOlio;
import menu.western.mains.Burger;
import menu.western.mains.ChickenCutlet;
import menu.western.mains.MainDish;
import menu.western.sides.SideDish;
import simulator.EventBus;
import simulator.Inventory;

import java.util.ArrayList;
import java.util.List;

/**
 * A set meal consists of one {@link MainDish} and one {@link SideDish}. Ordering a set meal will consume no extra ingredients, but adds a 1.50 discount.
 */
public class SetMeal implements MenuItem {

    private final MainDish main;
    private final List<SideDish> sides;

    public static class SetMealBuilder {
        private MainDish main;
        private final ArrayList<SideDish> sides = new ArrayList<>();

        /**
         * Sets the main to {@link AglioOlio}
         * @return itself (fluent interface)
         */
        public SetMealBuilder setMainAsAglioOlio() {
            main = new AglioOlio();
            return this;
        }

        /**
         * Sets the main to {@link Burger}
         * @return itself (fluent interface)
         */
        public SetMealBuilder setMainAsBurger() {
            main = new Burger();
            return this;
        }

        /**
         * Sets the main to {@link ChickenCutlet}
         * @return itself (fluent interface)
         */
        public SetMealBuilder setMainAsChickenCutlet() {
            main = new ChickenCutlet();
            return this;
        }

        /**
         * Adds sideDishes to the order. You need to keep track of the side dishes. Once added, cannot remove.
         * @param side the side dish to add
         * @return itself (fluent interface)
         */
        public SetMealBuilder addSide(SideDish side) {
            sides.add(side);
            return this;
        }

        /**
         * Builds the set meal.
         * @throws IllegalArgumentException if there are less than 2 side dishes added to the order
         * @return new set meal
         */
        public SetMeal build() {
            if (this.sides.size() < 2) {
                throw new IllegalArgumentException("Needs at least 2 sides!");
            }
            if (this.main == null){
                throw new IllegalArgumentException("No main dish defined!");
            }
            return new SetMeal(main, sides);
        }
    }

    /**
     * Protected constructor used by the builder
     * @param main the main dish
     * @param sides the side dishes (at least two please)
     */
    protected SetMeal(MainDish main, List<SideDish> sides) {
        this.main = main;
        this.sides = sides;
    }

    @Override
    public void make(Inventory inventory) {
        // check if can make all components
        boolean can_make_main = this.main.canMake(inventory);
        if (!can_make_main) {
            throw new Inventory.NoMoreIngredientException();
        }
        for (SideDish side : this.sides) {
            boolean can_make_side = side.canMake(inventory);
            if (!can_make_side) {
                throw new Inventory.NoMoreIngredientException();
            }
        }
        // make each component
        this.main.make(inventory);
        for (SideDish side: this.sides){
            side.make(inventory);
        }
        // slap discount by notifying item sale event with negative price
        EventBus.getInstance().notifyObservers(new ItemSaleEvent(this, -1.5));
    }
}
