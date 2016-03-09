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
	private int rejected;
	private int accepted;
	private int numFast;
	private int numSlow;
	private int freeFast;
	private int freeSlow;
	private double totalQueueTime;
	private double totalIdleTime;
	
	
	public CarWashState(){
		slow = new Vector<CarWash>();
		fast = new Vector<CarWash>();
		factory = new CarFactory();
		carQueue = new FIFO();
		start();
	}
	/**
	 * Sets current time for the simulation.
	 * @param double to set current time to.
	 * */
	public void setCurrentTime(double time){
		currentTime = time;
		System.out.println("T: Set currentime to " + currentTime);
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
		System.out.println("Created fast " + f);
		System.out.println("Created slow " + s);
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
		System.out.println("setmaxsize to " + size);
	}
	
	/**
	 * Adds car to the car queue.
	 * @param car
	 * */
	public void addQueue(Car car){
		System.out.println("QueueSize: " + getCarQueueSize());
		carQueue.add(car);
		accepted++;
		System.out.println("Q: added car to queue, car id: " + car.getId());
	}
	/**
	 * Removes first car in the car queue.
	 * @return car that is first in car queue.
	 * */
	public Car removeQueue(){
		Car removed = carQueue.getFirst();
		System.out.println("Q: removed car from queue, car id: " + removed.getId());
		carQueue.removeFirst();
		return removed;
	}
	/**
	 * Adds Car to CarWash
	 * @param car to add.
	 * @return Returns time when car will be finished in wash
	 * */
	public double addWash(Car car){
		System.out.println("freeFast: " + freeFast);
		System.out.println("freeSlow: " + freeSlow);
		System.out.println("QueueTime: " + getTotalQueueTime());
		System.out.println("Idle: " + totalIdleTime);
		double washTime = 0;
		boolean foundWash = false;
		for (CarWash wash : fast){
			if(wash.gotCar()==false){
				wash.addCar(car);
				foundWash = true;
				washTime = wash.timeToWash();
				freeFast--;
				System.out.println("W: added car to fast wash, car id: " + wash.getCar().getId());
				break;
				}
			}
		if (!foundWash){
			for (CarWash wash : slow){
				if(wash.gotCar()==false){
					wash.addCar(car);
					washTime = wash.timeToWash();
					freeSlow--;
					System.out.println("W: added car to slow wash, car id: " + wash.getCar().getId());
					break;
				}
			}
		}
		//Time car will be finished washing used to create leave event for the car.
		return currentTime + washTime;
	}
	/**
	 * Removes specific car from the carwash.
	 * @param car to remove from the carwash.
	 * */
	public void removeWash(Car car){
		boolean carFound = false;
		for (CarWash wash : fast){
			if (wash.getCar()==car){
				wash.removeCar();
				carFound = true;
				freeFast++;
				System.out.println("W: removed car from wash with id: " + car.getId());
				break;
			}
		}
		if (carFound == false){
			for (CarWash spot : slow){
				if (spot.getCar()==car){
					spot.removeCar();
					freeSlow++;
					System.out.println("W: removed car from wash with id: " + car.getId());
					break;
				}
			}
		}
	}
	/**
	 * Increases the total queue time for carqueue and the total idle time for carwashes.
	 * @param time next event will occur
	 * */
	//Takes the time next event will occur minus the time before the event occured and multiplies it with the amount of carwashes that has been idle.
	//Takes the time next event will occur minus the time before the event occured and multiplies it with the amount of cars that has been in queue.
	public void incTotalTimes(double eventTime){
		totalIdleTime += ((eventTime - currentTime)*(freeFast + freeSlow));
		totalQueueTime += ((eventTime - currentTime)*getCarQueueSize());
	}
	/**
	 * Returns the carfactory that are used to create the cars.
	 * @return CarFactory
	 * */
	public CarFactory getCarFactory(){
		System.out.println("Created car");
		return factory;
	}
	
	/**
	 * Creates random streams.
	 * @param fastMax The max distribution time for fast machines.
	 * @param fastMin The minimum distribution time for fast machines.
	 * @param slowMax The max distribution time for slow machines. 
	 * @param slowMin The minimum distribution time for slow machines.
	 * @param lambda Lambda used for number generator.
	 * @param seed Seed used for number generator.
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
		System.out.println("created streams");
	}
	
	///Below you find statistics to print/use.
	/**
	 * Gets seed used in number generator
	 * @return seed
	 * */
	public long getSeed(){
		return seed;
	}
	/**
	 * Gets lambda used in number generator
	 * @return lambda
	 * */
	public double getLambda(){
		return lambda;
	}
	/**
	 * Gets the maximum value used in random number generator for slow carwashes.
	 * @return double
	 * */
	public double getSlowMax(){
		return slowMax;
	}
	/**
	 * Gets the minimum value used in random number generator for slow carwashes.
	 * @return double.
	 * */
	public double getSlowMin(){
		return slowMin;
	}
	/**
	 * Gets the maximum value used in random number generator for fast carwashes.
	 * @return double
	 * */
	public double getFastMax(){
		return fastMax;
	}
	/**
	 * Gets the minimum value used in random number generator for fast carwashes.
	 * @return double
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
	 * Adds one to the number of rejected cars.
	 * */
	public void reject(){
		System.out.println("QueueTime: " + getTotalQueueTime());
		System.out.println("Idle: " + totalIdleTime);
		System.out.println("rejected car");
		rejected++;
	}
	/**
	 * Returns number of rejected cars.
	 * @return int
	 * */
	public int getRejected(){
		return rejected;
	}
	/**
	 * Returns the current size of the car queue.
	 * @return int
	 * */
	public int getCarQueueSize(){
		return carQueue.getSize();
	}

	/**
	 * Returns the time carwashes has been idle.
	 * @return double
	 * */
	public double getTotalIdleTime(){
		return totalIdleTime;
	}
}