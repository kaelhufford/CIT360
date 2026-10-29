package Queues;

/**
 * A fixed-capacity FIFO queue backed by a circular array.
 * Enqueue, dequeue, peek, size, isEmpty, and isFull run in O(1) time.
 *
 * @param <E> the type of item stored in the queue
 */
public class QueueA<E> implements QueueI<E> {
    private E[] queue;
    private int front;
    private int size;

    /** Creates a queue with the default capacity. */
    public QueueA() {
        this(DEFAULT_CAPACITY);
    }

    /** Creates a queue with the specified capacity. */
    @SuppressWarnings("unchecked")
    public QueueA(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero!.");
        }
        queue = (E[]) new Object[capacity];
    }

    /** Adds an item to the back, or prints an error if full. */
    @Override
    public void enqueue(E obj) {
        if (isFull()) {
            System.err.println("Capacity reached in queue!");
            return;
        }

        int backIndex = (front + size) % queue.length;
        queue[backIndex] = obj;
        size++;
    }

    /** Removes and returns the front item, or null if empty. */
    @Override
    public E dequeue() {
        if (isEmpty()) {
            System.err.println("Queue is empty!");
            return null;
        }

        E item = queue[front];
        queue[front] = null;
        front = (front + 1) % queue.length;
        size--;
        return item;
    }

    /** Returns the front item without removing it, or null if empty. */
    @Override
    public E peek() {
        if (isEmpty()) {
            System.err.println("Queue is empty!");
            return null;
        }
        return queue[front];
    }

    /** Returns the number of items currently stored. */
    @Override
    public int size() {
        return size;
    }

    /** Returns whether the queue contains no items. */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns whether the queue has reached its capacity. */
    @Override
    public boolean isFull() {
        return size == queue.length;
    }

    /** Returns the backing array contents and queue state for debugging. */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (E item : queue) {
            result.append(item).append(':');
        }
        result.append("\n Front: ").append(front);
        result.append("\n Size: ").append(size);
        return result.toString();
    }
}
