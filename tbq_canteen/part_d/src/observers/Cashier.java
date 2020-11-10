package observers;

import events.ItemSaleEvent;

import java.util.Observable;
import java.util.Observer;

/**
 * An observer that sums up all the sales it observes to be retrieved later.
 */
public class Cashier implements Observer {

    private double total_sales = 0;

    @Override
    public void update(Observable o, Object event) {
        if(event instanceof ItemSaleEvent){
            ItemSaleEvent sale = (ItemSaleEvent) event;
            total_sales += sale.getPrice();
        }
    }

    /**
     * Get the total sales (money) recorded so far
     * @return total sales
     */
    public double getTotalSales(){
        return total_sales;
    }
}
