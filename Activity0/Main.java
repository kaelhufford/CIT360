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
        int[] testArray= {1,1,3,4,2,6,8,2,5,2,1,12,8,1819};

        ArrayOperations.printArray(testArray);
        System.out.println(ArrayOperations.isArrayEmpty(testArray));
        ArrayOperations.printArray(ArrayOperations.copyArray(testArray));
        System.out.println(ArrayOperations.areArraysEqual(testArray, testArray));
        ArrayOperations.printArray(ArrayOperations.generateIntArray(5, 10));
        System.out.println(ArrayOperations.findMaxInArray(testArray));
        System.out.println(ArrayOperations.findMinInArray(testArray));
        ArrayOperations.printArray(ArrayOperations.removeDuplicatesFromArray(testArray));
   
    }
}
