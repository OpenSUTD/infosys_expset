package menu.chicken_rice;

import events.IngredientUsedEvent;
import events.ItemSaleEvent;
import menu.MenuItem;
import simulator.EventBus;
import simulator.Ingredient;
import simulator.Inventory;

public class SteamedChickenRice implements MenuItem {
    @Override
    public void make(Inventory inventory) {
        boolean has_rice = inventory.getIngredientCount(Ingredient.WHITE_RICE) >= 1;
        boolean has_breasts = inventory.getIngredientCount(Ingredient.CHICKEN_BREAST) >= 1;
        if (!has_breasts){
            // try to find a whole chicken
            boolean has_chicken = inventory.getIngredientCount(Ingredient.WHOLE_CHICKEN) >= 1;
            if (has_chicken){
                // chop chop!
                inventory.takeOutIngredients(Ingredient.WHOLE_CHICKEN, 1);
                inventory.putInIngredients(Ingredient.CHICKEN_BREAST, 5);
                inventory.putInIngredients(Ingredient.CHICKEN_WING, 2);
                make(inventory);
            } else {
                throw new Inventory.NoMoreIngredientException();
            }
        }else if (has_rice && has_breasts){
            // cook!
            inventory.takeOutIngredients(Ingredient.CHICKEN_BREAST, 1);
            inventory.takeOutIngredients(Ingredient.WHITE_RICE, 1);
            EventBus.getInstance().notifyObservers(new IngredientUsedEvent(Ingredient.CHICKEN_BREAST, 1));
            EventBus.getInstance().notifyObservers(new IngredientUsedEvent(Ingredient.WHITE_RICE, 1));
            EventBus.getInstance().notifyObservers(new ItemSaleEvent(this, 2.8));
        } else {
            throw new Inventory.NoMoreIngredientException();
        }
    }
}
