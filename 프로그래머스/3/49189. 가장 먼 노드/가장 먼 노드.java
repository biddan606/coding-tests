import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        // 노드를 연결한다.
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int[] e : edge) {
            int id1 = e[0];
            int id2 = e[1];
            
            graph.computeIfAbsent(id1, k -> new ArrayList<>()).add(new Edge(id2));
            graph.computeIfAbsent(id2, k -> new ArrayList<>()).add(new Edge(id1));
        }
        
        // 1번 노드로부터의 거리를 반환한다.
        Map<Integer, Integer> distatnces = bfs(1, n, graph);
            
        // 가장 먼 노드를 찾는다.
        int maxDistance = distatnces.values().stream()
            .max(Integer::compare)
            .orElse(0);
        
        return (int) distatnces.values().stream()
            .filter(v -> v == maxDistance)
            .count();
    }
    
    private Map<Integer, Integer> bfs(int startId, int maxId, Map<Integer, List<Edge>> graph) {
        Map<Integer, Integer> distances = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        
        distances.put(startId, 0);
        queue.offer(startId);
        
        while (!queue.isEmpty()) {
            int currentId = queue.poll();
            int nextDistance = distances.get(currentId) + 1;
            
            for (Edge edge : graph.getOrDefault(currentId, Collections.emptyList())) {
                int nextId = edge.toId;
                if (distances.containsKey(nextId)) {
                    continue;
                }
                
                distances.put(nextId, nextDistance);
                queue.offer(nextId);
            }
        }
        
        return distances;
    }
    
    private static class Edge {
        final int toId;
        
        public Edge(int toId) {
            this.toId = toId;
        }
    }
}