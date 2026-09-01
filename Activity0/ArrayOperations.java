package Activity0;


public class ArrayOperations {
    /** printArray - Takes in an array to print it index by index.
     */
    public static void printArray(int[] array){
        for (int i = 0; i < array.length; i++){
            System.out.print(array[i] + " - ");
        }
    }   

    /** isArrayEmpty - Takes an array to tell if the given array is empty or null
     */
    public static boolean isArrayEmpty(int[] array){
        return array.length == 0 || array == null;
    }

    /**copyArray - Returns a copy of the given array
     */
    public static int[] copyArray(int[] array){
        int[] copyOfArray = new int[array.length];
        for (int i = 0; i < array.length; i++){
            copyOfArray[i] = array[i];
        }
        return copyOfArray;
    }

    /**areArraysEqual - Returns T/F if the two given arrays are index for index equal
     */
    public static boolean areArraysEqual(int[] arrayOne, int[] arrayTwo){

        boolean equal = true;

        try {
            for (int i = 0; i < arrayOne.length; i++){
                if (arrayOne[i] != arrayTwo[i]){ equal = false; break; }
            }
            return equal;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }

    }

    /** generateIntArray - Returns a randomly generated array based off of the length
     */
    public static int[] generateIntArray(int length, int max){
        int[] randomArray = new int[length];
        for (int i = 0; i < length; i++){
            randomArray[i] = (int) Math.round(Math.random() * max);
        }   
        return randomArray;
    }

    /** findMaxInArray - Returns the max value in the array
     */
    public static int findMaxInArray(int[] array){
        int maxValue = array[0];
        for (int i = 0; i < array.length; i++){
            if (array[i] > maxValue){maxValue = array[i];}
        }
        return maxValue;
    }

    /** findMinInArray - Returns the min value in the given array
     */
    public static int findMinInArray(int[] array){
        int minValue = array[0];
        for (int i = 0; i < array.length; i++){
            if (array[i] < minValue){minValue = array[i];}
        }
        return minValue;
    }

    /** removeDuplicatesFromArray - Returns an array that has all copies of the given array removed
     */
    public static int[] removeDuplicatesFromArray(int[] array){
        int flagValue = -999999991;
        int[] clonedArray = array.clone();
        int decreasedSize = 0;

        for (int i = 0; i < array.length; i++ ){
            boolean firstOccuranceHappened = false;
            for (int ci = 0; ci < clonedArray.length; ci++){
                if (clonedArray[ci] == array[i] && firstOccuranceHappened){
                    // Replace value with a flagValue to remove later
                    clonedArray[ci] = flagValue;
                    decreasedSize++;
                } else if(clonedArray[ci] == array[i] && !firstOccuranceHappened){
                    firstOccuranceHappened = true;
                }
            }
        }

        int[] noDupeArray = new int[(clonedArray.length - decreasedSize)];
        int noDupeIndex = 0;
        for (int i = 0; i < clonedArray.length; i++){
            if(clonedArray[i] != flagValue){
                noDupeArray[noDupeIndex] = clonedArray[i];
                noDupeIndex++;
            }
        }
        return noDupeArray;
    }

    /** exists - Checks if a(Array) contains q(Querey) inside it.
     * @param a - Array being searched through
     * @param q - Query, whats being looked for
     * @return T/F if q exists
    */
    public static boolean exists(int[] a, int q){
        for (int i = 0; i < a.length; i++){
            if (a[i] == q) { return true; }
        }

        return false;
    }

    //! SimpleSorting!!

    /**bubbleSort - Sorts the array using a buble sort */
    public static void bubbleSort(int[] a){
        for (int i = 0; i < a.length-1; i++){ // "a.length-1" because we dont want a ArrayOutOfIndex
            for (int j = 0; j < a.length - i - 1; j++){ // "a.length - i - 1" We dont want to pass what has already been changed
                if (a[j] > a[j+1]){
                    swap(a, j+1, j);
                }
            }
        }
    }

    /**selectionSort - Sorts the array using a selectionSort */
    public static void selectionSort(int[] a){
        for (int i = 0; i < a.length; i++){
            //Index of the smallest value found
            int jMin = i;

            // Loop through array and record the smallest values index
            for (int j = i + 1; j < a.length; j++){
                if (a[j] < a[jMin]){
                    jMin = j;
    
                }
                swap(a, jMin, j);
            }
        }
    }

    /** insertionSort - Sorts given array using insertion sort method */
    public static void insertionSort(int[] a){
        for (int i = 0; i < a.length; i++){
            int current = a[i];
            int j = i - 1;
            
            // Walk back through array and shift elements until we get a smaller element
            while (j >= 0 && a[j] > current) {
                a[j+1] = a[j];
                j--;
            }
            
            a[j+1] = current;

        }
    }

    public static void swap(int[] a, int from, int to){
        int tmp = a[from];
        a[from] = a[to]; 
        a[to] = tmp;
    }

}