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
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        dfs(root, target);
        if (isMatch(root, target)) {
            return null;
        }
        return root;
    }

    private boolean isMatch(TreeNode node, int target) {
        return isLeaf(node) && node.val == target;
    }

    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

    private boolean dfs(TreeNode current, int target) {
        if (current == null) {
            return false;
        }

        boolean leftMatchingResult = dfs(current.left, target);
        if (leftMatchingResult) {
            current.left = null;    
        }

        boolean rightMatchingResult = dfs(current.right, target);
        if (rightMatchingResult) {
            current.right = null;    
        }

        if (isMatch(current, target)) {
            return true;
        }
        return false;
    }
}
