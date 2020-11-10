package tests.menu;

import menu.MenuItem;
import menu.chicken_rice.WantonNoodle;
import org.junit.Assert;
import org.junit.Test;
import simulator.Ingredient;
import simulator.Inventory;

public class WantonNoodleTest {
    @Test
    public void test_makes_only_one_serving(){
        Inventory inventory = new Inventory();
        inventory.putInIngredients(Ingredient.WANTON, 6);
        inventory.putInIngredients(Ingredient.NOODLE, 2);
        MenuItem food = new WantonNoodle();
        food.make(inventory);
        Assert.assertEquals(1, inventory.getIngredientCount(Ingredient.WANTON));
        Assert.assertEquals(1, inventory.getIngredientCount(Ingredient.NOODLE));
    }

    @Test(expected = Inventory.NoMoreIngredientException.class)
    public void test_cannot_make_when_not_enough_ingredients(){
        Inventory inventory = new Inventory();
        inventory.putInIngredients(Ingredient.WANTON, 1);
        inventory.putInIngredients(Ingredient.NOODLE, 1);
        MenuItem food = new WantonNoodle();
        food.make(inventory);
    }

    @Test
    public void test_no_ingredients_consumed_on_exception(){
        Inventory inventory = new Inventory();
        inventory.putInIngredients(Ingredient.WANTON, 1);
        inventory.putInIngredients(Ingredient.NOODLE, 1);
        MenuItem food = new WantonNoodle();
        try {
            food.make(inventory);
            Assert.fail("No exception was thrown but expecting failure");
        }catch(Inventory.NoMoreIngredientException ex){
            Assert.assertEquals(1, inventory.getIngredientCount(Ingredient.WANTON));
            Assert.assertEquals(1, inventory.getIngredientCount(Ingredient.NOODLE));
        }
    }
}
