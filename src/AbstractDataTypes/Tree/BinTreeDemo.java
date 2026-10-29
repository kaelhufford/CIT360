public class BinTreeDemo {
    public static void main(String[] args) {
        BinarySearchTree<Integer, String> bst = new BinarySearchTree<>();

        bst.add(29, "twenty-nine");
        bst.add(40, "fourty");
        bst.add(15, "fifteen");
        bst.add(20, "twenty");
        bst.add(5, "five");
        bst.add(4, "four");
        bst.add(3, "three");
        bst.add(100, "one-hundred");
        bst.add(17, "seventeen");
        bst.add(87, "eighty-seven");

        bst.remove(4);

        bst.toString();

    }
    
}