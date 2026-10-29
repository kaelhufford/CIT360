package AbstractDataTypes.LinkedLists;

import java.util.Objects;

public class LinkedList<E> implements ListI<E> {
	private class Node {
		private E data;
		private Node next;

		private Node(E obj) {
			data = obj;
			next = null;
		}
	}

	private Node head;
	private Node tail;
	private int currentSize = 0;

	@Override
	public long size() {
		return currentSize;
	}

	@Override
	public void addFront(E obj) {
		Node newNode = new Node(obj);
		newNode.next = head;
		head = newNode;
		if (currentSize == 0) {
			tail = newNode; // A list with one node has the same as the tail and head
		}
		currentSize++;
	}

	public E getFront() {
		if (head == null) {
			return null;
		}
		return head.data;
	}

	@Override
	public void addBack(E obj) {
		if (head == null) {
			addFront(obj);
			return;
		}

		Node newNode = new Node(obj);
		// Since I have tail use, I just add to the back using that
		tail.next = newNode;
		tail = newNode;
		currentSize++;
	}

	@Override
	public E removeFront() {
		if (head == null) {
			return null;
		}

		E data = head.data;
		head = head.next;
		currentSize--;

		if (head == null) {
			tail = null;
		}
		return data;
	}

	@Override
	public E removeBack() {
		if (head == null) {
			return null;
		}
		if (currentSize == 1) {
			return removeFront();
		}

		Node current = head;
		while (current.next != tail) {
			current = current.next;
		}

		E data = tail.data;
		current.next = null;
		tail = current;
		currentSize--;
		return data;
	}

	@Override
	public E get(long n) {
		if (n < 0 || n >= currentSize) {
			return null;
		}

		Node current = head;
		for (long i = 0; i < n; i++) {
			current = current.next;
		}
		return current.data;
	}

	@Override
	public long indexOf(E obj) {
		Node current = head;
		long position = 0;

		while (current != null) {
			if (Objects.equals(current.data, obj)) {
				return position;
			}

			current = current.next;
			position++;
		}
		return -1;
	}

	@Override
	public boolean contains(E obj) {
		return indexOf(obj) != -1;
	}

	@Override
	public String toString() {
		Node current = head;
		String str = "(" + currentSize + ") || Head -> ";
		while (current != null) {
			str += current.data + " |---> ";
			current = current.next;
		}
		return str + "null ||";
	}
}

