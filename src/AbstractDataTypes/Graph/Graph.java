package AbstractDataTypes.Graph;

import java.beans.BeanDescriptor;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.DoubleBinaryOperator;

import javax.swing.DebugGraphics;

public class Graph {

    //All nodes are in this
    private LinkedList<Node> nodes;

    private final boolean DEBUG = true;

    public Graph(){
        nodes = new LinkedList<>();
    }

    public boolean addNode(Node node){
        return nodes.add(node);
    }

    public boolean addEdge(Node start, Node end, int weight){
        return start.edges.add(new Edge(start, end, weight));
    }

    /**
     *? Print out the MST of the graph using Prims algorithm.
     * 
     *! Graph must already be constructed by the caller.
     */
    public void prims(){
        int mst_weight = 0;
        if (DEBUG) System.out.print("PRIM");

        //make all nodes unvisited
        unvisitAllNodes();
        //pick a node start
        Node tmp = nodes.getFirst();
        if (DEBUG) System.out.println("Starting at " + tmp);
        
        tmp.visited = true;
        if (DEBUG) System.out.println(tmp + ".visited = true.");

        if (DEBUG) System.out.println("Visited all nodes...");

        // continue until all nodes are visited
        while(!allNodesVisited()){
        if (DEBUG) System.out.println("Getting smallest edge...");
            // find smallest edge
            Edge smallEdge = smallestEdgeToUnvisited();

            // print smallest edge
            if (DEBUG) System.out.println("Smallest Edge: " + smallEdge);

            // add cost to total mst cost
            mst_weight += smallEdge.weight;

            // mark dest as visited
            smallEdge.end.visited = true;

        }
        // report total weight
        System.out.println("Prims Complete. Total MST cost: " + mst_weight);


    }

    /**
     * Dijkstras Algo
     * Findind the shortest path given start and end, 100% of time
     * 
     */
    public void dijkstras(Node start, Node end){
        
        // Reset all data needed for dijkstras
        unvisitAllNodes();
        resetDistanceData();
        resetPrevData();

        Node curr = start;
        start.distance = 0;

        Scanner keybScanner = new Scanner(System.in);

        while(curr != null){
            if(DEBUG)print("Current node is " + curr + "." + "Distance = " + curr.distance);

            if (curr == end){
                break; // We found the end, exit outta there
            }
            
            // Pick edges 1 by one
            for (Edge edge : curr.edges){
                if(DEBUG)print("\tEdge: " + edge + " | Visited: " + edge.end.visited);

                if (edge.end.visited){
                    if(DEBUG)print("\t\tVisited Skiping.");
                    continue;
                }

                int newDistance = curr.distance + edge.weight;
                if(DEBUG)print("\t\tNew distance calculated: " + newDistance + "("+curr.distance+" + "+edge.weight+")");

                if(newDistance < edge.end.distance){
                    if(DEBUG)print("\t\tNew distance is smaller, updating.");
                    edge.end.distance = newDistance;
                    edge.end.prev = curr;

                }
            }//end for edge

            curr.visited = true;

            //TODO: printout table

            if(DEBUG)printDijkstrasTable();

            if(DEBUG)keybScanner.nextLine();

            curr = getSmallestDistNode();

        }//end while curr not null

        // Back track 
        Stack<Node> path = new Stack<>();

        // Start at the end
        curr = end;
        while(curr != null){
            path.push(curr);
            // Loop by traversing backwards to start
            curr = curr.prev;
        }

        print("Shortest path from " + start + " to " + end + ":");

        // Print out path, LIFO, start -> end
        while(!path.isEmpty()){
            curr = path.pop();
            print("Go to " + curr);
        }

        keybScanner.close();
    }

    private void printDijkstrasTable(){
        System.out.println();

        String rowFormat = "%-10s%-10s%-15s%-10s%n";
        System.out.printf(rowFormat, "NAME", "VISITED", "DIST", "PREV");

        for (Node node : nodes) { System.out.printf(rowFormat, node.name, node.visited, node.distance, node.prev); }
        
        System.out.println();
    }

    /**
     * Returns the node with the smallest distance value
     * that has not been visited
     * 
     * CAN RETURN NULL
     * 
     */
    private Node getSmallestDistNode(){
        int min = Integer.MAX_VALUE;
        Node candadite = null;

        for (Node n : nodes){
            if (n.visited) {continue;}
            if (n.distance < min) {min = n.distance; candadite = n;}
        }
        return candadite;
    }

    private void print(String s){
        System.out.println(s);
    }

    /**
     * Reset the previous node refered to by all noeds for easier traversal
     */
    private void resetPrevData(){
        for (Node n : nodes){
            n.prev = null;
        }
    }

    /**
     * Reset the distance data to max int value to overwrite any data that should not be there
     */
    private void resetDistanceData(){
        for (Node n : nodes){
            n.distance = Integer.MAX_VALUE;
        }
    }

    /**
     * Finds the smallest edge to an unvisited node,
     * only considering visited nodes.
     * 
     */
    private Edge smallestEdgeToUnvisited(){
        int min = Integer.MAX_VALUE;
        Edge candidate = null;

        for(Node n : nodes){
            if (n.visited){
                for(Edge e : n.edges){
                    // If the weight > min AND the end node has NOT been visited
                    if (e.weight < min && !e.end.visited){
                        min = e.weight;
                        candidate = e;
                    }
                }// end for edge
            }// end if
        }// end for node

        return candidate;
    }

    private boolean allNodesVisited(){
        // Early return if any not visited
        for (Node n : nodes){
            if (n.visited == false) return false;
        }
        // Return true if they're all visited.
        return true;
    }

    /**
     * Util method for marking all nodes to unvisited
     * so they appear un processed.
     */
    private void unvisitAllNodes(){
        for (Node n : nodes){
            n.visited = false;
        }
    }

    public void print(){
        for(Node n : nodes){
            for(Edge e : n.edges){
                System.out.print(e);
            }
            System.out.print("\n");
        }
    }
    
}
