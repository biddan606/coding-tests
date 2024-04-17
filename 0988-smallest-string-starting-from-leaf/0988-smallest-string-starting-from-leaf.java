/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public String smallestFromLeaf(TreeNode root) {
        return getSmallestPath(root, "");
    }

    private String getSmallestPath(TreeNode node, String path) {
        if (node == null) {
            return path;
        }
        String nextPath = ((char) ('a' + node.val)) + path;

        String leftPath = getSmallestPath(node.left, nextPath);
        String rightPath = getSmallestPath(node.right, nextPath);
        if (node.left == null) {
            return rightPath;
        } else if (node.right == null) {
            return leftPath;
        }

        return getSmaller(leftPath, rightPath);
    }

    private String getSmaller(String leftPath, String rightPath) {
        if (leftPath.compareTo(rightPath) <= 0) {
            return leftPath;
        }
        return rightPath;
    }
}
