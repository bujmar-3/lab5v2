package carwash.state;
import random.UniformRandomStream;

/**
 * The CarWash class used to simulate carwashes.
 * @author Martin Björklund, Arvid Persson, Emil Lilja.
 * */

public class CarWash {
private boolean hasCar;
private Car car;
private UniformRandomStream uniStream;

/**
 * The constructor, constructs a new CarWash
 * @param uniStr the UniformRandomStream used to calculate washing time.
 * */
public CarWash(UniformRandomStream uniStr){
	this.uniStream = uniStr;
	hasCar = false;
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
public void addCar(Car car){
	this.car = car;
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

}
