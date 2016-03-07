package carwash.event; 
 
import carwash.state.*;  
import simulator.state.SimState; 
import simulator.event.EventQueue; 
 
/** 
 * A LeaveEvent for a CarWash. Symbolizes the leaving of a car from a carwash. 
  */ 
 public class LeaveEvent extends CarWashEvent { 
 
 
 	/** 
 	 * Constructs a LeaveEvent with a specific car 
 	 * @param car the car related to the event 
 	 * @param time the time the event will occur 
 	 */ 
 	public LeaveEvent(Car car, double time) {// creating a new event 
 		super(time, car); 
 	} 
 
 
 	/** 
 	 * updates the state with the changes that a LeaveEvent causes. 
 	 * It removes the car from the CarWashes and if the car queue 
 	 * isn't empty add a new car from that queue to the machines. 
 	 * @param state the state wich is to be updated 
 	 * @param eventQueue the eventqueue from where the event came from 
 	 */ 
 	public void updateState(SimState state, EventQueue eventQueue) { 
 		CarWashState s = (CarWashState) state; 
 		s.setCurrentTime(this.time); 
 		s.removeWash(this.car); 
 		if (s.getCarQueueSize() != 0) { 
 			Car c = s.removeQueue(); 
 			s.addTotalQueueTime(this.time - c.getArrive()); 
 			eventQueue.insert(new LeaveEvent(c, s.addWash(c))); 
 		} 
 		s.updateView(this); 
 	} 
 } 
