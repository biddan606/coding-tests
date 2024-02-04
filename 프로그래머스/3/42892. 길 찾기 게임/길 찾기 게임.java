import java.util.*;

class Solution {

    public int[][] solution(int[][] nodeinfo) {
        // 이진 트리를 만든다
        CustomNode[] nodes = new CustomNode[nodeinfo.length];
        for (int i = 0; i < nodeinfo.length; i++) {
            int value = i + 1;
            int width = nodeinfo[i][0];
            int height = nodeinfo[i][1];

            nodes[i] = new CustomNode(value, width, height);
        }
        
        // 레벨 순으로 정렬을 한다.
        Arrays.sort(nodes, (n1, n2) -> n2.height - n1.height);

        // 레벨 순으로 정렬된 노드들로 트리를 구성한다.
        CustomNode root = constructTree(nodes);

        // 전위 순회를 기록한다.
        List<Integer> preorderValues = new ArrayList<>();
        preorder(root, preorderValues);

        // 후위 순회를 기록한다.
        List<Integer> postorderValues = new ArrayList<>();
        postorder(root, postorderValues);

        return new int[][]{
                preorderValues.stream().mapToInt(Integer::intValue).toArray(),
                postorderValues.stream().mapToInt(Integer::intValue).toArray()
        };
    }
    
    private CustomNode constructTree(CustomNode[] nodes) {
        CustomNode root = nodes[0];
        
        for (int i = 1; i < nodes.length; i++) {
            insert(root, nodes[i]);
        }
        
        return root;
    }
    
    private void insert(CustomNode root, CustomNode leaf) {
        if (root.width > leaf.width) {
            if (root.left == null) {
                root.left = leaf;
                return;
            }
            insert(root.left, leaf);
        } else if (root.width < leaf.width) { 
            if (root.right == null) {
                root.right = leaf;
                return;
            }
            insert(root.right, leaf);
        }
    }
    
    private void preorder(CustomNode node, List<Integer> visited) {
        if (node == null) {
            return;
        }
        
        visited.add(node.value);
        preorder(node.left, visited);
        preorder(node.right, visited);
    }
    
    private void postorder(CustomNode node, List<Integer> visited) {
        if (node == null) {
            return;
        }
        
        postorder(node.left, visited);
        postorder(node.right, visited);
        visited.add(node.value);
    }

    private static class CustomNode {

        int value;
        int width;
        int height;
        
        CustomNode left;
        CustomNode right;

        public CustomNode(int value, int width, int height) {
            this.value = value;
            this.width = width;
            this.height = height;
        }
    }
}
