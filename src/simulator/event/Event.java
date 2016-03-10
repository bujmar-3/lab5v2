package simulator.event; 
 
import simulator.state.SimState; 

  /** 
   * an Abstract event describing an event for a Simulation of a SimState 
   * 
   */ 
	public abstract class Event { 
		protected double time; 
 
  /** 
   * Constructs an Event 
   */ 
 	public Event() { 
 	} 
 	
 	public abstract String getName();
 
 	/** 
 	 * Constructs an Event with a given time that it should occur 
 	 * @param time the time the event will occur 
 	 */ 
 	public Event(double time) { 
 		this.time = time; 
 	} 
 
 	/** 
 	 * Method returning the time the event will occur 
 	 * @return the time the event will occur 
 	 */ 
 	public double getTime() { 
 		return time; 
 	} 
 
 	/** 
 	 * The method that execute the event. It updates the state with the changes that the event causes  
 	 * @param state the state that is to be updated due to the event 
 	 * @param eventQueue the queue that the event came from 
 	 */ 
 	public abstract void updateState(SimState state, EventQueue eventQueue); 
 } 