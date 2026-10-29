package AbstractDataTypes.Stacks;

public class StackADemo{
	public static void main(String[] arg){
		Stack<Integer> numbers = new Stack<>(5);

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
