package carwash.event; 
 
import simulator.event.Event; 
import simulator.state.SimState; 
import simulator.event.EventQueue; 

import carwash.state.CarWashState; 
  /** 
   * A StartEvent symbolizing when the simulation starts 
   */ 
	public class StartEvent extends Event { 
		public StartEvent() { 
 		this.time = 0.0; 
 	} 
 
  /** 
   * method updating the state of the system according to a Start event
   * @param state The state that should be updated 
   * @param eventQueue the queue where the event came from  
   */ 
 	public void updateState(SimState state, EventQueue eventQueue) { 
 		CarWashState s = (CarWashState) state; 
 		s.setCurrentTime(this.time); 
 		eventQueue.insert(new ArriveEvent(s.nextArrive(), 
 		s.getCarFactory().createCar())); 
 		s.updateView(this); 
 	}

@Override
public String getName() {
	// TODO Auto-generated method stub
	return "Start";
} 
 } 