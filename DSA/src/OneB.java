//Assume you were hired to create an application for an ISP, and there is n number of network
// devices, such as routers, that are linked together to provides internet access to home user users. You
// are given a 2D array that represents network connections between these network devices such that
// a[i]=[xi,yi] where xi is connected to yi device.  Suppose there is a power outage on a certain device
// provided as int n represents id of the device on which power failure occurred, Write an algorithm
// to return impacted network devices due to breakage of the link between network devices. These impacted
// device list assists you notify linked consumers that there is a power outage and it will take some
// time to rectify an issue. Note that: node 0 will always represent a source of internet or gateway to
// international network.

//Solution:
//We can find the impacted devices by first removing its connected edges. Then we can conduct depth-first-
//search to find of the source (0) is reachable from each nodes. If it is not reachable, it is flagged as
//impacted and printed to the console.


import java.util.*;

public class OneB {

    //This function takes as input an adjacency list, represented by an ArrayList of ArrayLists, and two
    //integers s and d representing the source and destination nodes. It adds an edge between the two
    //nodes by appending d to the list at index s, and appending s to the list at index d.
    static void addEdge(ArrayList<ArrayList<Integer>> adjacencyMatrix, int s, int d) {
        adjacencyMatrix.get(s).add(d);
        adjacencyMatrix.get(d).add(s);
    }

    //This function takes as input an adjacency list, represented by an ArrayList of ArrayLists, and
    //two integers s and d representing the source and destination nodes. It removes the edge between
    //the two nodes by removing d from the list at index s, and removing s from the list at index d.
    static void removeEdge(ArrayList<ArrayList<Integer>> adjacencyMatrix, int s, int d) {
        if (adjacencyMatrix.get(s).contains(d)) {
            adjacencyMatrix.get(s).remove(Integer.valueOf(d));
            System.out.println("removed node " + s);
        }
        if (adjacencyMatrix.get(d).contains(s)) {
            adjacencyMatrix.get(d).remove(Integer.valueOf(s));
            System.out.println("removed node " + d);
        }
    }

    //This function takes as input an adjacency list and an integer representing the disconnected node.
    //It iterates through the list of neighbors of the disconnected node, removes each edge between the
    //disconnected node and its neighbors using the removeEdge function, and prints a message to the
    //console for each edge that is removed.
    private static void printDisconnectedNodes(ArrayList<ArrayList<Integer>> adjacencyMatrix, int disconnectedNode) {
        for (int i = adjacencyMatrix.get(disconnectedNode).size() - 1; i >= 0; i--) {
            int neighbor = adjacencyMatrix.get(disconnectedNode).get(i);
            System.out.println("Removing edge between " + disconnectedNode + " and " + neighbor);
            removeEdge(adjacencyMatrix, disconnectedNode, neighbor);
        }
    }

    //This function takes as input an adjacency list, represented by an ArrayList of ArrayLists, and
    //two integers s and d representing the source and destination nodes. It uses depth-first search (DFS)
    //to determine whether there is a path between s and d in the graph. It initializes a boolean array
    //visited to track which nodes have been visited. It sets the value at index s to true to mark it as
    //visited. It checks if s is equal to d, and if so, returns true. Otherwise, it iterates through the
    //list of neighbors of s and recursively calls the DFS function on each unvisited neighbor. If any
    //neighbor leads to a path to d, the function returns true. Otherwise, it returns false.
    static boolean isReachable(ArrayList<ArrayList<Integer>> adjacencyMatrix, int s, int d) {
        boolean[] visited = new boolean[adjacencyMatrix.size()];
        return dfs(adjacencyMatrix, visited, s, d);
    }

    static boolean dfs(ArrayList<ArrayList<Integer>> adjacencyMatrix, boolean[] visited, int s, int d) {
        visited[s] = true;
        if (s == d) {
            return true;
        }
        for (int i = 0; i < adjacencyMatrix.get(s).size(); i++) {
            int v = adjacencyMatrix.get(s).get(i);
            if (!visited[v] && dfs(adjacencyMatrix, visited, v, d)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        int vertices = 8;

        //initialize an empty adjacency matrix
        ArrayList<ArrayList<Integer>> adjacencyMatrix = new ArrayList<>(vertices);

        //add new arraylist that will represent the edges of the graph (time complexity: O(n))
        for (int i = 0; i < vertices; i++) adjacencyMatrix.add(new ArrayList<>());

        //add edges
        addEdge(adjacencyMatrix, 0, 1);
        addEdge(adjacencyMatrix, 0, 2);
        addEdge(adjacencyMatrix, 1, 3);
        addEdge(adjacencyMatrix, 2, 4);
        addEdge(adjacencyMatrix, 1, 6);
        addEdge(adjacencyMatrix, 4, 6);
        addEdge(adjacencyMatrix, 4, 5);
        addEdge(adjacencyMatrix, 5, 7);

        //provide the disconnected node
        int disconnectedNode=4;

        //use printDisconnectedNodes function to remove all edges that connect to this node
        printDisconnectedNodes(adjacencyMatrix,disconnectedNode);

        //use isReachable function to determine if each node in the array is reachable from the destination
        //node. If a node is not reachable, it is printed to the console.
        int[] nodes ={0,1,2,3,4,5,6,7};
        int destination = 0;
        for (int source : nodes) {
            boolean disconnected = isReachable(adjacencyMatrix, source, destination);
            if (!disconnected) {
                System.out.println("disconnected nodes are " + source);
            }

        }
    }
}
