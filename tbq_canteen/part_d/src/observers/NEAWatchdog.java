package observers;

import events.IngredientUsedEvent;
import simulator.Ingredient;

import java.util.Observable;
import java.util.Observer;

/**
 * An observer that keeps track if caterpillars have been used in food.
 */
public class NEAWatchdog implements Observer {

    private boolean issue_summon = false;

    @Override
    public void update(Observable o, Object event) {
        if(event instanceof IngredientUsedEvent){
            IngredientUsedEvent _event = (IngredientUsedEvent) event;
            if(_event.getIngredient() == Ingredient.CATERPILLAR){
                issue_summon = true;
            }
        }
    }

    /**
     * Will there be a summon issued?
     * @return issue_summon
     */
    public boolean willIssueSummon(){
        return issue_summon;
    }
}
