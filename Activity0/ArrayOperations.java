package Activity0;

import java.util.Arrays;
import java.util.Random;

public class ArrayOperations {
    public static void printArray(int[] array){
        System.out.println(Arrays.toString(array));
    }   

    public static boolean isArrayEmpty(int[] array){
        if (array.length == 0 || array == null){
            return true;
        } else {
            return false;   
        }
    }

    public static int[] copyArray(int[] array){
        int[] copyOfArray = {};
        for (int i = 0; i < array.length; i++){
            copyOfArray[i]=  array[i];
        }
        return copyOfArray;
    }

    public static boolean areArraysEqual(int[] arrayOne, int[] arrayTwo){
        if (arrayOne == arrayTwo){
            return true;
        } else {
            return false;
        }
    }

    public static int[] generateIntArray(int length, int max){
        int[] randomArray = {};
        Random random = new Random();
        for (int i = 0; i < length; i++){
            randomArray[i] = random.nextInt(max);
        }   
        return randomArray;
    }

    public static int findMaxInArray(int[] array){
        int maxValue = array[0];
        for (int i = 0; i < array.length; i++){
            if (array[i] > maxValue){maxValue = array[i];}
        }
        return maxValue;
    }

    public static int findMinInArray(int[] array){
        int minValue = array[0];
        for (int i = 0; i < array.length; i++){
            if (array[i] < minValue){minValue = array[i];}
        }
        return minValue;
    }

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