package Activity0;

public class Main {
    /**
     * public - access spec
     * static - def of function
     * void - return type
     * main - method name
     * @param args - ...args...
     */
    public static void main(String[] args) {
        int[] arr1 = ArrayOperations.generateIntArray(1000 ,1000);
        ArrayOperations.insertionSort(arr1);
        ArrayOperations.printArray(arr1);
   
    }
}
