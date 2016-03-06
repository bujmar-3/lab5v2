package simulator.event;

import java.util.PriorityQueue;

/**
 * This class contains events, sorted by their time.
 * The sorting makes sure the events are run in proper order.
 * 
 */
public class EventQueue {
	private PriorityQueue<Event> queue;

	public EventQueue() {
		queue = new PriorityQueue<Event>();
	}
	
	/**
	 * Constants 
	 * Adds a event to the queue.
	 * @param event
	 */
	public void add(Event event) {
		queue.add(event);
	}
	
	/**
	 * returns head of queue and removes it from the queue.
	 * @return Head of the queue
	 */
	public Event poll() {
		return queue.poll();
	}
	
	/**
	 * 
	 * @return True if the queue is empty
	 */
	public boolean empty() {
		return queue.isEmpty();
	}


}
