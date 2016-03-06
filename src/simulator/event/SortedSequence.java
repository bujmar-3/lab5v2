package simulator.event;

import java.util.Vector;

/**
 * We used this class before we found PriorityQueue. Obsolete.
 * Code based on lecture 13.
 * @param <E>
 */
public class SortedSequence<E extends Comparable<E>> {
	private Vector<E> seq = new Vector<E>();
	
    public void add(E item) throws IllegalArgumentException {
    	if (item == null) {
    		throw new NullPointerException();
    	}
        if (seq.size() == 0) {
        	seq.add((E) item);
        } else {
            if (item.compareTo(first()) == -1) {
                // item is smaller than the smallest item in the sequence, 
            	// so add it first
                seq.add(0, item);
            } else { // item is not smallest
                E smallest = first(); // save the smallest
                seq.removeElementAt(0); // remove it
                add(item);  // recursive call to insert item
                seq.add(0, smallest);   // re-insert smallest first
            }
        }
    }

	public E first() { //returns the first element in seq 
		return seq.firstElement();
	}
	
	public void pop() { //removes the first element in seq
		seq.remove(0);
	}
	
	public int size() { //returns the size of seq
		return seq.size();
	}
}
