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
        Map<Integer, List<Integer>> nodeMap1 = generateNodeMap(root1);
        Map<Integer, List<Integer>> nodeMap2 = generateNodeMap(root2);
        return compare(nodeMap1, nodeMap2);
    }

    private static Map<Integer, List<Integer>> generateNodeMap(TreeNode root) {
        Map<Integer, List<Integer>> nodeMap = new HashMap<>();

        addValues(nodeMap, root);
        return nodeMap;
    }

    private static void addValues(Map<Integer, List<Integer>> nodeMap, TreeNode node) {
        if (node == null) {
            return;
        }

        List<Integer> children = new ArrayList<>();
        if (node.left != null) {
            children.add(node.left.val);
        }
        if (node.right != null) {
            children.add(node.right.val);
        }
        children.sort((a, b) -> a - b);

        nodeMap.put(node.val, children);

        addValues(nodeMap, node.left);
        addValues(nodeMap, node.right);
    }

    private static boolean compare(
            Map<Integer, List<Integer>> nodeMap1,
            Map<Integer, List<Integer>> nodeMap2) {
        if (nodeMap1.size() != nodeMap2.size()) {
            return false;
        }

        for (Map.Entry<Integer, List<Integer>> entry1 : nodeMap1.entrySet()) {
            if (!nodeMap2.containsKey(entry1.getKey())) {
                return false;
            }

            List<Integer> value1 = entry1.getValue();
            List<Integer> value2 = nodeMap2.get(entry1.getKey());
            if (value1.size() != value2.size()) {
                return false;
            }

            int size = value1.size();
            for (int i = 0; i < size; i++) {
                if (value1.get(i) != value2.get(i)) {
                    return false;
                }
            }
        }

        return true;
    }
}
