public class HashDemo {
    public static void main(String[] args) {
        
        Hash<String, String> map = new Hash<>(89); // Prime is better, makes collision harder, due to the way %ing works with div
        map.add("Apple", "Grows on trees and is red");
        map.add("Bread", "Bread.");
        map.add("Coin", "Round sometimes shiny thing.");
        map.add("Duck", "Quack Quack Animal");
        map.add("Elephant", "Trunk, large, heavy, loud.");

        map.add("Flimango", "Sleeps on one foot, pink, skinny");
        map.remove("Flimango");

        System.out.println(map.getValue("Coin"));

    }
    
}
