package carwash.state;

import simulator.state.SimState;
import java.util.Vector;

public class CarWashState extends SimState {
	private Vector<CarWash> slow;
	private Vector<CarWash> fast;
	private FIFO carQueue;
	private CarFactory factory;
	
	//Statistics
	
	
	public CarWashState(){
		slow = new Vector<CarWash>();
		fast = new Vector<CarWash>();
		factory = new CarFactory();
		carQueue = new FIFO();
		start();
	}
	/**
	 * Creates CarWashes
	 * @param s Creates the number s slow carwashes
	 * @param f Creates the number f fast carwashes
	 * */
	public void createWashes(int s, int f){
		//Creates slow machines
		for (int i = 0; i < s; i++){
			CarWash wash = new CarWash(0);
			slow.addElement(wash);
		}
		//Creates fast machines
		for (int e =0; e < f; e++){
			CarWash wash = new CarWash(1);
			fast.addElement(wash);
		}
	}
	/**
	 * Adds car to the queue.
	 * @param car to add to queue.
	 * */
	public void addQueue(Car car){
		carQueue.add(car);
	}
	/**
	 * Removes first car in the queue.
	 * */
	public void removeQueue(){
		carQueue.removeFirst();
	}
	/**
	 * Checks if car queue is empty. If car queue is empty it tries to add car to an Carwash.
	 * If queue is not empty it adds car to car queue.
	 * @param car
	 * */
	public void checkQueue(Car car){
		if (carQueue.empty()){
			addWash(car);
		}
		else{addQueue(car);}
	}
	/**
	 * Tries to add car to a carwash, if carwashes are full adds car to car queue.
	 * @param car to add.
	 * */
	public void addWash(Car car){
			boolean fastFound = false;
			boolean slowFound = false;
			for (CarWash spot : fast){
				if(spot.gotCar()==false){
					spot.addCar(car);
					fastFound = true;
					break;
				}
			}
			if (fastFound == false){
				for (CarWash spot : slow){
					if(spot.gotCar()==false){
						spot.addCar(car);
						slowFound = true;
						break;
					}
				}
			}
			if(slowFound==false){
				addQueue(car);
		}
	}
	/**
	 * Removes specific car from the carwash.
	 * @param car to remove from the carwash.
	 * */
	public void removeWash(Car car){
		boolean carFound = false;
		for (CarWash spot : fast){
			if (spot.getCar()==car){
				spot.removeCar();
				carFound = true;
				break;
			}
		}
		if (carFound == false){
			for (CarWash spot : slow){
				if (spot.getCar()==car){
					spot.removeCar();
					break;
				}
			}
		}
	}
}
