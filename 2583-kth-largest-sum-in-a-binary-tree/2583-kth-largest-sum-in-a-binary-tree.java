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
    private Map<Integer, Long> levelSums;
    public long kthLargestLevelSum(TreeNode root, int k) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        
        Queue<TreeNode> nodes = new ArrayDeque<>();
        nodes.offer(root);

        while (!nodes.isEmpty()) {
            long currentLevelSum = 0;
            int size = nodes.size();

            for (int i = 0; i < size; i++) {
                TreeNode current = nodes.poll();
                currentLevelSum += current.val;

                if (current.left != null) {
                    nodes.offer(current.left);
                }
                if (current.right != null) {
                    nodes.offer(current.right);
                }
            }

            pq.offer(currentLevelSum);
            while (pq.size() > k) {
                pq.remove();
            }
        }

        if (pq.size() < k) {
            return -1;
        }
        return pq.peek();
    }
}
