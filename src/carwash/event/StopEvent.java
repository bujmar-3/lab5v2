package carwash.event;

import simulator.event.Event;
import simulator.state.SimState;

/**
 *
 */
public class StopEvent extends Event{
	
	public StopEvent(double time) {
		super(time);
	}
	
	/**
	 * Turns off the simulation
	 * @param state The state of the simulation
	 */
	public Event[] run(SimState state) {
		state.setCurrentEvent(this);
		state.run = false;
		state.updateTimes();
		Event[] newEvents = { state.stop() };
		return newEvents;
	}
	
	public String toString() {
		return "Stop";
	}
	
}
