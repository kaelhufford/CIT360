package Queues;

import java.util.Objects;

/** Demonstrates FIFO behavior and checks queue boundary conditions. */
public class QueueDemo {
    public static void main(String[] args) {
        QueueA<String> queue = new QueueA<>(3);

        check("Initial size", 0, queue.size());
        check("Initially empty", true, queue.isEmpty());
        check("Initially not full", false, queue.isFull());
        check("Peek on empty queue", null, queue.peek());
        check("Dequeue on empty queue", null, queue.dequeue());
        check("Empty operations preserve size", 0, queue.size());

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        check("Full size", 3, queue.size());
        check("Full queue", true, queue.isFull());
        check("Peek returns first item", "A", queue.peek());
        check("Peek preserves size", 3, queue.size());

        queue.enqueue("Overflow");
        check("Overflow preserves size", 3, queue.size());
        check("Overflow preserves front", "A", queue.peek());

        check("First removal", "A", queue.dequeue());
        check("Removal decreases size", 2, queue.size());
        check("Removal frees capacity", false, queue.isFull());
        queue.enqueue("D");
        check("Wraparound restores full size", 3, queue.size());
        check("Second removal", "B", queue.dequeue());
        check("Third removal", "C", queue.dequeue());
        check("Wrapped item", "D", queue.dequeue());
        check("Drained size", 0, queue.size());
        check("Drained queue is empty", true, queue.isEmpty());
        check("Drained queue is not full", false, queue.isFull());
        check("Peek after draining", null, queue.peek());
        check("Dequeue after draining", null, queue.dequeue());

        queue.enqueue("E");
        check("Reuse after draining", "E", queue.dequeue());
        check("Reuse ends empty", true, queue.isEmpty());

        QueueA<Integer> singleItemQueue = new QueueA<>(1);
        for (int value = 0; value < 5; value++) {
            singleItemQueue.enqueue(value);
            check("Capacity one is full", true, singleItemQueue.isFull());
            check("Capacity one peek", value, singleItemQueue.peek());
            singleItemQueue.enqueue(-1);
            check("Capacity one rejects overflow", 1, singleItemQueue.size());
            check("Capacity one removal", value, singleItemQueue.dequeue());
            check("Capacity one is empty", true, singleItemQueue.isEmpty());
            check("Capacity one size", 0, singleItemQueue.size());
            check("Capacity one is not full", false, singleItemQueue.isFull());
        }

        QueueA<String> defaultQueue = new QueueA<>();
        for (int index = 0; index < QueueI.DEFAULT_CAPACITY; index++) {
            defaultQueue.enqueue("Item " + index);
        }
        check("Default capacity", QueueI.DEFAULT_CAPACITY, defaultQueue.size());
        check("Default queue is full", true, defaultQueue.isFull());
        System.out.println("All queue checks passed.");
    }

    /** Fails immediately if an observed result differs from its expectation. */
    private static void check(String description, Object expected, Object actual) {
        if (!Objects.equals(expected, actual)) {
            throw new AssertionError(description + ": expected " + expected
                    + ", but got " + actual);
        }
        System.out.println("PASS: " + description);
    }
}
