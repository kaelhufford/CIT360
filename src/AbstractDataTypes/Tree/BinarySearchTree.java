import javax.print.FlavorException;

public class BinarySearchTree<K,V> implements BinarySearchTreeI<K, V>{
    //PRIVATE INNER CLASS
    private class Node<K, V>{
        public Node<K,V> left;
        public Node<K,V> right;

        public K key;
        public V value;

        //CONSTR
        public Node(K key, V value){
            this.key = key;
            this.value = value;

            left = right = null;
        
        }

    }


    private Node<K,V> root;
    
    // CONSTRUCTOR
    public BinarySearchTree(){
        root = null;
    }

    @Override
    public boolean contains(K key) { 
        // This compacts making a tmp node and comparing it, and then 
        return  ( searchTree(root, key) != null );
    }

    @Override
    public V get(K key) {
        Node<K,V> tmp = searchTree(root, key);

        if (tmp == null) {return null;}
        return tmp.value;
    }

    private Node<K,V> searchTree(Node<K,V> currNode, K key){
        if (currNode == null){
            return null;
        }
        
        // FOUND IT
        if (((Comparable) key).compareTo(currNode.key) == 0){
            return currNode;
        }

        if (((Comparable) key).compareTo(currNode.key) < 0){
            return searchTree(currNode.left, key);
        }

        return searchTree(currNode.right, key);

    }

    @Override
    public void add(K key, V obj){
        root = add(root, key, obj);
        return;
    }

    private Node<K,V> add(Node<K,V> curr, K key, V value){
        if (curr == null){
            return new Node<K, V>(key, value);
        }

        if (((Comparable) key).compareTo(curr.key) < 0){
            // Go left
            curr.left = add(curr.left, key, value);
        }

        if (((Comparable) key).compareTo(curr.key) > 0){
            curr.right = add(curr.right, key, value);
        }

        return curr;
    }

    @Override
    public void remove(K key) {
        delete(root, key);
    }

    private void delete(Node<K,V> root, K key){
        Node<K,V> parrent = null;
        Node<K,V> curr = root;

        while(curr != null && ((Comparable) key).compareTo(curr.key) != 0){
            parrent = curr;
        
            if (((Comparable) key).compareTo(curr.key) < 0){
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        // Early return, nothing found, nothing to remove
        if (curr == null) {return;}

        // 1- node is a leaf
        if (curr.left == null && curr.right == null){
            //If node to be deleted is not a root, then set its parent left/right to null
            if (curr == root){
                // Special case 
                this.root = null;
            } else {
                if(parrent.left == curr){
                    parrent.left = null;
                } else {
                    parrent.right = null;
                }
            }
        } else if (curr.left != null && curr.right != null){ // Node has to have two children
            // Find sucessor/predecessor
            Node<K,V> predNode = findPred(curr);

            K predKey = predNode.key;
            V predValue = predNode.value;

            delete(root, predKey);

            curr.key = predKey;
            curr.value = predValue;

        } else {
//?                          If curr.left = null, then curr.right, but if null. then curr.left.
            Node<K,V> child = (curr.left == null) ? curr.right : curr.left;

            if (curr == root){
                this.root = child;
            } else {
                if (curr == parrent.left){
                    parrent.left = child;
                } else {
                    parrent.right = child;
                }
            }

        }
    }

    private Node<K,V> findPred(Node<K,V> curr){
        curr = curr.left;

        while (curr.right != null) {
            curr = curr.right;
        }

        return curr;
    }

    public void printInOrder(){
        inOrder(root);
    }

    private void inOrder(Node<K,V> node){
        if (node != null){ // Base case is == null
            inOrder(node.left);
            System.out.print(node.value);
            inOrder(node.right);
        }

    }

}