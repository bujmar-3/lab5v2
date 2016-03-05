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
 * @return True if FIFO is empty otherwise false.
 * */
public boolean empty(){
	return carQ.isEmpty();
}
/**
 * Check if FIFO is empty, if not returns Car object at first index.
 * @return Returns the first element in the FIFO.
 * */
public Car getFirst(){
	if (carQ.empty()==true){
		return;
	}
	else{
		return carQ.elementAt(0);
	}
}
/**
 * Removes first object in FIFO
 * */
public void removeFirst(){
	if (carQ.isEmpty==false){
		carQ.remove(0);
	}
	else{
		return;
	}
}
/**
 * @return Returns the vector CarQ which holds car objects 
 * */
public Vector<Car> getCarq(){
	return carQ;
}

}
