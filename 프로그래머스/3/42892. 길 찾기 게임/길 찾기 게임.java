import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class Solution {

    public int[][] solution(int[][] nodeinfo) {
        // 이진 트리를 만든다
        Map<Integer, List<CustomNode>> heights = new TreeMap<>((i1, i2) -> i2 - i1);
        for (int i = 0; i < nodeinfo.length; i++) {
            int value = i + 1;
            int width = nodeinfo[i][0];
            int height = nodeinfo[i][1];

            CustomNode newCustomNode = new CustomNode(value, width);

            heights.computeIfAbsent(height, k -> new ArrayList<>())
                    .add(newCustomNode);
        }

        CustomLinkedList linkedList = new CustomLinkedList();
        for (List<CustomNode> nodes : heights.values()) {
            for (CustomNode node : nodes) {
                linkedList.add(node);
            }
        }

        // 전위 순회를 기록한다.
        List<Integer> preorderValues = linkedList.getPreorderValues();

        // 후위 순회를 기록한다.
        List<Integer> postorderValues = linkedList.getPostorderValues();

        return new int[][]{
                preorderValues.stream().mapToInt(v -> v).toArray(),
                postorderValues.stream().mapToInt(v -> v).toArray()
        };
    }

    private static class CustomLinkedList {

        CustomNode head;

        public void add(CustomNode newNode) {
            if (head == null) {
                head = newNode;
                return;
            }

            CustomNode prevNode = null;
            CustomNode node = head;
            while (node != null) {
                prevNode = node;

                if (newNode.width < node.width) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }

            if (newNode.width < prevNode.width) {
                prevNode.left = newNode;
            } else {
                prevNode.right = newNode;
            }
        }


        public List<Integer> getPreorderValues() {
            List<Integer> preorderValues = new ArrayList<>();

            preorder(head, preorderValues);

            return preorderValues;
        }

        private void preorder(CustomNode node, List<Integer> values) {
            if (node == null) {
                return;
            }

            values.add(node.value);
            preorder(node.left, values);
            preorder(node.right, values);
        }

        public List<Integer> getPostorderValues() {
            List<Integer> postorderValues = new ArrayList<>();

            postorder(head, postorderValues);

            return postorderValues;
        }

        private void postorder(CustomNode node, List<Integer> values) {
            if (node == null) {
                return;
            }

            postorder(node.left, values);
            postorder(node.right, values);
            values.add(node.value);
        }
    }

    private static class CustomNode {

        int value;
        int width;
        CustomNode left;
        CustomNode right;

        public CustomNode(int value, int width) {
            this.value = value;
            this.width = width;
        }
    }
}
