package AbstractDataTypes.Stacks;

import AbstractDataTypes.LinkedLists.LinkedList;

public class StackL<E> implements StackI<E> {
	private LinkedList<E> list;
	private int capacity;

	public StackL() {
		this(32);
	}

	public StackL(int cap) {
		if (cap <= 0) {
			throw new IllegalArgumentException("Capacity must be greater than zero.");
		}
		capacity = cap;
		list = new LinkedList<>();
	}

	@Override
	public void push(E obj) {
		if (isFull()) {
			throw new IllegalStateException("Stack capacity exceeded.");
		}
		list.addFront(obj);
	}

	@Override
	public E pop() {
		return list.removeFront();
	}

	@Override
	public E peek() {
		return list.getFront();
	}

	@Override
	public int size() {
		return (int) list.size();
	}

	@Override
	public int capacity() {
		return capacity;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public boolean isFull() {
		return size() == capacity;
	}

	@Override
	public String toString() {
		return list.toString();
	}
}

