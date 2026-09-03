public class MergeSort {
    private int[] tmp, array; //GLOBALLY SCOPED 

    // CONSTRUCTORS
    MergeSort(int[] arr){
        this.array = arr;
        this.tmp = new int[arr.length];

        split(0, array.length-1);
    }

    // private --> access specifier
    private void split(int low, int high) {
        // Base case
        if (low == high){
            return;
        }

        int mid = (low + high) / 2;

        split(low, mid);
        split(mid + 1, high);

        merge(low, mid, high);
    }

    private void merge(int low, int mid, int high){
        int i = low;
        int j = mid + 1;
        int tmpPos =  low;

        while (i <= mid && j <= high){
            if (array[i] <= array[j]){
                tmp[tmpPos++] = array[i++]; //Post-fixed incrementer || Use first THEN adds to vars
                //tmpPos++; Comemented out due to post-fixed incrementer
                //i++; Comemented out due to post-fixed incrementer
            } else {
                tmp[tmpPos++] = array[j++];
                //tmpPos++; Comemented out due to post-fixed incrementer
                //j++; Comemented out due to post-fixed incrementer
            }

        }

        // Run out rest of left
        while (i <= mid){
            tmp[tmpPos++] = array[i++];
        }
        // Run out rest of right
        while (j <= high){
            tmp[tmpPos++] = array[j++];
        }

        // Put into original array its now sorted
        for (tmpPos = low; tmpPos <= high; tmpPos++){
            array[tmpPos] = tmp[tmpPos]; 
        }

    }

    
}