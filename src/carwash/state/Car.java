package carwash.state;

public class Car {
	private int id; //Stores id for the Car
	/**
	 * Creates a Car with ID.
	 * @param ID of the Car.
	 * */
	public Car(int Carid){
		this.id = Carid;
	}
	/**
	 * Returns the cars id.
	 * @return id of the car
	 * */
	public int getId(){
		return this.id;
	}
	/**
	 * Sets the arrive time for the car.
	 * @param Arrive Time
	 * */
}
