import Activity0.ArrayOperations;

public class Main {
    /**
     * public - access spec
     * static - def of function
     * void - return type
     * main - method name
     * @param args - ...args...
     */
    public static void main(String[] args) {
        int[] arr1 = ArrayOperations.generateIntArray(10 ,10);

        ArrayOperations.printArray(arr1);

        MergeSort ms = new MergeSort(arr1);

        System.out.println("Done sorting");
        ArrayOperations.printArray(arr1);
   
    }
}
