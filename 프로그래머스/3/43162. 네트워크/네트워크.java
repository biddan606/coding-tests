import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];
        int count = 0;
        
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            
            link(computers, visited, i);
            count++;
        }
        
        return count;
    }
    
    private void link(int[][] computers, boolean[] visited, int startIndex) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(startIndex);
        
        while (!queue.isEmpty()) {
            int index = queue.poll();
            
            if (visited[index]) {
                continue;
            }
            visited[index] = true;
            
            for (int i = 0; i < computers[index].length; i++) {
                if (computers[index][i] == 1) {
                    queue.offer(i);
                }
            }
        }
    }
}
