package carwash.state;
/**
 * Creates Cars
 * **/
public class CarFactory {
	private int newId = 0;
	
	/**
	 * Creates a new car with a unique id in form of an int starting at 0 and returns it.
	 * Next car will have the old id+1 as id.
	 * @return Car.
	 * */
	public Car createCar(){
		Car newCar = new Car(newId);
		newId++;
		return newCar;
	}

}
