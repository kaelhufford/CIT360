package Activity0;

import java.util.Random;

import javax.swing.plaf.TreeUI;

public class ArrayOperations {
    /** printArray - Takes in an array to print it index by index.
     */
    public static void printArray(int[] array){
        for (int i = 0; i < array.length; i++){
            System.out.println("[" + i + "] - " + array[i]);
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
        Random random = new Random();
        for (int i = 0; i < length; i++){
            randomArray[i] = Math.round(random.);
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
}