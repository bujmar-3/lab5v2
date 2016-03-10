package simulator.view;

import java.util.Observer;
import simulator.state.SimState;


public abstract class SimView implements Observer {
	
	public SimView(SimState state){
		state.addObserver(this);
	}
}
