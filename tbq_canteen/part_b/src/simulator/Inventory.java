package simulator;

import java.util.EnumMap;

/**
 * A class that can keep track of how many of each {@link Ingredient} the store has left.
 */
public class Inventory {

    /**
     * Do not change this line
     */
    private final EnumMap<Ingredient, Integer> ingredients = new EnumMap<>(Ingredient.class);

    /**
     * Thrown when the user tries to withdraw more ingredients than there currently are in the inventory.
     * Do not change this class
     */
    public static class NoMoreIngredientException extends RuntimeException {

    }

    /**
     * Given an ingredient, how many of this ingredient do I have left?
     * @param ingredient the ingredient to check for
     * @return the remaining count
     */
    public int getIngredientCount(Ingredient ingredient){
        return this.ingredients.getOrDefault(ingredient, 0);
    }

    /**
     * Top up the inventory with more of a certain ingredient.
     * @param ingredient the ingredient to top up
     * @param count how much to top up
     * @throws IllegalArgumentException if count is negative
     */
    public void putInIngredients(Ingredient ingredient, int count){
        if(count < 0){
            throw new IllegalArgumentException("Don't top up a negative value!");
        }
        int currentAmount = this.getIngredientCount(ingredient);
        this.ingredients.put(ingredient, currentAmount + count);
    }

    /**
     * Attempts to take out a certain number of a certain ingredient form the inventory.
     * @param ingredient the ingredient to retrieve
     * @param count how many of this ingredient to retrieve
     * @throws IllegalArgumentException if count is negative
     * @throws NoMoreIngredientException if there are not enough ingredients to satisfy this request
     */
    public void takeOutIngredients(Ingredient ingredient, int count){
        if (count < 0){
            throw new IllegalArgumentException("Don't take out a negative value!");
        }
        int currentAmount = this.getIngredientCount(ingredient);
        if(currentAmount - count < 0){
            throw new NoMoreIngredientException();
        }
        this.ingredients.put(ingredient, currentAmount - count);
    }
}
