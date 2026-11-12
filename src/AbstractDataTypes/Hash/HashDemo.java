public class HashDemo {
    public static void main(String[] args) {
        
        Hash<String, String> map = new Hash<>(4); // Small enough to exercise automatic resizing.
        map.add("Apple", "Grows on trees and is red");
        map.add("Bread", "Bread.");
        map.add("Coin", "Round sometimes shiny thing.");
        map.add("Duck", "Quack Quack Animal");
        map.add("Elephant", "Trunk, large, heavy, loud.");

        map.add("Flimango", "Sleeps on one foot, pink, skinny");
        String removed = map.remove("Flimango");
        if (!"Sleeps on one foot, pink, skinny".equals(removed)
                || map.getValue("Flimango") != null) {
            throw new AssertionError("Flimango should be removed");
        }

        if (!"Round sometimes shiny thing.".equals(map.getValue("Coin"))) {
            throw new AssertionError("Coin should still be present after resizing");
        }

        System.out.println(map.getValue("Coin"));

    }
    
}
