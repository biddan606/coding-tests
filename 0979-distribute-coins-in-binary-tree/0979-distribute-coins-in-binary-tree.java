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
    private int moves = 0;

    public int distributeCoins(TreeNode root) {
        dfs(root);
        return moves;
    }

    private int dfs(TreeNode current) {
        if (current == null) {
            return 0;
        }

        // 자식 노드로부터 초과된 코인을 반환 받는다.
        int leftExcessedCoins = dfs(current.left);
        int rightExcessedCoins = dfs(current.right);

        /**
         * 이동 횟수를 추가한다.
         * 자식 노드로부터 양수의 코인을 받은 경우, 부모 노드로 이동한 것이기 때문에 +1
         * 자식 노드로부터 음수의 코인을 받은 경우, 언젠가 자식 노드로 이동해야 하기 때문에 +1
         */
        moves += Math.abs(leftExcessedCoins) + Math.abs(rightExcessedCoins);

        // 부모 노드로 초과된 코인을 반환한다.
        return current.val + leftExcessedCoins + rightExcessedCoins - 1;
    }
}
