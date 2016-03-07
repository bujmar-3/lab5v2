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
	private int carQueueSize;
	private int rejected;
	private int accepted;
	private int numFast;
	private int numSlow;
	private int freeFast;
	private int freeSlow;
	private double totalQueueTime;
	
	
	public CarWashState(){
		slow = new Vector<CarWash>();
		fast = new Vector<CarWash>();
		factory = new CarFactory();
		carQueue = new FIFO();
		start();
	}
	/**
	 * Sets current time
	 * @param double to set current time to.
	 * */
	public void setCurrentTime(double time){
		currentTime = time;
	}
	/**
	 * Adds queue time to the total queue time.
	 * */
	public void addTotalQueueTime(double time){
		totalQueueTime += time;
	}
	/**
	 * Creates CarWashes
	 * @param s number of slow carwashes to create.
	 * @param f number of fast carwashes to create.
	 * */
	public void createWashes(int s, int f){
		numFast = f;
		freeFast = f;
		numSlow = s;
		freeSlow = s;
		//Creates slow machines
		for (int i = 0; i < s; i++){
			CarWash wash = new CarWash(slowRandom);
			slow.addElement(wash);
		}
		//Creates fast machines
		for (int e =0; e < f; e++){
			CarWash wash = new CarWash(fastRandom);
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
		carQueueSize++;
		accepted++;
	}
	/**
	 * Removes first car in the queue.
	 * */
	public Car removeQueue(){
		Car removed = carQueue.getFirst();
		carQueue.removeFirst();
		carQueueSize--;
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
	
	///Below you find statistics to print/use.
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
	 * Gets the maximum value used in random number generator for slow carwashes.
	 * @return max value for number generator used for slow carwashes.
	 * */
	public double getSlowMax(){
		return slowMax;
	}
	/**
	 * Gets the minimum value used in random number generator for slow carwashes.
	 * @return minimum value for number generator used for slow carwashes.
	 * */
	public double getSlowMin(){
		return slowMin;
	}
	/**
	 * Gets the maximum value used in random number generator for fast carwashes.
	 * @return max value for number generator used for fast carwashes.
	 * */
	public double getFastMax(){
		return fastMax;
	}
	/**
	 * Gets the minimum value used in random number generator for fast carwashes.
	 * @return minimum value for number generator used for fast carwashes.
	 * */
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
		Vector<Car>queue = carQueue.getCarq();
		for (Car car : queue){
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
	/**
	 * Adds one to the number of rejected cars
	 * */
	public void reject(){
		rejected++;
	}
	/**
	 * Returns number of rejected cars.
	 * @return number rejected cars.
	 * */
	public int getRejected(){
		return rejected;
	}
	/**
	 * Returns the size of the car queue.
	 * @return size of car queue.
	 * */
	public int getCarQueueSize(){
		return carQueueSize;
	}
	/**
	 * Returns the sum of time all machines has been idle.
	 * @return double idle time
	 * */
	public double getTotalIdleCarWash(){
		double idleTime = 0;
		for (CarWash carwash : fast){
			idleTime += carwash.getIdle();
		}
		for (CarWash carwash : slow){
			idleTime += carwash.getIdle();
		}
		return idleTime;
	}

}