package simulator;

import simulator.state.SimState;
import simulator.event.*;

public class Simulator {
	private SimState state;
	private EventQueue eventQueue;
	
	public Simulator(SimState s, EventQueue e){
		state = s;
		eventQueue = e;
	}
	public void execute(){
		while (state.state()){
			Event nextEvent = eventQueue.get();
			nextEvent.updateState(state, eventQueue);
		}
	}
}
