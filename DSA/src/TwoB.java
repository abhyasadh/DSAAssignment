//You are given an array of binary trees that represent different cities where a certain corporation
// has its branch office and the organization wishes to provide service by constructing a service center.
// Building service centers at any node, i.e., a city can give service to its directly connected cities
// where it can provide service to its parents, itself, and its immediate children. Returns the smallest
// number of service centers required by the corporation to provide service to all connected cities. Note
// that: the root node represents the head office and other connected nodes represent the branch office
// connected to the head office maintaining some kind of hierarchy.


import javax.swing.tree.TreeNode;

public class TwoB {
//    int res = 0;
//    public int minCameraCover(TreeNode root) {
//        return (dfs(root) < 1 ? 1 : 0) + res;
//    }
//
//    public int dfs(TreeNode root) {
//        if (root == null) return 2;
//        int left = dfs(root.left), right = dfs(root.right);
//        if (left == 0 || right == 0) {
//            res++;
//            return 1;
//        }
//        return left == 1 || right == 1 ? 2 : 0;
//    }
}
