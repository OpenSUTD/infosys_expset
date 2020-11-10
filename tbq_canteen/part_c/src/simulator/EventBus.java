package simulator;

import java.util.Observable;

public class EventBus extends Observable {
    private final static EventBus instance = new EventBus();

    public static EventBus getInstance(){
        instance.setChanged();
        return instance;
    }
}
