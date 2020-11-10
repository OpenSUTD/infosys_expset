import menu.MenuItem;
import menu.chicken_rice.SteamedChickenRice;
import menu.chicken_rice.WantonNoodle;
import menu.mixed_rice.EconomicRice;
import observers.MostUsedIngredientTracker;
import org.junit.Assert;
import org.junit.Test;
import simulator.EventBus;
import simulator.Ingredient;
import simulator.Inventory;

public class MostUsedIngredientTrackerTest {
    @Test
    public void test_chicken_rice(){
        Inventory inventory = new Inventory();
        MostUsedIngredientTracker t = new MostUsedIngredientTracker();
        EventBus.getInstance().deleteObservers();
        EventBus.getInstance().addObserver(t);
        inventory.putInIngredients(Ingredient.WHITE_RICE, 2);
        inventory.putInIngredients(Ingredient.CHICKEN_BREAST, 2);
        MenuItem food = new SteamedChickenRice();
        food.make(inventory);
        food.make(inventory);
        Ingredient most_used_ingredient = t.getMostUsedIngredient();
        boolean chicken_or_rice = most_used_ingredient == Ingredient.CHICKEN_BREAST || most_used_ingredient == Ingredient.WHITE_RICE;
        Assert.assertTrue(chicken_or_rice);
    }

    @Test
    public void test_wanton_noodle(){
        Inventory inventory = new Inventory();
        MostUsedIngredientTracker t = new MostUsedIngredientTracker();
        EventBus.getInstance().deleteObservers();
        EventBus.getInstance().addObserver(t);
        inventory.putInIngredients(Ingredient.WANTON, 50);
        inventory.putInIngredients(Ingredient.NOODLE, 10);
        MenuItem food = new WantonNoodle();
        food.make(inventory);
        food.make(inventory);
        Ingredient most_used_ingredient= t.getMostUsedIngredient();
        Assert.assertEquals(Ingredient.WANTON, most_used_ingredient);
    }

    @Test
    public void test_economic_rice(){
        Inventory inventory = new Inventory();
        MostUsedIngredientTracker t = new MostUsedIngredientTracker();
        EventBus.getInstance().deleteObservers();
        EventBus.getInstance().addObserver(t);
        inventory.putInIngredients(Ingredient.WHITE_RICE, 50);
        inventory.putInIngredients(Ingredient.CHICKEN_HAM, 50);
        inventory.putInIngredients(Ingredient.WHOLE_EGG, 50);
        MenuItem food = new EconomicRice();
        food.make(inventory);
        food.make(inventory);
        Ingredient most_used_ingredient = t.getMostUsedIngredient();
        boolean rice_or_ham_or_egg = most_used_ingredient == Ingredient.WHITE_RICE || most_used_ingredient == Ingredient.CHICKEN_HAM || most_used_ingredient == Ingredient.WHOLE_EGG;
        Assert.assertTrue(rice_or_ham_or_egg);
    }

    @Test
    public void test_combination_of_orders(){
        Inventory inventory = new Inventory();
        MostUsedIngredientTracker t = new MostUsedIngredientTracker();
        EventBus.getInstance().deleteObservers();
        EventBus.getInstance().addObserver(t);
        inventory.putInIngredients(Ingredient.WHITE_RICE, 100);
        inventory.putInIngredients(Ingredient.CHICKEN_HAM, 100);
        inventory.putInIngredients(Ingredient.WHOLE_EGG, 100);
        inventory.putInIngredients(Ingredient.CHICKEN_BREAST, 100);
        MenuItem food1 = new SteamedChickenRice();
        MenuItem food2 = new EconomicRice();
        food1.make(inventory);
        food2.make(inventory);
        food1.make(inventory);
        food2.make(inventory);
        Ingredient most_used_ingredient = t.getMostUsedIngredient();
        Assert.assertEquals(Ingredient.WHITE_RICE, most_used_ingredient);
    }
}
