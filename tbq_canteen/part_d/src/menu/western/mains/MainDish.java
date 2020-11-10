package menu.western.mains;

import menu.MenuItem;
import simulator.Inventory;

public interface MainDish extends MenuItem {

    /**
     * Checks if this dish can be made given the inventory, without actually modifying the inventory
     *
     * @param inventory the inventory to check in
     * @return whether the dish can be made or not
     */
    boolean canMake(Inventory inventory);
}
