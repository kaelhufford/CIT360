
public class HeapDemo {
    public static void main(String[] args) {
        MaxHeap heap = new MaxHeap(10);
        int[] els = {5, 5, 8, 2, 6, 10, 59, 456, 487, 24, 14};

        for (int t : els){
            heap.insert(t);
        }

        System.out.println(heap);

        while (heap.size() != 0) {
            System.out.print(heap.remove() + " ");
        }
    }
}
