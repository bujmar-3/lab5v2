package simulator;

import simulator.state.SimState;
import simulator.event.*;
/**
 * A discrete event-driven simulator that simulates a state.
 * */
public class Simulator {
	private SimState state;
	private EventQueue eventQueue;
	
	/**
	 * Creates the simulator.
	 * @param s The SimState used in the simulation.
	 * @param e The EventQueue used for the simulation.
	 * */
	public Simulator(SimState s, EventQueue e){
		state = s;
		eventQueue = e;
	}
	/**
	 * Gets the first event in eventQueue and executes the code in method updateState
	 *  in each event which changes the state of the simulator. 
	 *  If the event calls stop method in the state or the event queue is empty simulation stops.
	 * */
	public void execute(){
		while (state.state() && eventQueue.size()!=0){
			Event nextEvent = eventQueue.get();
			nextEvent.updateState(state, eventQueue);
		}
	}
}
