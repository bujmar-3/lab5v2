package simulator.view;

import java.util.Observable;
import java.util.Observer;
import simulator.event.Event;


public abstract class SimView implements Observer {
	
	public abstract void update(Observable o, Event e);
}
