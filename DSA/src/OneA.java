//There are n nations linked by train routes. You are given a 2D array indicating routes between
//countries and the time required to reach the target country, such that E[i]=[xi,yi,ki], where xi
//represents the source country, yi represents the destination country, and ki represents the time
//required to go from xi to yi. If you are also given information on the charges, you must pay while
//entering any country. Create an algorithm that returns the cheapest route from county A to county
//B with a time constraint.

//Solution:
//We can implement Dijkstra's algorithm to find the cheapest route between a given source and destination
//node in a graph, subject to a time constraint. The graph is represented using an adjacency list, where
//each node is represented by an integer value, and each edge is represented as a triplet of integers:
//the source node, the destination node, and the cost of the edge.

//We have to construct the graph using an adjacency list, initialize a priority queue and a visited set, and
//add the source node to the priority queue. Then, we need to repeatedly remove the node with the lowest
//cost from the priority queue, mark it as visited, and explore its neighboring nodes. For each neighboring
//node that has not been visited before and can be visited within the time constraint, we need to compute
//the cost of visiting the node and add it to the priority queue. When the destination node is reached,
//returns the cost of visiting the destination node. If the destination node is not reachable within the
//time constraint, return -1.

import java.util.*;

public class OneA {

    static class Edge {
        int src, dest, cost;
        Edge(int src, int dest, int cost) {
            this.src = src;
            this.dest = dest;
            this.cost = cost;
        }
    }

    public static int findCheapestRoute(int[][] edges, int[] charges, int source, int destination, int timeConstraint) {
        //Construct graph using the adjacency list
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int src = edge[0];
            int dest = edge[1];
            int cost = edge[2];
            graph.computeIfAbsent(src, k -> new ArrayList<>()).add(new Edge(src, dest, cost));
        }

        //Initialize priority queue and visited set
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]); // [node, time]
        Set<Integer> visited = new HashSet<>();

        //Add source node to priority queue
        pq.offer(new int[]{source, 0, charges[source]}); // [node, time, cost]

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int time = curr[1];
            int cost = curr[2];

            //If destination reached, return cost
            if (node == destination) {
                return cost;
            }

            //Mark node as visited
            visited.add(node);

            //Explore neighboring nodes
            if (graph.containsKey(node)) {
                for (Edge edge : graph.get(node)) {
                    int nextNode = edge.dest;
                    int nextTime = time + edge.cost;
                    int nextCost = cost + charges[nextNode];

                    //Check if next node can be visited within time constraint and has not been visited before
                    if (nextTime <= timeConstraint && !visited.contains(nextNode)) {
                        pq.offer(new int[]{nextNode, nextTime, nextCost});
                    }
                }
            }
        }

        //If destination not reachable, return -1
        return -1;
    }

    //driver method
    public static void main(String[] args) {
        int[][] edges = {{0,1,5}, {0,3,2}, {1,2,5}, {3,4,5}, {4,5,6}, {2,5,5}};
        int[] charges = {10,2,3,25,25,4};
        int source = 0;
        int destination = 5;
        int timeConstraint = 14;
        int cheapestCost = findCheapestRoute(edges, charges, source, destination, timeConstraint);
        System.out.println("Cheapest route cost: " + cheapestCost);
    }
}