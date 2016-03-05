package simulator.state;
import java.util.Observable;

public class SimState extends Observable {
	private boolean started;
	
	/**
	 * Sets the simulation state to started.
	 * */
	public void start(){
		started = true;
	}
	/**
	 * Sets the simulation state to stopped.
	 * */
	public void stop(){
		started = false;
	}
	/**
	 * Returns the state of the simulation
	 * @return Boolean, true if simulation started, false if simulation is stopped.
	 * */
	public boolean state(){
		return started;
	}
}
