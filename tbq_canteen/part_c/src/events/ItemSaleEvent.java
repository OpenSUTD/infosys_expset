package events;

import menu.MenuItem;

/**
 * Event that is broadcast when an item is sold
 */
public class ItemSaleEvent {
    private final MenuItem itemSold;
    private final double price;

    /**
     * Creates an Item Sale Event to notify others of your earnings
     * @param itemSold the item sold
     * @param price the price this item sold for
     */
    public ItemSaleEvent(MenuItem itemSold, double price) {
        this.itemSold = itemSold;
        this.price = price;
    }

    /**
     * Gets the item sold in this event
     * @return the item sold
     */
    public MenuItem getItemSold() {
        return itemSold;
    }

    /**
     * Get the price this item sold for
     * @return sale price
     */
    public double getPrice() {
        return price;
    }
}
