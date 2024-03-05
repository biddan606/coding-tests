import java.util.*;
import java.util.stream.*;

class Solution {
    /*
    출발지 -> 산봉우리로 갔던 루트 그대로 돌아올 때도 올 것이다.
    - 다른 길로 가서 최소값을 찾더라도 이미 최대값은 정해져 있으니 `intensity`는 변하지 않는다.
    - 최대값을 갱신하게 되면 최소값이 아니게 되므로 무의미하다.
    출발지 -> 산봉우리까지 가는 길만 구하면 된다.
    */
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        Graph graph = new Graph();
        IntStream.range(1, n + 1)
            .forEach(graph::addNode);
        
        Arrays.stream(paths)
            .forEach(path -> graph.addEdge(path[0], path[1], path[2]));
        
        Arrays.stream(summits).forEach(summit -> graph.findNode(summit).summitFlag = true);
    
        PriorityQueue<QueueElement> pq = new PriorityQueue<>(Comparator.comparingInt(QueueElement::getIntensity));
        Arrays.stream(gates)
            .mapToObj(gate -> new QueueElement(graph.findNode(gate), 0))
            .forEach(pq::offer);
        
        return bfs(pq);
    }
    
    private int[] bfs(PriorityQueue<QueueElement> pq) {
        int minIntensity = Integer.MAX_VALUE;
        int summitId = Integer.MAX_VALUE;
        
        while (!pq.isEmpty()) {
            QueueElement current = pq.poll();
            Node currentNode = current.getNode();
            int currentIntensity = current.getIntensity();
                
            if (currentNode.visited) {
                continue;
            }
            currentNode.visited = true;
            
            if (currentNode.summitFlag) {
                if (canUpdateMinPath(currentNode.id, currentIntensity, summitId, minIntensity)) {
                    minIntensity = currentIntensity;
                    summitId = currentNode.id;
                }
                continue;
            }
            
            for (Edge e : currentNode.edges) {
                if (e.weight > minIntensity) {
                    continue;
                }
                
                pq.offer(new QueueElement(e.to, Math.max(currentIntensity, e.weight)));
            }
        }
            
        return new int[]{summitId, minIntensity};
    }
    
    private boolean canUpdateMinPath(int nextId, int nextIntensity, int currentId, int currentIntensity) {
        if (currentIntensity > nextIntensity) {
            return true;
        }
        return currentIntensity == nextIntensity && currentId > nextId;
    }
    
    private static class Node {
        final int id;
        final List<Edge> edges = new ArrayList<>();
        boolean summitFlag;
        boolean visited;
        
        public Node(int id) {
            this.id = id;
        }
    }
    
    private static class Edge {
        final Node to;
        final int weight;
        
        public Edge(Node to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    
    private static class Graph {
        final Map<Integer, Node> nodes = new HashMap<>();
        
        public void addNode(int id) {
            nodes.putIfAbsent(id, new Node(id));
        }
        
        public void addEdge(int fromId, int toId, int weight) {
            Node fromNode = nodes.get(fromId);
            Node toNode = nodes.get(toId);
            
            fromNode.edges.add(new Edge(toNode, weight));
            toNode.edges.add(new Edge(fromNode, weight));
        }
        
        public Node findNode(int id) {
            return nodes.get(id);
        }
    }
    
    private static class QueueElement {
        final Node node;
        final int intensity;
        
        public QueueElement(Node node, int intensity) {
            this.node = node;
            this.intensity = intensity;
        }
        
        public Node getNode() {
            return node;
        }
        
        public int getIntensity() {
            return intensity;
        }
    }
}