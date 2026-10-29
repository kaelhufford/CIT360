public interface BinarySearchTreeI<K, V> {
	 /**
     * Public facing method that returns true or false 
     * depending on if a key is found in the tree.
     * 
     * @param key
     * @return
     */
	public boolean contains(K key);
	
	/**
     * Public method for getting items. 
     * Immediately does a search through the tree.
     * 
     * @param key
     * @return obj referenced in node or null if not found
     */
	public V get(K key);
	
	/**
     * Public facing method for adding elements.
     * 
     * @param key
     * @param obj
     */
	public void add(K key, V obj);
	
	/**
     * Public remove
     * 
     * @param key
     */
	public void remove(K key);
}
