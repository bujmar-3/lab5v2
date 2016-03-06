package carwash.state;

public class CarWash {
private boolean hasCar;
private Car car;
private double idleTime;
private int speed; // Speed of the CarWash 1 for fast 0 for slow.
private int state = 0;

/**
 * Creates a new CarWash
 * @param int speed of the machine 1 for fast 0 for slow
 * */
public CarWash(int speed){
	this.speed=speed;
	hasCar = false;
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
public double getIdle(){
	return this.idleTime;
}
/**
 * Returns the speed of the carwash.
 * @return Int, returns the speed of the CarWash, 1 for fast 0 for slow.
 * */
public int getSpeed(){
	return this.speed;
}



}
