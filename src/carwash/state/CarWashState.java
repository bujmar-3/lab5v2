package carwash.state;

import simulator.state.SimState;
import java.util.Vector;
import random.ExponentialRandomStream;
import random.UniformRandomStream;

public class CarWashState extends SimState {
	private Vector<CarWash> slow;
	private Vector<CarWash> fast;
	private FIFO carQueue;
	private CarFactory factory;
	private int maxQueueSize;
	private ExponentialRandomStream carRandom;
	private UniformRandomStream fastRandom;
	private UniformRandomStream slowRandom;
	//Statistics
	private double latestArrive;
	private long seed;
	private double fastMin;
	private double fastMax;
	private double slowMin;
	private double slowMax;
	private double lambda;
	
	
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
			CarWash wash = new CarWash(0, slowRandom);
			slow.addElement(wash);
		}
		//Creates fast machines
		for (int e =0; e < f; e++){
			CarWash wash = new CarWash(1, fastRandom);
			fast.addElement(wash);
		}
	}
	/**
	 * Sets maximum queue size for car queue.
	 * @param size
	 * */
	public void setMaxQueue(int size){
		maxQueueSize = size;
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
	public Car removeQueue(){
		Car removed = carQueue.getFirst();
		carQueue.removeFirst();
		return removed;
	}
	/**
	 * Adds Car to CarWash
	 * @param car to add.
	 * */
	public void addWash(Car car){
		boolean foundWash = false;
		for (CarWash spot : fast){
			if(spot.gotCar()==false){
				spot.addCar(car);
				foundWash = true;
				break;
				}
			}
		if (foundWash == false){
			for (CarWash spot : slow){
				spot.addCar(car);
				break;
			}
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
	
	/**
	 * Returns the carfactory
	 * @return CarFactory
	 * */
	public CarFactory getCarFactory(){
		return factory;
	}
	
	/**
	 * Creates random streams.
	 * */
	public void createRandom(double fastMax, double fastMin, double slowMax, double slowMin, double lambda, long seed){
		this.fastMax = fastMax;
		this.fastMin = fastMin;
		this.slowMax = slowMax;
		this.slowMin = slowMin;
		this.lambda = lambda;
		this.seed = seed;
		
		carRandom = new ExponentialRandomStream(this.lambda, this.seed);
		fastRandom = new UniformRandomStream(this.fastMin, this.fastMax, this.seed);
		slowRandom = new UniformRandomStream(this.slowMin, this.slowMax, this.seed);
	}
	
	/**
	 * Gets seed
	 * @return seed
	 * */
	public long getSeed(){
		return seed;
	}
	/**
	 * Returns next arrive time.
	 * @double arrive time.
	 * */
	public double nextArrive(){
		return latestArrive += carRandom.next();
	}

}