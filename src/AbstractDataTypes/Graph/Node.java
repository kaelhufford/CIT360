package AbstractDataTypes.Graph;

import java.util.LinkedList;

public class Node {

    public String name;
    public LinkedList<Edge> edges; // Outgoing

    // MST
    public boolean visited;

    // FPoT (Fastest Path of Traversal)
    public int distance;
    public Node prev;

    public Node(String name){
        this.name = name;
        edges = new LinkedList<>();
        visited = false;
        distance = 0;
        prev = null;
    }

    @Override 
    public String toString(){
        return this.name;
    }
    
}
