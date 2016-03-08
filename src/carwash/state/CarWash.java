package carwash.state;
import random.UniformRandomStream;

public class CarWash {
private boolean hasCar;
private Car car;
private double lastTimeUsed;
private UniformRandomStream uniStream;

/**
 * Creates a new CarWash
 * @param int speed of the machine 1 for fast 0 for slow
 * */
public CarWash(UniformRandomStream uniStr){
	this.uniStream = uniStr;
	hasCar = false;
	lastTimeUsed = 0;
}
/**
 * Returns the time it takes to wash the car
 * @return time
 * */
public double timeToWash(){
	return uniStream.next();
}
/**
 * Returns true if the Carwash got a car otherwise false.
 * @return boolean
 * */
public boolean gotCar(){
	return hasCar;
}
/**
 * Adds Car to the CarWash.
 * @param Car
 * */
public void addCar(Car i){
	this.car = i;
	hasCar = true;
}
/**
 * Removes the car for the CarWash.
 * */
public void removeCar(){
	this.car = null;
	hasCar = false;
}
/**
 * Returns the car the CarWash holds
 * @return Car
 * **/
public Car getCar(){
		return this.car;
	}
/**
 * Returns the time CarWash has been idle
 * @return Double
 * */
public double getLastTimeUsed(){
	return this.lastTimeUsed;
}



}
