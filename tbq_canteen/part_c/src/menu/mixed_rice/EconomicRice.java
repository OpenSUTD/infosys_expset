package menu.mixed_rice;

import events.IngredientUsedEvent;
import events.ItemSaleEvent;
import menu.MenuItem;
import simulator.EventBus;
import simulator.Ingredient;
import simulator.Inventory;

import java.util.Random;

public class EconomicRice implements MenuItem {
    @Override
    public void make(Inventory inventory) {
        boolean has_white_rice = inventory.getIngredientCount(Ingredient.WHITE_RICE) >= 1;
        boolean has_egg = inventory.getIngredientCount(Ingredient.WHOLE_EGG) >= 1;
        boolean has_ham = inventory.getIngredientCount(Ingredient.CHICKEN_HAM) >= 1;
        boolean has_basics = has_white_rice && has_egg && has_ham;
        if (!has_basics) {
            throw new Inventory.NoMoreIngredientException();
        }
        boolean has_caterpillar = inventory.getIngredientCount(Ingredient.CATERPILLAR) >= 1;
        boolean include_caterpillar = false;
        if (has_caterpillar) {
            Random rng = new Random();
            include_caterpillar = rng.nextInt(10) == 0;
        }
        // start cooking
        inventory.takeOutIngredients(Ingredient.WHITE_RICE, 1);
        inventory.takeOutIngredients(Ingredient.WHOLE_EGG, 1);
        inventory.takeOutIngredients(Ingredient.CHICKEN_HAM, 1);
        EventBus.getInstance().notifyObservers(new IngredientUsedEvent(Ingredient.WHITE_RICE, 1));
        EventBus.getInstance().notifyObservers(new IngredientUsedEvent(Ingredient.WHOLE_EGG, 1));
        EventBus.getInstance().notifyObservers(new IngredientUsedEvent(Ingredient.CHICKEN_HAM, 1));
        if (include_caterpillar) {
            inventory.takeOutIngredients(Ingredient.CATERPILLAR, 1);
            EventBus.getInstance().notifyObservers(new IngredientUsedEvent(Ingredient.CATERPILLAR, 1));
            EventBus.getInstance().notifyObservers(new ItemSaleEvent(this, 0));
        } else {
            EventBus.getInstance().notifyObservers(new ItemSaleEvent(this, 1.8));
        }
    }
}
