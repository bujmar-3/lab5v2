package simulator.event; 
 
import java.util.Vector; 

/** 
 * The class defines a sorted sequence. It keeps all element sorted by requiring that all elements should be of the same type and implements the interface comparable. 
 * It is designed so that it only will remove or return the first element in the sequence. 
 * @param <E> The type of the elements. Requires that E implements the interface comparable 
 */ 
	public class SortedSequence { 
		private Vector<Event> seq = new Vector<Event>(); 
 
 
 	/** 
 	 * Inserts an element into the sorted sequence 
 	 * @param o the object to be inserted. must be of type E 
 	 */ 
 	public void insert(Event o) { 
 		if (seq.isEmpty()) 
 		{ 
 			seq.add(o); 
 		} 
 		else 
 		{ 
 			int i = 0;
 			for (Event event : seq){
 				if (o.getTime() < event.getTime()) 
 				{
 					seq.add(i,o);
 					return;
 				}
 				i++;
 			}
 			seq.add(o);
 		} 
 	} 
 
 	/** 
 	 * Method returning the number of elements in the sorted sequence 
 	 * @return the number of elements in the sorted sequence 
 	 */ 
 	public int size() { 
 		return seq.size(); 
 	} 
 
 	/** 
 	 * Method removing the first element in the sorted sequence 
 	 */ 
 	public void removeFirst() { 
 		if (seq.size() > 0) { 
 			seq.removeElementAt(0); 
 		} 
 	} 
 
 	/** 
 	 * Method returning the first element of the sorted sequence 
 	 * @return the first element of the sorted sequence. it will be of type E 
 	 */ 
 	public Event first() { 
 		if (seq.size() > 0)
 		{
 			return seq.elementAt(0);
 		}
 		else
 		{
 			return null; 
 		}
 	} 
 } 