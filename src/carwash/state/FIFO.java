package carwash.state;
import java.util.Vector;

public class FIFO {
private Vector<Car> carQ;

/**
 * Creates a FIFO queue by making a vector that holds Car objects.
 * */
public FIFO(){
	carQ = new Vector<Car>();	
}
/**
 * Adds an Car object to the FIFO vector.
 * */
public void add(Car i){
	carQ.addElement(i);
}
/**
 * 
 * @return Returns size of the FIFO vector.
 */
public int getSize(){
	return carQ.size();
}
/**
 * Check if FIFO is empty, if not returns Car object at first index.
 * @return Returns the first element in the FIFO.
 * */
public Car getFirst(){
		return carQ.elementAt(0);
	}
/**
 * Removes first object in FIFO
 * */
public void removeFirst(){
		carQ.remove(0);
	}
}
