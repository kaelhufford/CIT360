import java.util.Iterator;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Hash<K, V> implements HashI<K, V>, Iterable<K> {

    private int tableSize;
    private int numElements;
    
    private double loadFactor;
	
    private LinkedList<HashElement<K,V>>[] table;

    public Hash() {
        this(16);
    }

    public Hash(int tableSize) {
        resize(tableSize);
    }

    @Override
    public boolean add(K key, V value) {
        LinkedList<HashElement<K,V>> list = table[hash(Objects.hashCode(key))];
        for (HashElement<K,V> h : list) {
            if (Objects.equals(h.key, key)) { return false; }
        }
        list.add(new HashElement<>(key, value));
        numElements++;
        loadFactor = (double) numElements / tableSize;
        return true;
    }

    @Override
    public boolean remove(K key, V value) {
        Iterator<HashElement<K,V>> items = table[hash(Objects.hashCode(key))].iterator();
        while (items.hasNext()) {
            HashElement<K,V> h = items.next();
            if (Objects.equals(h.key, key) && Objects.equals(h.value, value)) {
                items.remove();
                numElements--;
                loadFactor = (double) numElements / tableSize;
                return true;
            }
        }
        return false;
    }

    @Override
    public V getValue(K key) {
        for (HashElement<K,V> h : table[hash(Objects.hashCode(key))]) {
            if (Objects.equals(h.key, key)) { return h.value; }
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void resize(int newSize) {
        if (newSize <= 0) { throw new IllegalArgumentException("Table size must be greater than zero."); }
        LinkedList<HashElement<K,V>>[] oldTable = table;
        table = new LinkedList[newSize];
        tableSize = newSize;
        for (int i = 0; i < tableSize; i++) { table[i] = new LinkedList<>(); }
        if (oldTable != null) {
            for (LinkedList<HashElement<K,V>> list : oldTable) {
                for (HashElement<K,V> h : list) {
                    table[hash(Objects.hashCode(h.key))].add(h);
                }
            }
        }
        loadFactor = (double) numElements / tableSize;
    }

    @Override
    public double loadFactor() {
        return loadFactor;
    }

    class HashElement<K,V> implements Comparable<HashElement<K,V>>{
        public K key;
        public V value;

        public HashElement(K key, V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(HashElement<K,V> obj){
            return (((Comparable<K>) this.key).compareTo(obj.key));
        }

        //TODO: toString()

    }
    
    private int hash(int hashVal){
        hashVal = hashVal & 0x7FFFFFFF; // Force posititve in the fastest way possible | One assembly instruction
        hashVal = hashVal % tableSize;
        return  hashVal;
    }

	class IteratorHelper<T> implements Iterator<T>{
		//hold all the keys
		T[] keys;
		int position;//points to the item in the iterator
		
		public IteratorHelper() {
			keys = (T[]) new Object[numElements];
			
			//get everything out of the hash and put into an array of keys
			int p = 0;
			for(int i = 0; i < tableSize; i++) {
				LinkedList<HashElement<K, V>> list = table[i];
				
				for(HashElement<K, V> h : list) {
					keys[p++] = (T) h.key;
				}
			}
			
			//init position
			position = 0;
		}
		
		@Override
		public boolean hasNext() {
			return position < keys.length;
		}

		@Override
		public T next() {
			if(!hasNext())
				throw new NoSuchElementException();
			
			return keys[position++];
		}
		
	}
	
	@Override
	public Iterator<K> iterator() {
		return new IteratorHelper<K>();
	}
	
	
}
