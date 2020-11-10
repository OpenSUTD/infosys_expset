package menu.chicken_rice;

import events.IngredientUsedEvent;
import events.ItemSaleEvent;
import menu.MenuItem;
import simulator.EventBus;
import simulator.Ingredient;
import simulator.Inventory;

public class WantonNoodle implements MenuItem {
    @Override
    public void make(Inventory inventory) {
        boolean has_wantons = inventory.getIngredientCount(Ingredient.WANTON) >= 5;
        boolean has_noodle = inventory.getIngredientCount(Ingredient.NOODLE) >= 1;
        if (has_wantons && has_noodle){
            // deduct from inventory
            inventory.takeOutIngredients(Ingredient.WANTON, 5);
            inventory.takeOutIngredients(Ingredient.NOODLE, 1);
            EventBus.getInstance().notifyObservers(new IngredientUsedEvent(Ingredient.WANTON, 5));
            EventBus.getInstance().notifyObservers(new IngredientUsedEvent(Ingredient.NOODLE, 1));
            EventBus.getInstance().notifyObservers(new ItemSaleEvent(this, 2.0));
        } else {
            throw new Inventory.NoMoreIngredientException();
        }
    }
}
