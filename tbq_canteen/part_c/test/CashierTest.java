import menu.MenuItem;
import menu.chicken_rice.SteamedChickenRice;
import menu.chicken_rice.WantonNoodle;
import menu.mixed_rice.EconomicRice;
import observers.Cashier;
import org.junit.Assert;
import org.junit.Test;
import simulator.EventBus;
import simulator.Ingredient;
import simulator.Inventory;

public class CashierTest {
    @Test
    public void test_should_not_add_sales_when_not_enough_ingredients(){
        Inventory inventory = new Inventory();
        inventory.putInIngredients(Ingredient.WHITE_RICE, 1);
        inventory.putInIngredients(Ingredient.CHICKEN_BREAST, 1);
        Cashier c = new Cashier();
        EventBus.getInstance().deleteObservers();
        EventBus.getInstance().addObserver(c);
        MenuItem food = new SteamedChickenRice();
        food.make(inventory);
        // second one will fail
        try {
            food.make(inventory);
            Assert.fail("Expected failure do to lack of ingredients");
        }catch(Inventory.NoMoreIngredientException ex){
            // do nothing
        }
        // check only one sale was made
        Assert.assertEquals(2.8, c.getTotalSales(), 0);
    }

    @Test
    public void test_cumulative_sales(){
        Inventory inventory = new Inventory();
        inventory.putInIngredients(Ingredient.WHITE_RICE, 100);
        inventory.putInIngredients(Ingredient.CHICKEN_BREAST, 100);
        inventory.putInIngredients(Ingredient.WANTON, 500);
        inventory.putInIngredients(Ingredient.NOODLE, 100);
        Cashier c = new Cashier();
        EventBus.getInstance().deleteObservers();
        EventBus.getInstance().addObserver(c);
        for(int i = 0; i < 100; i ++){
            MenuItem food1 = new SteamedChickenRice();
            MenuItem food2 = new WantonNoodle();
            food1.make(inventory);
            food2.make(inventory);
        }
        // total sales should be 280 + 200
        Assert.assertEquals(480, c.getTotalSales(), 0.01f);
    }

    /**
     * I'm going to make 100 batches of caifan with 1 with the caterpillar. So only 99 of them have should have value.
     */
    @Test
    public void test_should_not_generate_sales_when_caterpillar_appears(){
        Inventory inventory = new Inventory();
        inventory.putInIngredients(Ingredient.WHITE_RICE, 100);
        inventory.putInIngredients(Ingredient.CHICKEN_HAM, 100);
        inventory.putInIngredients(Ingredient.WHOLE_EGG, 100);
        inventory.putInIngredients(Ingredient.CATERPILLAR, 1);
        Cashier c = new Cashier();
        EventBus.getInstance().deleteObservers();
        EventBus.getInstance().addObserver(c);
        for(int i = 0; i < 100; i++){
            MenuItem food = new EconomicRice();
            food.make(inventory);
        }
        // total sales should be 99 * 1.8
        Assert.assertEquals(99*1.8, c.getTotalSales(), 0.1f);
    }

}
