package menu.chicken_rice;

import menu.MenuItem;
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
        } else {
            throw new Inventory.NoMoreIngredientException();
        }
    }
}
