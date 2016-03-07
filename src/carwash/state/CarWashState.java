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
	private double currentTime;
	private int numCarQueue;
	private int rejected;
	private int accepted;
	private int numFast;
	private int numSlow;
	private int freeFast;
	private int freeSlow;
	private double totalIdleCarWash;
	private double totalQueueTime;
	private double meanQueueTime;
	
	
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
		numFast = f;
		freeFast = f;
		numSlow = s;
		freeSlow = s;
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
	public void setMaxQueueSize(int size){
		maxQueueSize = size;
	}
	
	/**
	 * Adds car to the queue.
	 * @param car to add to queue.
	 * */
	public void addQueue(Car car){
		carQueue.add(car);
		numCarQueue++;
	}
	/**
	 * Removes first car in the queue.
	 * */
	public Car removeQueue(){
		Car removed = carQueue.getFirst();
		carQueue.removeFirst();
		numCarQueue--;
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
				freeFast--;
				break;
				}
			}
		if (foundWash == false){
			for (CarWash spot : slow){
				spot.addCar(car);
				freeSlow--;
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
				freeFast++;
				break;
			}
		}
		if (carFound == false){
			for (CarWash spot : slow){
				if (spot.getCar()==car){
					spot.removeCar();
					freeSlow++;
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
	
	///Statistics to print
	/**
	 * Gets seed
	 * @return seed
	 * */
	public long getSeed(){
		return seed;
	}
	/**
	 * Gets lambda
	 * @return lambda
	 * */
	public double getLambda(){
		return lambda;
	}
	/**
	 * Gets the maximum value
	 * */
	public double getSlowMax(){
		return slowMax;
	}
	public double getSlowMin(){
		return slowMin;
	}
	public double getFastMax(){
		return fastMax;
	}
	public double getFastMin(){
		return fastMin;
	}
	/**
	 * Returns next arrive time.
	 * @double arrive time.
	 * */
	public double nextArrive(){
		return latestArrive += carRandom.next();
	}
	/**
	 * Retururns total queue time for car queue.
	 * @return double time.
	 */
	public double getTotalQueueTime(){
		for (Car car : carQueue){
			totalQueueTime += currentTime - car.getArrive();
		}
		return totalQueueTime;
	}
	/**
	 * Returns mean car queue time.
	 * @return double.
	 * */
	public double getMeanQueueTime(){
		return totalQueueTime/accepted;
	}
	/**
	 * Returns number of fast carwashes.
	 * @return int number.
	 * */
	public int getNumFast(){
		return numFast;
	}
	/**
	 * Returns number of slow carwashes.
	 * @return int number.
	 * */
	public int getNumSlow(){
		return numSlow;
	}
	/**
	 * Returns number of fast carwashes with no car.
	 * @return int number of fast washes with no car.
	 * */
	public int getFreeFast(){
		return freeFast;
	}
	/**
	 * Returns number of slow carwashes with no car.
	 * @return int number of slow washes with no car.
	 * */
	public int getFreeSlow(){
		return freeSlow;
	}
	/**
	 * Returns the maximum queue size for the car queue.
	 * @return int maximum size for the car queue.
	 * */
	public int getMaxQueueSize(){
		return maxQueueSize;
	}

}