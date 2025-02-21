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
class FindElements {
    private static final int TARGET_MAX_VALUE = 1_000_000;
    private final boolean[] existingValues = new boolean[TARGET_MAX_VALUE + 1];

    public FindElements(TreeNode root) {
        TreeNode current = root;
        decontaminate(root, 0);
    }
    
    public boolean find(int target) {
        return existingValues[target];
    }

    private void decontaminate(TreeNode current, int value) {
        if (current == null) {
            return;
        }

        current.val = value;
        if (value <= TARGET_MAX_VALUE) {
            existingValues[value] = true;
        }

        decontaminate(current.left, value * 2 + 1);
        decontaminate(current.right, value * 2 + 2);
    }
}

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements obj = new FindElements(root);
 * boolean param_1 = obj.find(target);
 */
