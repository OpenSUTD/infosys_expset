package menu.western.mains;

import events.IngredientUsedEvent;
import events.ItemSaleEvent;
import menu.MenuItem;
import simulator.EventBus;
import simulator.Ingredient;
import simulator.Inventory;

public class ChickenCutlet implements MainDish {
    @Override
    public void make(Inventory inventory) {
        // check stock
        if (!canMake(inventory)) {
            throw new Inventory.NoMoreIngredientException();
        }
        // cook
        inventory.takeOutIngredients(Ingredient.WHOLE_CHICKEN, 1);
        // notify ingredient usage event
        EventBus.getInstance().notifyObservers(new IngredientUsedEvent(Ingredient.WHOLE_CHICKEN, 1));
        // notify item sale event
        EventBus.getInstance().notifyObservers(new ItemSaleEvent(this, 5.50));
    }

    @Override
    public boolean canMake(Inventory inventory) {
        return inventory.getIngredientCount(Ingredient.WHOLE_CHICKEN) >= 1;
    }
}
