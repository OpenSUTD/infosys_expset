package tests.menu;

import menu.MenuItem;
import menu.mixed_rice.EconomicRice;
import org.junit.Assert;
import org.junit.Test;
import simulator.Ingredient;
import simulator.Inventory;

public class EconomicRiceTest {
    @Test
    public void test_makes_only_one_serving(){
        Inventory inventory = new Inventory();
        inventory.putInIngredients(Ingredient.WHITE_RICE, 2);
        inventory.putInIngredients(Ingredient.WHOLE_EGG, 2);
        inventory.putInIngredients(Ingredient.CHICKEN_HAM, 2);
        MenuItem food = new EconomicRice();
        food.make(inventory);
        Assert.assertEquals(1, inventory.getIngredientCount(Ingredient.WHITE_RICE));
        Assert.assertEquals(1, inventory.getIngredientCount(Ingredient.WHOLE_EGG));
        Assert.assertEquals(1, inventory.getIngredientCount(Ingredient.CHICKEN_HAM));
    }

    @Test
    public void test_will_not_produce_caterpillar_when_not_present(){
        Inventory inventory = new Inventory();
        inventory.putInIngredients(Ingredient.WHITE_RICE, 1000);
        inventory.putInIngredients(Ingredient.WHOLE_EGG, 1000);
        inventory.putInIngredients(Ingredient.CHICKEN_HAM, 1000);
        for(int i = 0; i < 1000; i ++){
            MenuItem food = new EconomicRice();
            food.make(inventory);
        }
        Assert.assertEquals(0, inventory.getIngredientCount(Ingredient.WHITE_RICE));
        Assert.assertEquals(0, inventory.getIngredientCount(Ingredient.WHOLE_EGG));
        Assert.assertEquals(0, inventory.getIngredientCount(Ingredient.CHICKEN_HAM));
    }

    @Test
    public void test_caterpillar_will_eventually_be_consumed(){
        Inventory inventory = new Inventory();
        inventory.putInIngredients(Ingredient.WHITE_RICE, 1000);
        inventory.putInIngredients(Ingredient.WHOLE_EGG, 1000);
        inventory.putInIngredients(Ingredient.CHICKEN_HAM, 1000);
        inventory.putInIngredients(Ingredient.CATERPILLAR, 1);
        boolean caterpillar_consumed = false;
        for(int i = 0; i < 1000; i ++){
            MenuItem food = new EconomicRice();
            food.make(inventory);
            caterpillar_consumed = inventory.getIngredientCount(Ingredient.CATERPILLAR) == 0;
            if(caterpillar_consumed){
                break;
            }
        }
        Assert.assertTrue(caterpillar_consumed);
    }

    @Test
    public void test_does_not_consume_ingredients_on_exception(){
        Inventory inventory = new Inventory();
        inventory.putInIngredients(Ingredient.WHITE_RICE, 1);
        MenuItem food = new EconomicRice();
        try {
            food.make(inventory);
            Assert.fail("Expected exception but did not fail");
        }catch(Inventory.NoMoreIngredientException ex){
            Assert.assertEquals(1, inventory.getIngredientCount(Ingredient.WHITE_RICE));
        }
    }

}
