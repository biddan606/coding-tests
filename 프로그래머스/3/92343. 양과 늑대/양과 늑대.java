import java.util.*;

class Solution {
    
    private static final int SHEEP_NUMBER = 0;
    private static final int WOLF_NUMBER = 1;
    
    public int solution(int[] info, int[][] edges) {
        // edges를 이용하여 노드끼리의 연결 정보를 얻습니다.
        boolean[][] graph = new boolean[info.length][info.length];
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            
            graph[from][to] = true;
        }
        
        // 양보다 늑대가 많은 최대 양의 개수를 반환합니다.(0번은 무조건 양이므로 양이 많을 수 있습니다)
        return getMaxSheep(graph, info, 0, new HashSet<>(), 0, 0);
    }
    
    private int getMaxSheep(boolean[][] graph, int[] info, int id, Set<Integer> idsSearch, int accumulatedSheep, int accumulatedWolf) {
        int currentSheep = accumulatedSheep;
        if (info[id] == SHEEP_NUMBER) {
            currentSheep++;
        }
        int currentWolf = accumulatedWolf;
        if (info[id] == WOLF_NUMBER) {
            currentWolf++;
        }
        
        if (currentWolf >= currentSheep) {
            return 0;
        }
        
        // 현재 노드에서 갈 수 있는 다음 노드를 추가합니다.
        for (int idAdd = 0; idAdd < graph[id].length; idAdd++) {
            if (!graph[id][idAdd]) {
                continue;
            }
            idsSearch.add(idAdd);
        }
        
        int maxSheep = currentSheep;
        
        // 노드를 순회합니다.
        for (int nextId : idsSearch) {        
            // 다음 노드로 방문합니다. 다음 노드 -> 다음 노드로 방문하면 안되기 때문에 다음 노드는 제거한 방문 노드로 넘겨줘야 합니다.
            maxSheep = Math.max(maxSheep,
                               getMaxSheep(graph, info, nextId, getNextIdsSearch(idsSearch, nextId), currentSheep, currentWolf));
        }
        
        // 반환 값으로 최대 양의 수를 반환합니다. 늑대의 수는 양의 수를 넘지 못하므로 항상 최대로 가능한 양의 수 입니다.
        return maxSheep;
    }
    
    private Set<Integer> getNextIdsSearch(Set<Integer> idsSearch, int nextId) {
        Set<Integer> nextIdsSearch = new HashSet<>(idsSearch);
        nextIdsSearch.remove(nextId);
        
        return nextIdsSearch;
    }
}