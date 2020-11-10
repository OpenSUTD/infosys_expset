package menu.western.mains;

import events.IngredientUsedEvent;
import events.ItemSaleEvent;
import menu.MenuItem;
import simulator.EventBus;
import simulator.Ingredient;
import simulator.Inventory;

public class AglioOlio implements MainDish {

    @Override
    public void make(Inventory inventory) {
        // check if there are enough ingredients
        if (!canMake(inventory)) {
            throw new Inventory.NoMoreIngredientException();
        }
        // cook
        inventory.takeOutIngredients(Ingredient.PASTA, 1);
        inventory.takeOutIngredients(Ingredient.CHICKEN_HAM, 1);
        inventory.takeOutIngredients(Ingredient.TOMATO_SAUCE, 1);
        // notify ingredient usage events
        EventBus.getInstance().notifyObservers(new IngredientUsedEvent(Ingredient.PASTA, 1));
        EventBus.getInstance().notifyObservers(new IngredientUsedEvent(Ingredient.CHICKEN_HAM, 1));
        EventBus.getInstance().notifyObservers(new IngredientUsedEvent(Ingredient.TOMATO_SAUCE, 1));
        // notify item sale events
        // assume ala carte price
        EventBus.getInstance().notifyObservers(new ItemSaleEvent(this, 5));
    }


    @Override
    public boolean canMake(Inventory inventory) {
        // check if there are enough ingredients
        boolean has_pasta = inventory.getIngredientCount(Ingredient.PASTA) >= 1;
        boolean has_ham = inventory.getIngredientCount(Ingredient.CHICKEN_HAM) >= 1;
        boolean has_tomato_sauce = inventory.getIngredientCount(Ingredient.TOMATO_SAUCE) >= 1;
        return has_pasta && has_ham && has_tomato_sauce;
    }
}
