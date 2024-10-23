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
    public TreeNode replaceValueInTree(TreeNode root) {
        // Map에 자기 위치와 value를 저장한다.
        Map<Integer, Map<Integer, Integer>> valuesByLevel = calculateValues(root);

        // 사촌들의 value들을 합쳐 자신의 value로 변경한다.
        changeValueToSumOfCousins(valuesByLevel, root);
        return root;
    }

    private Map<Integer, Map<Integer, Integer>> calculateValues(TreeNode root) {
        Queue<Element> queue = new LinkedList<>();
        queue.offer(new Element(root, 0, 0));
        Map<Integer, Map<Integer, Integer>> valuesByLevel = new HashMap<>();

        while (!queue.isEmpty()) {
            Element element = queue.poll();

            Map<Integer, Integer> cousins = valuesByLevel.computeIfAbsent(element.level, k -> new HashMap<>());
            cousins.put((element.id + 1) / 2, cousins.getOrDefault((element.id + 1) / 2, 0) + element.node.val);

            if (element.node.left != null) {
                queue.offer(new Element(element.node.left, element.id * 2 + 1, element.level + 1));
            }
            if (element.node.right != null) {
                queue.offer(new Element(element.node.right, element.id * 2 + 2, element.level + 1));
            }
        }

        return valuesByLevel;
    }

    private void changeValueToSumOfCousins(Map<Integer, Map<Integer, Integer>> valuesByLevel, TreeNode root) {
        Queue<Element> queue = new LinkedList<>();
        queue.offer(new Element(root, 0, 0));

        while (!queue.isEmpty()) {
            Element element = queue.poll();

            element.node.val = valuesByLevel.getOrDefault(element.level, new HashMap<>())
                    .entrySet()
                    .stream()
                    .filter(entry -> !entry.getKey().equals((element.id + 1) / 2))
                    .map(Map.Entry::getValue)
                    .reduce(0, Integer::sum);

            if (element.node.left != null) {
                queue.offer(new Element(element.node.left, element.id * 2 + 1, element.level + 1));
            }
            if (element.node.right != null) {
                queue.offer(new Element(element.node.right, element.id * 2 + 2, element.level + 1));
            }
        }
    }

    private static class Element {
       final TreeNode node;
       final int id;
       final int level;

       public Element(TreeNode node, int id, int level) {
           this.node = node;
           this.id = id;
           this.level = level;
       }
   }
}
