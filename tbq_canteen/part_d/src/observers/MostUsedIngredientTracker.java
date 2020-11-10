package observers;

import events.IngredientUsedEvent;
import simulator.Ingredient;

import java.util.EnumMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * An observer that keeps track of ingredients being used, then reports the most used ingredient via {@link #getMostUsedIngredient()}
 */
public class MostUsedIngredientTracker implements Observer {

    /**
     * Starting code to help you keep track of what ingredients are being used
     */
    private final EnumMap<Ingredient, Integer> ingredients_used = new EnumMap<Ingredient, Integer>(Ingredient.class);

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof IngredientUsedEvent){
            IngredientUsedEvent event = (IngredientUsedEvent) arg;
            int current_count = ingredients_used.getOrDefault(event.getIngredient(), 0);
            ingredients_used.put(event.getIngredient(), current_count + event.getQuantity());
        }
    }

    /**
     * Returns the name of the most used ingredient.
     *
     * Assume there will only be one most used ingredient (no ties).
     *
     * @return most used ingredient
     */
    public Ingredient getMostUsedIngredient(){
        Ingredient most_used_ingredient = null;
        int most_used_count = 0;
        for(Map.Entry<Ingredient, Integer> entry : ingredients_used.entrySet()){
            if(entry.getValue() > most_used_count){
                most_used_ingredient = entry.getKey();
                most_used_count = entry.getValue();
            }
        }
        return most_used_ingredient;
    }
}
