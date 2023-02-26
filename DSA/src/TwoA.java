//You are given a 2D array containing hierarchical information about certain species, with
// edge[i]=[xi,yi], where node xi is connected to xj. You are also provided an array of values associated
// with each species, such that value[i] reflects the ith nodes value. If the greatest common divisor
// of two values is 1, they are "relatively prime." Any other node on the shortest path from that node
// to the absolute parent node is an ancestor of certain species i. Return a list of nearest ancestors,
// where result[i] is the node i's nearest ancestor such that values[i] and value[result[i]] are both
// relative primes otherwise -1.

//Solution:
//First, we need a method to calculate the GCD (Greatest Common Divisor) of two numbers.

//Then, we need to conduct a depth-first search from the given node. We need to keep track of the depth
//of the current node (depth), whether the node has been visited (visited), the ancestor that is the
//closest and has a coprime number (ancestor), and the answer array (ans). We also require a
//Map<Integer,int[]> to store the last occurrence of each number in nums and its depth and node.

//Then we need a method that initializes a boolean matrix that stores whether two numbers are coprime or
//not. Then we have to construct a tree using the input edges. After that, we initialize the ans array to
// -1 for all nodes except the root, which has an ancestor of -1. Then we need to call the dfs method on
//the root node and return the ans array.

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class TwoA {

    //The gcd method computes the greatest common divisor of two integers using the Euclidean algorithm.
    //It takes two integers n1 and n2 as arguments and returns their gcd.
    public int gcd(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return gcd(n2, n1 % n2);
    }


    public void dfs(int[] nums, LinkedList<Integer>[] tree, int depth, int node, boolean[] visited, int[] ans, Map<Integer,int[]> map, boolean[][] poss) {
        //check if the node is already visited
        if(visited[node]) return;

        //if not visited, visit the node, set its ancestor to -1
        visited[node] = true;
        int ancestor = -1;

        //distance between current node and ancestor
        int d = Integer.MAX_VALUE;

        //loops through all the possible coprime numbers for nums[node] and find the ancestor that is
        //closest to the current node and has a coprime number
        for(int i = 1; i < 51; i++) {
            if(poss[nums[node]][i] && map.containsKey(i)) {
                if(depth - map.get(i)[0] <= d) {

                    //update ancestor and distance
                    d = depth - map.get(i)[0];
                    ancestor = map.get(i)[1];
                }
            }
        }

        //store the current occurrence of nums[node] in the map
        ans[node] = ancestor;
        int[] exist = (map.containsKey(nums[node])) ? map.get(nums[node]) :  new int[]{-1,-1};
        map.put(nums[node],new int[]{depth,node});
        for(int child : tree[node]) {
            if(visited[child]) continue;

            //recursively visit all the children of the current node, incrementing the depth and passing
            //in the updated variables
            dfs(nums, tree, depth+1, child, visited, ans, map, poss);
        }

        //remove the current occurrence of nums[node] from the map, or restore the last occurrence if
        //it was present before.
        if(exist[0] != -1) map.put(nums[node], exist);
        else map.remove(nums[node]);
    }

    public int[] getCoprimes(int[] nums, int[][] edges) {

        //matrix to store whether two numbers are coprime or not
        boolean[][] poss = new boolean[51][51];
        for(int i = 1; i < 51; i++) {
            for(int j = 1; j < 51; j++) {
                if(gcd(i,j) == 1) {
                    poss[i][j] = true;
                    poss[j][i] = true;
                }
            }
        }
        int n = nums.length;

        //construct a tree using input edges
        LinkedList<Integer>[] tree = new LinkedList[n];

        for(int i =0 ; i < tree.length; i++) tree[i] = new LinkedList<>();
        for(int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
            tree[edge[1]].add(edge[0]);
        }

        //initialize the ans array to -1 for all nodes except the root, which has an ancestor of -1
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        ans[0] = -1;
        Map<Integer,int[]> map = new HashMap<>();

        //call the dfs method on the root node and return the ans array
        boolean[] visited = new boolean[n];
        dfs(nums, tree, 0, 0, visited, ans, map, poss);
        return ans;
    }

    //driver method
    public static void main(String[] args) {
        TwoA nearestAncestor = new TwoA();
        System.out.println(
                Arrays.toString(nearestAncestor.getCoprimes(new int[]{3, 2, 6, 6, 4, 7, 12}, new int[][]{{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}}))
    );
    }
}
