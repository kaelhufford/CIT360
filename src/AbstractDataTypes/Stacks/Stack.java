package AbstractDataTypes.Stacks;

public class Stack<E> implements StackI<E> {
	
	
	/**
	 *? _______                                _______                     _______
	 *? |_____|                                |__5__| <-- Top             |__5__| <-- Values stays, but gets overridden when push(E) is calles
	 *? |__5__| <-- Top                        |__7__|                     |__7__| <-- Top
	 *? |__7__| <--- Stack | After push(0) --> |__1__|     After pop() --> |__1__|
	 *? |__1__|                                |__8__|                     |__8__|
	 *? |__8__|                                |__0__|                     |__0__|
	 *? Stacks follow an LIFO(Last in first out)
	 */


	private E[] arr;     // Array being used
	private int top;     // Pointer to the top of the stack
	private int capacity;// Capacity of arry

	public Stack(){
		this(32); // Call other constructor that uses arg for less space
	}

	@SuppressWarnings("unchecked")
	public Stack(int cap){
		if (cap <= 0) {
			throw new IllegalArgumentException("Capacity must be greater than zero.");
		}
		this.capacity = cap;
		this.arr = (E[]) new Object[this.capacity]; // Casts the array object to the type to what is passed through.
		this.top = -1;
	}

	@Override
	public void push(E obj) {
		if(isFull()) {
			throw new IllegalStateException("Stack capacity exceeded.");
		}

		// Move top to new spot, then use it
		arr[++top] = obj;

	}

	@Override
	public E pop() {
		if (isEmpty()) { return null; }
		E item = arr[top];
		arr[top--] = null;
		return item;
	}

	@Override
	public E peek() {
		if(isEmpty()) {return null;}
		return arr[top];
	}

	@Override
	public int size() {
		return top + 1;
	}

	@Override
	public int capacity() {
		return capacity;
	}

	@Override
	public boolean isEmpty() {
		return (top == -1);
	}

	@Override
	public boolean isFull() {
		return (top == capacity - 1);
	}

	@Override 
	public String toString(){
		String str = "---------\n";
		for (int i = top; i > -1; i--){
			str += "| " + arr[i] + " |\n";
		}

		return str + "---------";
	} 

	
	
}

