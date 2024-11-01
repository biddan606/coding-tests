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
        levelSums = new HashMap<>();
        calculateLevelSums(root, 1);

        // 레벨 합을 정렬한다.
        List<Long> sorted = levelSums.values().stream()
            .sorted((a, b) -> Long.compare(b, a))
            .toList();

        // k번째 레벨 합을 반환한다.
        if (sorted.size() < k) {
            return -1;
        }   
        return sorted.get(k - 1);
    }

    private void calculateLevelSums(TreeNode current, int level) {
        if (current == null) {
            return;
        }

        levelSums.put(level, levelSums.getOrDefault(level, 0L) + current.val);

        calculateLevelSums(current.left, level + 1);
        calculateLevelSums(current.right, level + 1);
    }
}
