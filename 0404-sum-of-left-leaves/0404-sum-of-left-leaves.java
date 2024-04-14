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
    public int sumOfLeftLeaves(TreeNode root) {
        return sumLeftLeaves(root, false);
    }

    private int sumLeftLeaves(TreeNode node, boolean isLeftChild) {
        if (node == null) {
            return 0;
        }
        if (isLeaf(node) && isLeftChild) {
            return node.val;
        }


        int leftSum = sumLeftLeaves(node.left, true);
        int rightSum = sumLeftLeaves(node.right, false);

        return leftSum + rightSum;
    }

    private boolean isLeaf(TreeNode node) {
        return node.left == null & node.right == null;
    }
}
