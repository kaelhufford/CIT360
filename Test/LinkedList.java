package Test;

// <E> is used for generic types. Everything
public class LinkedList<E> implements ListI<E> {
    // Arrays - Declaration requires size of array
    // LinkedLists - Always the right size

    //?         |  -- NODE A --  |  |  -- NODE B --  |      |  -- NODE Z --  |
    //? Node -> [Next | data = 10], [Next | data = 20], ... [Next | data = 270] 
    // Each is a pointer to the next node representing data
    //*NOTE: Node C's node points to null

    // Always enter at the front([Node A]) 
    // Head always points to [Node A] (front-most node)

    // To get value of Node A: head.data (refers to the data of the header, in this case, of Node A- 10)
    // To get value of Node B-Z: head.next.data (refers to the heads next(in some cases the heads next next ... next))
    // - head.next.data = 20

    /* 
    NODE CLASS 
    Private inner class
    next: Node
    data: E
    */
    class Node<E>{
        public Node next; // Pointer to the next node in the linked list
        public E data;

        // Must provide an obj to make a node with data
        Node(E obj){
            next = null;
            data = obj;

        }
    }// End Node

    private Node<E> head;
    private Node<E> tail;

    private int currentSize = 0; //Done for time complexity to make constant rather than O(n)
    
    @Override 
    public long size(){
        return  currentSize;
    }

    @Override
    public void addFront(E obj) {
        Node<E> newNode = new Node<>(obj);

        newNode.next = head; // Make the new node point to what head is
        head = newNode; // Set the new head to the new entry

        currentSize++; // Increment size 
    }

    /**
     * Returns data stored in first node.
     * If empty list, returns null
     * @return Node.data if stored, otherwise returns null
     */
    public E getFront(){
        if (head == null) {return null;}
        return head.data;
    }

    @Override
    public void addBack(E obj) {

        //* Special case 1 - Empty list
        if (head == null || currentSize == 0){ 
            addFront(obj);
            return;
        }

        // Declare tmp vars
        Node<E> cur = head;
        Node<E> newNode = new Node<>(obj);

        // Iterate through to find 
        while(cur.next != null){
            cur = cur.next;
        }

        // cur.next = null
        // cur == last node

        cur.next = newNode;
        tail = newNode;
        currentSize++;

        return;
    }

    @Override
    public E removeFront() {
        if(head == null || currentSize == 0){ return null;}

        E data = head.data;
        // If Size == 1, head is the only one in the list, it's set to null.
        // Size > 1, node 1(0) is removed.
        head = head.next; 
        currentSize--;

        return data;
    }

    @Override
    //* Theta(n) && O(n) -- Theta -> exactly */
    public E removeBack() {
        if (head == null || currentSize == 0) {return  null;}     //* Special case 1 - Empty list
        if (currentSize == 1 || head.next == null) {getFront();}  //* Special case 2 - Size of 1

        Node<E> cur = head;
        Node<E> prev = null;

        while(cur.next != null){
            prev = cur;
            cur = cur.next;
        }

        // prev is at penultimate
        // curr is at last node

        prev.next = null;
        tail = prev;
        currentSize--;
    
        return cur.data;
    }

    @Override
    public E get(long n) {
        if (head == null) {return null;}

        Node<E> cur = head;

        if (n > currentSize){ return null; }

        long i = 0;
        
        while(i < n){ cur = cur.next; i++; }

        return cur.data;
    }

    /**    (non-Javadoc)
     * returns pos of found element, 0-indexed.
     * -1 if not found
     * 
     * 
     * @param E the thing you want to look for in LinkedList
     * @return int representing pos in LinkedList, -1 otherwise
     */
    @Override
    public long indexOf(E obj) {
        if (head == null) {return -1;}
        if (head == obj) {return 0;}

        Node<E> cur = head;
        long pos = 0;

        while (cur != null) { // From contains()
            if(((Comparable<E>) cur.data).compareTo(obj) == 0){
                return pos;
            }

            cur = cur.next;
            pos++;
        }

        return -1;
    }

    @Override
    public boolean contains(E obj) {
        return indexOf(obj) != -1; // != -1 means its in the list
    }
    
    @Override 
    public String toString(){
        
        Node<E> tmp = head;
        String str = "(" + currentSize + ") || Head -> ";

        // Traverse through list adding on at the end 
        while(tmp != null){
            str += tmp.data + " |---> ";
            tmp = tmp.next;
        }

        str += "null ||";

        return str;
    }
    
}
