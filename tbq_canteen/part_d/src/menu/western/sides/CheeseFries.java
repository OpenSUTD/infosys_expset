package menu.western.sides;

import events.IngredientUsedEvent;
import events.ItemSaleEvent;
import menu.MenuItem;
import simulator.EventBus;
import simulator.Ingredient;
import simulator.Inventory;

public class CheeseFries implements SideDish {
    @Override
    public void make(Inventory inventory) {
        // check stock
        if (!canMake(inventory)) {
            throw new Inventory.NoMoreIngredientException();
        }
        // cook
        inventory.takeOutIngredients(Ingredient.POTATO, 2);
        inventory.takeOutIngredients(Ingredient.CHEESE, 1);
        // notify ingredient usage events
        EventBus.getInstance().notifyObservers(new IngredientUsedEvent(Ingredient.POTATO, 2));
        EventBus.getInstance().notifyObservers(new IngredientUsedEvent(Ingredient.CHEESE, 1));
        // notify item sale event (assume ala carte price)
        EventBus.getInstance().notifyObservers(new ItemSaleEvent(this, 3.50));
    }

    @Override
    public boolean canMake(Inventory inventory) {
        boolean has_potato = inventory.getIngredientCount(Ingredient.POTATO) >= 2;
        boolean has_cheese = inventory.getIngredientCount(Ingredient.CHEESE) >= 1;
        return has_potato && has_cheese;
    }
}
