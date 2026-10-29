package Queues;

public interface QueueI<E> {
	/**
	 * Default size of the queue.
	 */
	int DEFAULT_CAPACITY = 10;
	
	/**
	 * Insert an item into the queue at the back of the queue. 
	 * If items have a  priority be sure to implement a proper
	 * insertion into the correct place.
	 * 
	 * If the queue is full print an error message
	 * 
	 * @param obj
	 */
	void enqueue(E obj);
	
	/**
	 * Return and remove the front item of the queue. If items 
	 * have a priority, this item will be the highest priority.
	 * 
	 * If the queue is empty return null.
	 * 
	 * @return E object in front of the queue.
	 */
	E dequeue();
	
	/**
	 * Return the item in the front of the queue without removal.
	 * 
	 * If the queue is empty return null.
	 * 
	 * @return
	 */
	E peek();
	
	/**
	 * Returns the number of items in the queue. Zero is an empty queue
	 * 
	 * @return
	 */
	int size();
	
	/**
	 * Return whether the queue is empty or not. 
	 * 
	 * @return
	 */
	boolean isEmpty();
	
	/**
	 * Return whether the queue is full or not. This will be
	 * 
	 * @return
	 */
	boolean isFull();
}
