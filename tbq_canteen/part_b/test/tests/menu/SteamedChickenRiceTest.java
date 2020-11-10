package tests.menu;

import menu.MenuItem;
import menu.chicken_rice.SteamedChickenRice;
import org.junit.Assert;
import org.junit.Test;
import simulator.Ingredient;
import simulator.Inventory;

public class SteamedChickenRiceTest {
    @Test(expected = Inventory.NoMoreIngredientException.class)
    public void test_not_enough_basic_ingredients_will_fail() {
        Inventory inventory = new Inventory();
        MenuItem food = new SteamedChickenRice();
        food.make(inventory);
    }

    @Test(expected = Inventory.NoMoreIngredientException.class)
    public void test_not_enough_basic_ingredients_with_partial_inventory_will_fail() {
        Inventory inventory = new Inventory();
        inventory.putInIngredients(Ingredient.WHITE_RICE, 1);
        MenuItem food = new SteamedChickenRice();
        food.make(inventory);
    }

    @Test
    public void test_can_break_up_whole_chicken() {
        Inventory inventory = new Inventory();
        inventory.putInIngredients(Ingredient.WHITE_RICE, 2);
        inventory.putInIngredients(Ingredient.WHOLE_CHICKEN, 1);
        MenuItem food = new SteamedChickenRice();
        food.make(inventory);
        Assert.assertEquals(1, inventory.getIngredientCount(Ingredient.WHITE_RICE));
        Assert.assertEquals(4, inventory.getIngredientCount(Ingredient.CHICKEN_BREAST));
        Assert.assertEquals(2, inventory.getIngredientCount(Ingredient.CHICKEN_WING));
        Assert.assertEquals(0, inventory.getIngredientCount(Ingredient.WHOLE_CHICKEN));
    }

    @Test
    public void test_makes_only_one_serving() {
        Inventory inventory = new Inventory();
        inventory.putInIngredients(Ingredient.WHITE_RICE, 2);
        inventory.putInIngredients(Ingredient.CHICKEN_BREAST, 2);
        MenuItem food = new SteamedChickenRice();
        food.make(inventory);
        Assert.assertEquals(1, inventory.getIngredientCount(Ingredient.WHITE_RICE));
        Assert.assertEquals(1, inventory.getIngredientCount(Ingredient.CHICKEN_BREAST));
    }

    @Test
    public void test_no_ingredients_consumed_on_exception() {
        Inventory inventory = new Inventory();
        inventory.putInIngredients(Ingredient.WHITE_RICE, 1);
        MenuItem food = new SteamedChickenRice();
        try {
            food.make(inventory);
            Assert.fail("No exception thrown but expecting failure");
        } catch (Inventory.NoMoreIngredientException ex) {
            Assert.assertEquals(1, inventory.getIngredientCount(Ingredient.WHITE_RICE));
        }
    }
}
