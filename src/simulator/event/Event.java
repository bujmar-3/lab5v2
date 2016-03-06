package simulator.event;

import simulator.state.SimState;

/**
 * This abstract class is extended to proper events such as StartEvent.
 * 
 */
public abstract class Event implements Comparable<Event> {
	/** The time this event will be run. */
	private double time;
	
	/**
	 * Initializes the time.
	 * @param time The time this event will be run
	 */
	public Event(double time) {
		this.time = time;
	}
	
	/**
	 * Gets the time this event will be run.
	 * @return The time this event will be run
	 */
	public double getTime() {
		return time;
	}
	
	/**
	 * The abstract run class. Is implemented in proper events.
	 * @param state The state
	 * @return An array of events that this event caused.
	 */
	public abstract Event[] run(SimState state);
	
	/**
	 * Compares this event against another.
	 */
	public int compareTo(Event o) {
		if (time < o.getTime()) {
			return -1;
		}
		return 1;
	}
	
	/**
	 * A string representing the event, usually only the event name.
	 */
	public abstract String toString();
}
