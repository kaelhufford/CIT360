import java.util.Iterator;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Hash<K, V> implements HashI<K, V>, Iterable<K> {

    private int tableSize;
    private int numElements;

    private final boolean DEBUG = false; 

    private double maxLoadFactor; // lambda

    private LinkedList<HashElement<K, V>>[] table; // An array of linked list of Hashed Element to value


    public Hash(int tableSize){
        if (tableSize <= 0) {
            throw new IllegalArgumentException("Table size must be positive");
        }
        this.tableSize = tableSize;

        table = (LinkedList<HashElement<K,V>>[]) new LinkedList[tableSize];

        // Populate the array with linked lists 
        for(int i = 0; i < table.length; i++){
            table[i] = new LinkedList<HashElement<K,V>>();
        }
        
        this.maxLoadFactor = 0.5; // Half the slots available at all times
        this.numElements = 0;
    }

    @Override
    public boolean add(K key, V value) {
        // Is full check
        if ((numElements + 1.0) / tableSize > maxLoadFactor) {
            resize(tableSize * 2);
        }

        // Create hash element
        HashElement<K,V> element = new HashElement<>(key, value);
        
        int hashVal = key.hashCode();
        hashVal = hash(hashVal);

        table[hashVal].addFirst(element);

        numElements++;

        return true;

    }

    @Override
    public V remove(K key) {
        Iterator<HashElement<K,V>> entries = table[hash(key.hashCode())].iterator();
        while (entries.hasNext()) {
            HashElement<K,V> entry = entries.next();
            if (key.equals(entry.key)) {
                entries.remove();
                numElements--;
                return entry.value;
            }
        }
        return null;
    }

    @Override
    public V getValue(K key) {
        for (HashElement<K,V> entry : table[hash(key.hashCode())]) {
            if (key.equals(entry.key)) {
                return entry.value;
            }
        }
        return null;
    }

    @Override
    public void resize(int newSize) {
        if (newSize <= 0) {
            throw new IllegalArgumentException("Table size must be positive");
        }
        // Make a new array of linked lists based on the new size
        LinkedList<HashElement<K,V>>[] newTable = (LinkedList<HashElement<K,V>>[]) new LinkedList[newSize];

        // Instantiate the LL at all pos in the new talbe

        for(int i = 0; i < newSize; i++){
            newTable[i] = new LinkedList<HashElement<K,V>>();
        }
        for (LinkedList<HashElement<K,V>> bucket : table) {
            for (HashElement<K,V> entry : bucket) {
                int index = hash(entry.key.hashCode(), newSize);
                newTable[index].addLast(entry);
            }
        }

        table = newTable;
        tableSize = newSize;

    }

    /**
     * Measure of how full the table is
     * Elements divided by space we have in the map.
     * 
     * @returns double representing the ratio of fullness
     */
    @Override
    public double loadFactor() {
        return (double) numElements / tableSize;
    }

    class HashElement<K, V> implements Comparable<HashElement<K, V>> {
        public K key;
        public V value;

        public HashElement(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(HashElement<K, V> obj) {
            return (((Comparable<K>) this.key).compareTo(obj.key));
        }

        @Override
        public String toString() {
            return "[" + this.key + ": " + value + "]"; // [<KEY> : <VALUE>]
        }

    }

    private int hash(int hashVal) {
        return hash(hashVal, tableSize);
    }

    private int hash(int hashVal, int capacity) {
        hashVal = hashVal & 0x7FFFFFFF; // Force posititve in the fastest way possible | One assembly instruction
        if(DEBUG) {System.out.println(hashVal);} //Debug = print out stuff
        hashVal = hashVal % capacity; // Hash is based off of the table
        if(DEBUG) {System.out.println(hashVal);}  //Debug = print out stuff
        return hashVal;
    }

    class IteratorHelper<T> implements Iterator<T> {
        // hold all the keys
        T[] keys;
        int position;// points to the item in the iterator

        public IteratorHelper() {
            keys = (T[]) new Object[numElements];

            // get everything out of the hash and put into an array of keys
            int p = 0;
            for (int i = 0; i < tableSize; i++) {
                LinkedList<HashElement<K, V>> list = table[i];

                for (HashElement<K, V> h : list) {
                    keys[p++] = (T) h.key;
                }
            }

            // init position
            position = 0;
        }

        @Override
        public boolean hasNext() {
            return position < keys.length;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();

            return keys[position++];
        }

    }

    @Override
    public Iterator<K> iterator() {
        return new IteratorHelper<K>();
    }

}
