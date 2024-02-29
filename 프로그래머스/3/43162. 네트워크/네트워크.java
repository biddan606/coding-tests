import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int n, int[][] computers) {
        // 모든 컴퓨터를 돌며 컴퓨터끼리 연결한다.
        Map<Integer, Integer> linked = new HashMap<>();
        
        for (int i = 0; i < computers.length; i++) {
            if (linked.containsKey(i)) {
                continue;
            }
            
            for (int j = 0; j < computers[i].length; j++) {
                link(computers, i, linked, i);
            }
        }
        
        // 존재하는 네트워크 개수를 반환한다.
        List<Integer> result = linked.values().stream()
            .distinct()
            .collect(Collectors.toList());
        return result.size();
    }
    
    private void link(int[][] computers, int startIndex, Map<Integer, Integer> linked, int linkNumber) {
        Deque<Integer> indexes = new ArrayDeque<>();
        indexes.addLast(startIndex);
        
        while (!indexes.isEmpty()) {
            int currentIndex = indexes.removeFirst();
            int[] current = computers[currentIndex];
            
            for (int nextIndex = 0; nextIndex < current.length; nextIndex++) {
                if (current[nextIndex] == 0) {
                    continue;
                }
                if (linked.containsKey(nextIndex)) {
                    continue;
                }
                
                linked.put(nextIndex, linkNumber);
                indexes.addLast(nextIndex);
            }
        }
    }
}