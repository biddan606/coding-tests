import java.util.*;

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
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode newRoot = new TreeNode(val, root, null);
            return newRoot;
        }

        Queue<QueueElement> queue = new ArrayDeque<>();
        queue.offer(new QueueElement(root, 1));

        while (!queue.isEmpty()) {
            QueueElement element = queue.poll();
            
            TreeNode currentNode = element.node;
            if (currentNode == null) {
                continue;
            }
            
            int currentDepth = element.depth;
            int nextDepth = currentDepth + 1;
            
            if (nextDepth == depth) {
                TreeNode newLeftNode = new TreeNode(val, currentNode.left, null);
                currentNode.left = newLeftNode;

                TreeNode newRightNode = new TreeNode(val, null, currentNode.right);
                currentNode.right = newRightNode;
            } else if (nextDepth < depth) {
                queue.offer(new QueueElement(currentNode.left, nextDepth));
                queue.offer(new QueueElement(currentNode.right, nextDepth));
            }
        }

        return root;
    }

    private static class QueueElement {
        final TreeNode node;
        final int depth;

        public QueueElement(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
}