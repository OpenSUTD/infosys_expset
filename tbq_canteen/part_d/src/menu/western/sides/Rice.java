package menu.western.sides;

import events.IngredientUsedEvent;
import events.ItemSaleEvent;
import menu.MenuItem;
import simulator.EventBus;
import simulator.Ingredient;
import simulator.Inventory;

public class Rice implements SideDish {
    @Override
    public void make(Inventory inventory) {
        // check stock
        if(!canMake(inventory)){
            throw new Inventory.NoMoreIngredientException();
        }
        // cook
        inventory.takeOutIngredients(Ingredient.WHITE_RICE, 1);
        // notify ingredient usage event
        EventBus.getInstance().notifyObservers(new IngredientUsedEvent(Ingredient.WHITE_RICE, 1));
        // notify item sale event (assume ala carte price)
        EventBus.getInstance().notifyObservers(new ItemSaleEvent(this, 0.80));
    }

    @Override
    public boolean canMake(Inventory inventory) {
        return inventory.getIngredientCount(Ingredient.WHITE_RICE) >= 1;
    }
}
