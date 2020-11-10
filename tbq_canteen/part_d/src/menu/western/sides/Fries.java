package menu.western.sides;

import events.IngredientUsedEvent;
import events.ItemSaleEvent;
import menu.MenuItem;
import simulator.EventBus;
import simulator.Ingredient;
import simulator.Inventory;

public class Fries implements SideDish {
    @Override
    public void make(Inventory inventory) {
        // check stock
        if (!canMake(inventory)) {
            throw new Inventory.NoMoreIngredientException();
        }
        // cook
        inventory.takeOutIngredients(Ingredient.POTATO, 2);
        // notify ingredient usage events
        EventBus.getInstance().notifyObservers(new IngredientUsedEvent(Ingredient.POTATO, 2));
        // notify item sale event (assume ala carte price)
        EventBus.getInstance().notifyObservers(new ItemSaleEvent(this, 2.50));
    }

    @Override
    public boolean canMake(Inventory inventory) {
        return inventory.getIngredientCount(Ingredient.POTATO) >= 2;
    }
}
