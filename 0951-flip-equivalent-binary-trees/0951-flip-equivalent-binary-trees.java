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
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        // flip이 되었을 때 같다는 건, 자식이 모두 같다는 것
        // 각 노드의 자식들로 Map을 만든 후, 동일한지 비교
        Map<Integer, Integer> nodeMap1 = generateNodeMap(root1);
        Map<Integer, Integer> nodeMap2 = generateNodeMap(root2);
        return compare(nodeMap1, nodeMap2);
    }

    private static Map<Integer, Integer> generateNodeMap(TreeNode root) {
        Map<Integer, Integer> nodeMap = new HashMap<>();
        if (root != null) {
            nodeMap.put(root.val, -1);
            addValues(nodeMap, root);
        }

        return nodeMap;
    }

    private static void addValues(Map<Integer, Integer> nodeMap, TreeNode node) {
        if (node.left != null) {
            nodeMap.put(node.left.val, node.val);
            addValues(nodeMap, node.left);
        }
        if (node.right != null) {
            nodeMap.put(node.right.val, node.val);
            addValues(nodeMap, node.right);
        }
    }

    private static boolean compare(
            Map<Integer, Integer> nodeMap1,
            Map<Integer, Integer> nodeMap2) {
        if (nodeMap1.size() != nodeMap2.size()) {
            return false;
        }

        for (Map.Entry<Integer, Integer> entry1 : nodeMap1.entrySet()) {
            int key1 = entry1.getKey();
            int value1 = entry1.getValue();

            if (!nodeMap2.containsKey(key1) || nodeMap2.get(key1) != value1) {
                return false;
            }
        }

        return true;
    }
}
