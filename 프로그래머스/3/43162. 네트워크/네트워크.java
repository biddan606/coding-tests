import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        // 해당 컴퓨터와 연결된 컴퓨터들을 방문 처리한다.
        boolean[] visited = new boolean[computers.length];
        int count = 0;
        
        for (int i = 0; i < computers.length; i++) {
            if (visited[i]) {
                continue;
            }
            
            link(computers, i, visited);
            count++;
        }
        
        return count;
    }
    
    private void link(int[][] computers, int startIndex, boolean[] visited) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(startIndex);
        
        while (!queue.isEmpty()) {
            int currentIndex = queue.removeFirst();
            if (visited[currentIndex]) {
                    continue;
                }
            visited[currentIndex] = true;
            
            int[] current = computers[currentIndex];
            
            for (int nextIndex = 0; nextIndex < current.length; nextIndex++) {
                if (computers[currentIndex][nextIndex] == 0) {
                    continue;
                }
                
                queue.addLast(nextIndex);
            }
        }
    }
}