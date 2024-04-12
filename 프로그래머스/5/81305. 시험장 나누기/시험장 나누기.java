class Solution {
    
    private static final int MAX_COUNT = 10_000 + 1;
        
    public int solution(int k, int[] num, int[][] links) {
        // 노드를 생성한다.
        Node[] nodes = new Node[num.length];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(i, num[i]);
        }

        // 노드끼리 연결한다.
        for (int i = 0; i < nodes.length; i++) {
            Node current = nodes[i];
            int leftId = links[i][0];
            int rightId = links[i][1];

            if (leftId != -1) {
                current.left = nodes[leftId];
                nodes[leftId].parent = current;
            }
            if (rightId != -1) {
                current.right = nodes[rightId];
                nodes[rightId].parent = current;
            }
        }

        // root 노드를 찾는다.
        Node root = null;
        for (Node node : nodes) {
            if (node.parent == null) {
                root = node;
                break;
            }
        }

        // k개로 분할 할 수 있는 가장 큰 값을 찾는다.
        int sum = 0;
        for (Node node : nodes) {
            sum += node.value;
        }

        int low = sum / k;
        int high = sum;
        while (low < high) {
            int mid = (low + high) / 2;

            if (canSplit(root, k, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return high;
    }

    private boolean canSplit(Node root, int targetCount, int limit) {
        DfsResult dfsResult = dfs(root, limit);
        return dfsResult.count + 1 <= targetCount;
    }

    private DfsResult dfs(Node current, int limit) {
        if (current == null) {
            return new DfsResult(0, 0);
        }
        if (current.value > limit) {
            return new DfsResult(MAX_COUNT, 0);
        }

        DfsResult left = dfs(current.left, limit);
        if (left.count == MAX_COUNT) {
            return left;
        }
        DfsResult right = dfs(current.right, limit);
        if (right.count == MAX_COUNT) {
            return right;
        }


        if (left.value + right.value + current.value <= limit) {
            return new DfsResult(left.count + right.count, left.value + right.value + current.value);
        }

        if (current.value + left.value > limit && current.value + right.value > limit) {
            return new DfsResult(left.count + right.count + 2, current.value);
        } else if (left.value < right.value) {
            return new DfsResult(left.count + right.count + 1, left.value + current.value);
        } else {
            return new DfsResult(left.count + right.count + 1, right.value + current.value);
        }
    }

    private static class Node {
        final int id;
        final int value;
        Node parent;
        Node left;
        Node right;

        public Node(int id, int value) {
            this.id = id;
            this.value = value;
        }
    }

    private static class DfsResult {
        final int count;
        final int value;

        public DfsResult(int count, int value) {
            this.count = count;
            this.value = value;
        }
    }
}
