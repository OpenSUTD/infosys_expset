import menu.MenuItem;
import menu.chicken_rice.SteamedChickenRice;
import menu.chicken_rice.WantonNoodle;
import observers.Cashier;
import observers.MostUsedIngredientTracker;
import org.junit.Assert;
import org.junit.Test;
import simulator.EventBus;
import simulator.Ingredient;
import simulator.Inventory;

public class CombinedTest {
    @Test
    public void test_together(){
        Inventory inventory = new Inventory();
        inventory.putInIngredients(Ingredient.WHITE_RICE, 100);
        inventory.putInIngredients(Ingredient.CHICKEN_BREAST, 100);
        inventory.putInIngredients(Ingredient.WANTON, 500);
        inventory.putInIngredients(Ingredient.NOODLE, 100);
        Cashier c = new Cashier();
        MostUsedIngredientTracker t = new MostUsedIngredientTracker();
        EventBus.getInstance().deleteObservers();
        EventBus.getInstance().addObserver(c);
        EventBus.getInstance().addObserver(t);
        for(int i = 0; i < 100; i ++){
            MenuItem food1 = new SteamedChickenRice();
            MenuItem food2 = new WantonNoodle();
            food1.make(inventory);
            food2.make(inventory);
        }
        // total sales should be 280 + 200
        Assert.assertEquals(480, c.getTotalSales(), 0.01f);
        Assert.assertEquals(Ingredient.WANTON, t.getMostUsedIngredient());
    }
}
