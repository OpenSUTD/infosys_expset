import menu.MenuItem;
import menu.mixed_rice.EconomicRice;
import observers.NEAWatchdog;
import org.junit.Assert;
import org.junit.Test;
import simulator.EventBus;
import simulator.Ingredient;
import simulator.Inventory;

public class NEAWatchdogTest {
    @Test
    public void test_no_trip_when_no_caterpillar() {
        Inventory inventory = new Inventory();
        inventory.putInIngredients(Ingredient.WHITE_RICE, 100);
        inventory.putInIngredients(Ingredient.CHICKEN_HAM, 100);
        inventory.putInIngredients(Ingredient.WHOLE_EGG, 100);
        NEAWatchdog w = new NEAWatchdog();
        EventBus.getInstance().deleteObservers();
        EventBus.getInstance().addObserver(w);
        for (int i = 0; i < 100; i++) {
            MenuItem food = new EconomicRice();
            food.make(inventory);
        }
        Assert.assertFalse(w.willIssueSummon());
    }

    /**
     * If this fails (very small chance), try running this multiple times.
     * If it fails multiple times it means your code got problem.
     */
    @Test
    public void test_will_eventually_trip_with_caterpillar() {
        Inventory inventory = new Inventory();
        inventory.putInIngredients(Ingredient.WHITE_RICE, 100);
        inventory.putInIngredients(Ingredient.CHICKEN_HAM, 100);
        inventory.putInIngredients(Ingredient.WHOLE_EGG, 100);
        inventory.putInIngredients(Ingredient.CATERPILLAR, 1);
        NEAWatchdog w = new NEAWatchdog();
        EventBus.getInstance().deleteObservers();
        EventBus.getInstance().addObserver(w);
        for (int i = 0; i < 100; i++) {
            MenuItem food = new EconomicRice();
            food.make(inventory);
        }
        Assert.assertTrue(w.willIssueSummon());
    }
}
