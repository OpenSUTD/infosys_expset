package menu.western.mains;

import events.IngredientUsedEvent;
import events.ItemSaleEvent;
import menu.MenuItem;
import simulator.EventBus;
import simulator.Ingredient;
import simulator.Inventory;

public class Burger implements MainDish {
    @Override
    public void make(Inventory inventory) {
        // check ingredients
        if (!canMake(inventory)) {
            throw new Inventory.NoMoreIngredientException();
        }
        // cook!
        inventory.takeOutIngredients(Ingredient.SLICED_BUN, 2);
        inventory.takeOutIngredients(Ingredient.TOMATO, 1);
        inventory.takeOutIngredients(Ingredient.CHICKEN_BREAST, 1);
        inventory.takeOutIngredients(Ingredient.LETTUCE, 1);
        inventory.takeOutIngredients(Ingredient.CHEESE, 1);
        // notify ingredient usage events
        EventBus.getInstance().notifyObservers(new IngredientUsedEvent(Ingredient.SLICED_BUN, 2));
        EventBus.getInstance().notifyObservers(new IngredientUsedEvent(Ingredient.TOMATO, 1));
        EventBus.getInstance().notifyObservers(new IngredientUsedEvent(Ingredient.CHICKEN_BREAST, 1));
        EventBus.getInstance().notifyObservers(new IngredientUsedEvent(Ingredient.LETTUCE, 1));
        EventBus.getInstance().notifyObservers(new IngredientUsedEvent(Ingredient.CHEESE, 1));
        // notify item sale event
        // assume ala carte price
        EventBus.getInstance().notifyObservers(new ItemSaleEvent(this, 5));
    }

    @Override
    public boolean canMake(Inventory inventory) {
        boolean has_bun = inventory.getIngredientCount(Ingredient.SLICED_BUN) >= 2;
        boolean has_tomato = inventory.getIngredientCount(Ingredient.TOMATO) >= 1;
        boolean has_chicken = inventory.getIngredientCount(Ingredient.CHICKEN_BREAST) >= 1;
        boolean has_lettuce = inventory.getIngredientCount(Ingredient.LETTUCE) >= 1;
        boolean has_cheese = inventory.getIngredientCount(Ingredient.CHEESE) >= 1;
        return has_bun && has_tomato && has_chicken && has_lettuce && has_cheese;
    }
}
