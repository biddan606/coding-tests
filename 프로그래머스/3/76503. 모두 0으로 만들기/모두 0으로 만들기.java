import java.util.*;

class Solution {
    /*
    # 문제 이해
    모든 점들의 가중치를 0으로 만들어야 한다
    가중치를 0으로 만들 수 있다면 최소 카운트, 없다면 -1을 반환한다

    # 접근
    - -1, 1 연산만 가능하다 고로 전체의 합이 0이 나와야 한다
      0이 아닐 경우, return -1  
    - 최소 카운트를 반환하려면 이동한 장소를 다시 이동하면 안된다
        한 방향으로만 이동해야 한다

    leaf node를 0으로 만들면서 나머지 값을 root node로 올리면 될거 같다

    # 구현 스텝
    1. totalSum이 0이 아니라면 -1을 반환한다
        - 이 다음 로직은 0으로 만드는 것이 항상 가능한 경우에만 수행한다
    2. a, edges를 조합하여 노드를 만든다
    3. leaf node부터 0을 만들며 root node로 나머지 값을 반환한다
        - 값이 이동할 때마다 이동한 값만큼 연산 수 증가
    4. 총 연산 수를 반환한다
    
    --- 런타임 에러
    
    dfs 깊이가 깊어져 에러가 발생하는 것을 추정
    a의 길이가 최대 300_000이니 dfs 깊이가 300_000까지 갈 수 있음
    
    dfs -> bfs로 변경
    */
    public long solution(int[] a, int[][] edges) {
        // 가중치를 0으로 만들 수 있는지 검사
        long totalSum = 0;
        for (int value : a) {
            totalSum += value;
        }

        if (totalSum != 0) {
            return -1;
        }

        // 노드 생성
        int size = a.length;
        Node[] nodes = new Node[size];
        
        for (int i = 0; i < a.length; i++) {
            nodes[i] = new Node(a[i], i);
        }

        for (int[] edge : edges) {
            Node node1 = nodes[edge[0]];
            Node node2 = nodes[edge[1]];

            node1.connectedIds.add(edge[1]);
            node2.connectedIds.add(edge[0]);
        }

        // 최소 연산 수 계산
        long totalOperations = iterativeDfs(nodes, 0);
        return totalOperations;
    }

    private long iterativeDfs(Node[] nodes, int start) {
        int size = nodes.length;
        
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(nodes[0]);
        boolean[] visited = new boolean[size];
        visited[0] = true;
        long totalOperations = 0;
        
        while (!stack.isEmpty()) {
            Node node = stack.peek();
            
            if (node.processed) {
                if (node.parentId != -1) {
                    long passedValue = node.value;
                    nodes[node.parentId].value += passedValue;
                    totalOperations += Math.abs(passedValue);
                }
                stack.pop();
                continue;
            }
            
            node.processed = true;
            for (int nextId : node.connectedIds) {
                if (!visited[nextId]) {
                    visited[nextId] = true;
                    Node nextNode = nodes[nextId];
                    
                    nextNode.parentId = node.id;
                    stack.push(nextNode);
                }
            }
        }
        
        return totalOperations;
    }

    private static class Node {
        final int id;
        long value;
        final List<Integer> connectedIds = new ArrayList<>();
        boolean processed;
        int parentId = -1;

        public Node(long value, int id) {
            this.value = value;
            this.id = id;
        }
    }
}
