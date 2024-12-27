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
    public List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        Map<Integer, Integer> maxByRow = new HashMap<>();
        Queue<Element> queue = new LinkedList<>();
        queue.offer(new Element(0, root));
        int lastRow = 0;

        while(!queue.isEmpty()) {
            Element e = queue.poll();
            int max = Math.max(e.node.val, maxByRow.getOrDefault(e.row, Integer.MIN_VALUE));
            
            maxByRow.put(e.row, max);

            if (e.node.left != null) {
                queue.offer(new Element(e.row + 1, e.node.left));
            }
            if (e.node.right != null) {
                queue.offer(new Element(e.row + 1, e.node.right));
            }

            lastRow = e.row;
        }

        List<Integer> result = new ArrayList<>();
        for (int r = 0; r <= lastRow; r++) {
            result.add(maxByRow.get(r));
        }
        return result;
    }

    private static class Element {
        final int row;
        final TreeNode node;

        public Element(int row, TreeNode node) {
            this.row = row;
            this.node = node;
        }
    }
}
