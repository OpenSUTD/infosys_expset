import org.junit.Assert;
import org.junit.Test;
import simulator.Ingredient;
import simulator.Inventory;


public class InventoryTest {
    @Test
    public void test_initial_getIngredientCount_is_zero(){
        Inventory inventory = new Inventory();
        Assert.assertEquals(0, inventory.getIngredientCount(Ingredient.CHEESE));
    }

    @Test
    public void test_can_put_ingredient(){
        Inventory inventory = new Inventory();
        inventory.putInIngredients(Ingredient.CHEESE, 2);
        Assert.assertEquals(2, inventory.getIngredientCount(Ingredient.CHEESE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_cannot_put_negative_ingredients(){
        Inventory inventory = new Inventory();
        inventory.putInIngredients(Ingredient.CHEESE, -2);
    }

    @Test
    public void test_can_take_ingredients(){
        Inventory inventory = new Inventory();
        inventory.putInIngredients(Ingredient.CHEESE, 2);
        inventory.takeOutIngredients(Ingredient.CHEESE, 1);
        Assert.assertEquals(1, inventory.getIngredientCount(Ingredient.CHEESE));
    }

    @Test(expected = Inventory.NoMoreIngredientException.class)
    public void test_cannot_take_more_than_available(){
        Inventory inventory = new Inventory();
        inventory.takeOutIngredients(Ingredient.CHEESE, 1);
    }
}
