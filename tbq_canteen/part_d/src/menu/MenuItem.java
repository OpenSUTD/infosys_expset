package menu;


import simulator.Inventory;

/**
 * Any class that implements this represents a food item that can be prepared and ordered off the menu.
 */
public interface MenuItem {

    /**
     * Consumes some ingredients from the given {@link Inventory} and tries to make a product.
     * @param inventory the current inventory of items
     */
    void make(Inventory inventory);
}
