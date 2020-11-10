package events;

import simulator.Ingredient;

/**
 * Event that is broadcast when an ingredient is used.
 * I do not care which MenuItem was consuming this ingredient.
 */
public class IngredientUsedEvent {

    private final Ingredient ingredient;
    private final int quantity;

    /**
     * Creates a new IngredientUsedEvent to be sent to the event bus.
     *
     * Interested observers can listen to this event to keep track of what ingredients are being used.
     * @param ingredient the ingredient being consumed
     * @param quantity the quantity consumed
     */
    public IngredientUsedEvent(Ingredient ingredient, int quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    /**
     * Get the ingredient that was consumed in this event
     * @return the consumed ingredient
     */
    public Ingredient getIngredient() {
        return ingredient;
    }

    /**
     * Get the quantity of the ingredient that was consumed in this event
     * @return the consumed content
     */
    public int getQuantity() {
        return quantity;
    }
}
