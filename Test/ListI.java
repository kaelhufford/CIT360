package Test;

public interface ListI<E> {
	/**
	 * Adds an element to the front of the List.
	 * @param obj
	 */
	void addFront(E obj);
    
	
	/**
	 * Appends an element to the end of the List.
	 * @param obj
	 */
	void addBack(E obj);
	
	/**
	 * Removes the first elements of the list 
	 * and returns the data associated with that node.
	 * @return
	 */
	E removeFront();
	
	/**
	 * Removes the last element of the LinkedList and 
	 * returns data in that node.
	 * @return
	 */
	E removeBack();
	
	/**
	 * Returns the data associated with node n.
	 * @param n
	 * @return
	 */
	E get(long n);

	/**
	 * Returns the position of an element in the list. -1 if it is not found
	 * @param obj
	 * @return
	 */
	long indexOf(E obj);
	
	/**
	 * Tests whether an obj is contained in the List.
	 * @param obj
	 * @return
	 */
	boolean contains(E obj);
	
	/**
	 * Returns the length of the list.
	 * @return
	 */
	long size();
	
}

