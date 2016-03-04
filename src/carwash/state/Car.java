package carwash.state;

public class Car {
	private int id; //Stores id for the Car
	private double arriveTime; //Stores time when car arrived
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
	public void setArrive(double arriveTime){
		this.arriveTime = arriveTime;
	}
	/**
	 * Returns the arrive time of the car.
	 * @return Arrive Time
	 * */
	public double getArrive(){
		return this.arriveTime;
	}

}
