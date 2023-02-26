//You are given an array of binary trees that represent different cities where a certain corporation
// has its branch office and the organization wishes to provide service by constructing a service center.
// Building service centers at any node, i.e., a city can give service to its directly connected cities
// where it can provide service to its parents, itself, and its immediate children. Returns the smallest
// number of service centers required by the corporation to provide service to all connected cities. Note
// that: the root node represents the head office and other connected nodes represent the branch office
// connected to the head office maintaining some kind of hierarchy.


import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoB {

    //an array of integers representing the parent of each node in the tree.
    private int[] parents;

    //an array of integers representing the depth of each node in the tree.
    private int[] depth;

    //an array of lists representing the children of each node in the tree.
    private List<Integer>[] children;

    //an array of integers representing the size of the subtree rooted at each node in the tree.
    private int[] subtreeSize;

    //an array of integers representing the minimum number of service centers required to cover the
    //subtree rooted at each node in the tree.
    private int[] serviceCenters;

    //an integer representing the number of nodes in the tree.
    private int n;

    public int minimumServiceCenters(int n, List<Integer>[] children) {

        //initialize variables
        this.n = n;
        this.children = children;
        parents = new int[n];
        depth = new int[n];
        subtreeSize = new int[n];
        serviceCenters = new int[n];

        //fill parents and serviceCenters as -1
        Arrays.fill(parents, -1);
        Arrays.fill(serviceCenters, -1);
        dfs1(0, -1);
        return dfs2(0, -1);
    }

    //this function performs a depth-first search of the tree, and computes the depth, parents, and
    //subtreeSize arrays for each node
    private void dfs1(int node, int parent) {
        //This is a recursive method that updates the depth, parents, and subtreeSize arrays for each
        //node in the tree. It takes as input the current node and its parent, and recursively calls
        //itself for each of the node's children. It first sets the parent of the current node to parent
        //and sets the depth of the current node to depth[parent] + 1. It then initializes the subtree
        //size of the current node to 1, and iterates over the children of the current node, calling
        //dfs1 recursively for each child, and updating the subtree size of the current node accordingly.

        parents[node] = parent;
        depth[node] = parent == -1 ? 0 : depth[parent] + 1;
        subtreeSize[node] = 1;
        for (int child : children[node]) {
            dfs1(child, node);
            subtreeSize[node] += subtreeSize[child];
        }
    }

    //this function also performs a depth-first search of the tree, and computes the serviceCenters
    //array for each node
    private int dfs2(int node, int parent) {
        //The dfs2 method is also a recursive method that updates the serviceCenters array for each
        //node in the tree. It takes as input the current node and its parent, and recursively calls
        //itself for each of the node's children. It first checks if the serviceCenters array has
        //already been computed for the current node, and returns the value if it has. It then
        //initializes a counter variable count to 0, and iterates over the children of the current node
        //calling dfs2 recursively for each child, and adding the result to count. It then computes two
        //options for the number of service centers required: the first option is to have a service
        //center at each child of the current node, and the second option is to have a single service
        //center at the current node, covering the entire subtree rooted at the current node. It takes
        //the minimum of these two options and adds 1 to get the minimum number of service centers
        //required for the subtree rooted at the current node. Finally, it updates the serviceCenters
        //array for the current node, and returns the value.

        if (serviceCenters[node] != -1) return serviceCenters[node];
        int count = 0;
        for (int child : children[node]) {
            count += dfs2(child, node);
        }
        int option1 = count;
        int option2 = n - subtreeSize[node];
        serviceCenters[node] = Math.min(option1, option2) + 1;
        return serviceCenters[node];
    }

    //driver method
    public static void main(String[] args) {
        List<Integer>[] children = new List[5];
        for (int i = 0; i < 5; i++) {
            children[i] = new ArrayList<>();
        }
        children[0].add(1);
        children[0].add(2);
        children[0].add(3);
        TwoB sc = new TwoB();
        System.out.println(sc.minimumServiceCenters(5, children));
    }
}
