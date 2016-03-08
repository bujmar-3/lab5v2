package carwash.event;  
 
import simulator.state.SimState; 
import simulator.event.EventQueue;
import carwash.state.*; 


/** 
 * A class for ArriveEvents in a CarWash. 
 * Symbolizes the arrival of a car to a carwash. 
 */ 
 public class ArriveEvent extends CarWashEvent { 
 	/** 
 	 * Constructs an ArriveEvent 
 	 * @param time The time the event will occur 
 	 * @param car The car that arrives 
 	 */ 
 	public ArriveEvent(double time, Car car) { 
 		super(time, car);  
 	} 
 
 	
 	 	/** 
 	 * updates the state with the changes that an ArriveEvent makes 
 	 * @param state The state that should be updated 
 	 * @param eventQueue the queue where the event came from 
 	 */ 
 	public void updateState(SimState state, EventQueue eventQueue) { 
 		CarWashState s = (CarWashState) state; 
 
 		s.incTotalTimes(this.time);
 		s.setCurrentTime(this.time); 
 
 		if (s.getCarQueueSize() == 0){
 			if (s.getFreeFast() > 0 || s.getFreeSlow() > 0) {
 					// add car to fastWash or slowWash 
 					eventQueue.insert(new LeaveEvent(car, s.addWash(car))); 
 			}
 			else s.addQueue(car);
 		}
 		else if (s.getCarQueueSize()>=s.getMaxQueueSize()){
 			s.reject(); 
 		}
 		else s.addQueue(car);
 		
 		s.updateView(this); 
 		
 		/*
 		if (s.getFreeFast() > 0 || s.getFreeSlow() > 0) { 
 			// add car to fastWash or slowWash 
 			eventQueue.insert(new LeaveEvent(car, s.addWash(car))); 
 		} else if (s.getCarQueueSize() < s.getMaxQueueSize()) { 
 			// add to car queue  
 			s.addQueue(car); 
 		} else { 
 			s.reject(); 
 		} 
 		*/
 		
 
 		// if none of the if-statements is fulfilled the car is simply not used 
 		eventQueue.insert(new ArriveEvent(s.nextArrive(), 
 					s.getCarFactory().createCar())); 
 	} 
 } 
