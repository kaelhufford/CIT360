package AbstractDataTypes.Stacks;

public interface StackI<E>{
	/**
	 * Place element on top of the stack;
	 * @param obj
	 */
	void push(E obj);
	
	/**
	 * Remove and return the element on top of the stack.
	 * @return obj or null if the stack is empty
	 */
	E pop();
	
	/**
	 * Returns the element on top of the stack without removal.
	 * @return obj or null if stack is empty
	 */
	E peek();
	
	/**
	 * Returns an int that is how many elements are on the stack.
	 * @return int
	 */
	int size();
	
	/**
	 * Returns an int that represents the maximum allowable elements on the stack.
	 * @return Maximum allowable elements
	 */
	int capacity();
	
	/**
	 * Boolean representing if there is nothing on the stack.
	 * @return true if nothing is on the stack or false if there are elements on the stack
	 */
	boolean isEmpty();
	
	/**
	 * Boolean representing if our stack is full.
	 * @return true if there is no more space on our stack or false for 1 or more spaces for our stack
	 */
	boolean isFull();
}
