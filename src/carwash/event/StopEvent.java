package carwash.event; 
 
 
import simulator.event.Event; 
import simulator.state.SimState; 
import simulator.event.EventQueue; 

import carwash.state.CarWashState; 

 
/** 
 * A class symbolizing the stopping of the carwashstate 
 */ 
	public class StopEvent extends Event { 
 
 
/** 
 * Constructs a stop event 
 * @param time the time when the stopevent shall occur 
 */ 
 	public StopEvent(double time) { 
 		this.time = time; 
 	} 
 
 
 	/** 
 	 * method updating the state according to the stop event 
 	 */ 
 	public void updateState(SimState state, EventQueue eventQueue) { 
		CarWashState s = (CarWashState) state; 
		s.incTotalTimes(this.time);
 		s.setCurrentTime(this.time); 
 		s.updateView(this); 
 		s.stop(); 
 	} 
 } 
