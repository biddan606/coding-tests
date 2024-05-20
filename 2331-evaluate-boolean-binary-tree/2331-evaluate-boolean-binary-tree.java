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
    public boolean evaluateTree(TreeNode root) {
        return evaluateNode(root);
    }

    private boolean evaluateNode(TreeNode node) {
        if (node.val == 0) {
            return false;
        } else if (node.val == 1) {
            return true;
        }

        boolean left = evaluateNode(node.left);
        boolean right = evaluateNode(node.right);

        if (node.val == 2) {
            return left || right;
        }
        return left && right;
    }
}
