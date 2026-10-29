package AbstractDataTypes.Stacks;

public class StackLDemo {
	// ADDED: Same basic demonstration as StackADemo, using the linked-list stack.
	public static void main(String[] args) {
		StackL<Integer> numbers = new StackL<>(5);

		numbers.push(10);
		numbers.push(20);
		numbers.push(30);

		System.out.println(numbers);
		System.out.println("Top element: " + numbers.peek());
		System.out.println("Removed: " + numbers.pop());
		System.out.println("Size: " + numbers.size());
		System.out.println("Empty: " + numbers.isEmpty());
		System.out.println("Full: " + numbers.isFull());
	}
}
