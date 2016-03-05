package carwash.state;

public class CarWash {
private int gotCar = 0;
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
}
/**
 * Returns true if the Carwash got a car otherwise false.
 * @return boolean
 * */
public boolean gotCar(){
	if (gotCar == 1){
		return true;
	}else{return false;}
}
/**
 * Adds Car to the CarWash.
 * @param Car
 * */
public void addCar(Car i){
	this.car = i;
}
/**
 * Removes the car for the CarWash.
 * */
public void removeCar(){
	this.car = null;
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



}
