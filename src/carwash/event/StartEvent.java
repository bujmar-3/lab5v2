package carwash.event; 
 
 
import simulator.event.Event; 
import simulator.state.SimState; 
import simulator.event.EventQueue; 

 
import carwash.state.CarWashState; 
/** 
 * A StartEvent symbolizing when the simulation starts 
 * @author Andreas Nielsen, Fredrik Lind, Sebastian Larsson 
 * 
 */ 
	public class StartEvent extends Event { 
		public StartEvent() { 
 		this.time = 0.0; 
 	} 
 
 
/** 
 * method uppdating the state of the system according to a Start event 
 */ 
 	public void updateState(SimState state, EventQueue eventQueue) { 
 		CarWashState s = (CarWashState) state; 
 		s.getInfo().setCurrentTime(this.time); 
 		eventQueue.insert(new ArriveEvent(s.nextArriveTime(), 
 					s.getCarFactory().createCar())); 
 		s.doNotify(this); 
 	} 
 } 