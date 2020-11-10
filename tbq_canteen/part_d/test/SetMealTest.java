import menu.western.SetMeal;
import menu.western.sides.Fries;
import menu.western.sides.MashedPotato;
import menu.western.sides.Rice;
import observers.Cashier;
import org.junit.Assert;
import org.junit.Test;
import simulator.EventBus;
import simulator.Ingredient;
import simulator.Inventory;

public class SetMealTest {

    @Test(expected = IllegalArgumentException.class)
    public void test_no_side_will_throw_exception() {
        SetMeal.SetMealBuilder builder = new SetMeal.SetMealBuilder();
        builder.setMainAsAglioOlio();
        SetMeal meal = builder.build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_no_main_will_throw_exception() {
        SetMeal.SetMealBuilder builder = new SetMeal.SetMealBuilder();
        builder.addSide(new Fries());
        builder.build();
    }

    @Test
    public void test_set_meal_gives_discount() {
        SetMeal.SetMealBuilder builder = new SetMeal.SetMealBuilder();
        SetMeal meal = builder.setMainAsChickenCutlet()
                            .addSide(new Rice())
                            .addSide(new MashedPotato())
                            .build();
        Inventory inventory = new Inventory();
        inventory.putInIngredients(Ingredient.WHOLE_CHICKEN, 1);
        inventory.putInIngredients(Ingredient.WHITE_RICE, 1);
        inventory.putInIngredients(Ingredient.POTATO, 2);
        inventory.putInIngredients(Ingredient.GRAVY, 1);
        Cashier c = new Cashier();
        EventBus.getInstance().deleteObservers();
        EventBus.getInstance().addObserver(c);
        meal.make(inventory);
        Assert.assertEquals(6.80, c.getTotalSales(), 0.01);
    }

    @Test
    public void test_set_meal_consumes_ingredients() {
        SetMeal.SetMealBuilder builder = new SetMeal.SetMealBuilder();
        SetMeal meal = builder.setMainAsChickenCutlet()
                            .addSide(new Rice())
                            .addSide(new MashedPotato())
                            .build();
        Inventory inventory = new Inventory();
        inventory.putInIngredients(Ingredient.WHOLE_CHICKEN, 1);
        inventory.putInIngredients(Ingredient.WHITE_RICE, 1);
        inventory.putInIngredients(Ingredient.POTATO, 2);
        inventory.putInIngredients(Ingredient.GRAVY, 1);
        meal.make(inventory);
        Assert.assertEquals(0, inventory.getIngredientCount(Ingredient.WHOLE_CHICKEN));
        Assert.assertEquals(0, inventory.getIngredientCount(Ingredient.WHITE_RICE));
        Assert.assertEquals(0, inventory.getIngredientCount(Ingredient.POTATO));
        Assert.assertEquals(0, inventory.getIngredientCount(Ingredient.GRAVY));
    }

    @Test(expected = Inventory.NoMoreIngredientException.class)
    public void test_will_throw_not_enough_ingredients(){
        SetMeal.SetMealBuilder builder = new SetMeal.SetMealBuilder();
        SetMeal meal = builder.setMainAsChickenCutlet()
                            .addSide(new Rice())
                            .addSide(new MashedPotato())
                            .build();
        Inventory inventory = new Inventory();
        meal.make(inventory);
    }


}
