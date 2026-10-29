public class MaxHeap{

    private int[] heap;
    private int size;

    MaxHeap(){
        this(512);
    }

    MaxHeap(int maxCapacity){
        heap = new int[maxCapacity];
        size = 0;
    }

    //* Helper functions
    private int parent(int j){
        return (j-1) / 2; // Int Div
    }

    private int left(int j){
        return 2 * j + 1;
    }

    private int right(int j){
        return  2 * j + 2;
    }

    private boolean hasLeft(int j){
        return left(j) < size;
    }

    private boolean hasRight(int j){
        return right(j) < size;
    }

    private void swap(int i, int j){
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    private void trickleUp(int j){
        // While j is not at the root
        while (j > 0) {
            int p = parent(j);

            if (heap[j] < heap[p]){
                break;
            }

            swap(j, p);

            j = p;
        }
    }

    private void trickleDown(int j){
        // No left chile = no right child. While j has a left child...
        while (hasLeft(j)) {
            int leftChild = left(j);
            int largestChild = leftChild;

            if (hasRight(j)){
                int rightChild = right(j);
                // If the value of the right is larger, make it the largetsChild
                if (heap[leftChild] < heap[rightChild]) { largestChild = rightChild; }
            }

            if(heap[largestChild] < heap[j]){ break; }

            swap(largestChild, j);

            j = largestChild;

        }
    }

    public int remove(){
        if (size == 0){ return -1; }

        int tmp = heap[0];

        heap[0] = heap[--size];

        trickleDown(0);

        return tmp;
    }

    public void insert(int element){
        if (size == heap.length){
            int[] new_heap = new int[Math.max(1, heap.length * 2)]; // Create the room for more elements in a new array

            // Copy the existing elements into a new array to allow for more space.
            for(int i = 0; i < heap.length; i++){
                new_heap[i] = heap[i];
            }
            heap = new_heap;
        }

        heap[size] = element;

        trickleUp(size);
        
        size++;
    }

    public int peek(){
        if (size == 0) {return -1;}
        return heap[0];
    }

    public int size() { return size; }

    public int maxCapacity() { return  heap.length; }

    public String toString(){
        String str = "";
        for (int i = 0; i < size; i++){
            str += "[" + heap[i] + "]";
        }
        return str;
    }



}
