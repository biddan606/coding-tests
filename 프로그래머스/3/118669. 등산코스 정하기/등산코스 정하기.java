import java.util.*;

class Solution {
    /*
    출발지 -> 산봉우리로 갔던 루트 그대로 돌아올 때도 올 것이다.
    - 다른 길로 가서 최소값을 찾더라도 이미 최대값은 정해져 있으니 `intensity`는 변하지 않는다.
    - 최대값을 갱신하게 되면 최소값이 아니게 되므로 무의미하다.
    출발지 -> 산봉우리까지 가는 길만 구하면 된다.
    */
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        // 노드 초기화
        Map<Integer, Node> nodes = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            nodes.put(i, new Node(i));
        }
        
        // 간선 처리
        for (int[] path : paths) {
            Node node1 = nodes.get(path[0]);
            Node node2 = nodes.get(path[1]);
            int weight = path[2];
            
            node1.edges.add(new Edge(weight, node2));
            node2.edges.add(new Edge(weight, node1));
        }
        
        // 산봉우리 등록
        for (int summit : summits) {
            nodes.get(summit).summitStatus = true;
        }
        
        // 출발지 등록
        PriorityQueue<QueueElement> pq = new PriorityQueue<>((e1, e2) -> e1.intensity - e2.intensity);
        
        for (int gate : gates) {
            pq.offer(new QueueElement(0, nodes.get(gate)));
        }
        
        return bfs(pq);
    }
    
    private int[] bfs(PriorityQueue<QueueElement> pq) {
        int minIntensity = Integer.MAX_VALUE;
        int summit = Integer.MAX_VALUE;
        
        while (!pq.isEmpty()) {
            QueueElement current = pq.poll();
            if (current.node.visited) {
                continue;
            }
            current.node.visited = true;
            
            if (current.node.summitStatus) {
                if (minIntensity > current.intensity
                   || (minIntensity == current.intensity && summit > current.node.number)) {
                    minIntensity = current.intensity;
                    summit = current.node.number;
                }
                continue;
            }
            
            for (Edge e : current.node.edges) {
                if (e.weight > minIntensity) {
                    continue;
                }
                
                pq.offer(new QueueElement(Math.max(current.intensity, e.weight), e.node));
            }
        }
            
        return new int[]{summit, minIntensity};
    }
    
    private static class Node {
        final int number;
        final Set<Edge> edges = new HashSet<>();
        boolean visited;
        boolean summitStatus;
        
        public Node(int number) {
            this.number = number;
        }
    }
    
    private static class Edge {
        final int weight;
        final Node node;
        
        public Edge(int weight, Node node) {
            this.weight = weight;
            this.node = node;
        }
    }
    
    private static class QueueElement {
        final int intensity;
        final Node node;
        
        public QueueElement(int intensity, Node node) {
            this.intensity = intensity;
            this.node = node;
        }
    }
}