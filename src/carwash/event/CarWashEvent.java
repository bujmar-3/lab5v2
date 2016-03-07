package carwash.event;

import carwash.state.Car;
import simulator.event.Event;
import simulator.state.SimState;

public abstract class CarWashEvent extends Event{
	private Car car = null;
	
	public CarWashEvent(double time) {
		super(time);
	}
	
	public void addCar(Car c) {
		if (car == null){
			car = c;
		} else {
			throw new UnknownError("You can't add 2 cars to an event!");
		}
	}
	public Car getCar() {
		if (car == null) {
			throw new IllegalStateException("You must add a car first!");
		}
		return car;
	}
}

