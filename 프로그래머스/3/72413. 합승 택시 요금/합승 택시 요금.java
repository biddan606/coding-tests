import java.util.*;

class Solution {
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] distances = new int[n + 1][n + 1];
        for (int from = 0; from <= n; from++) {
            Arrays.fill(distances[from], Integer.MAX_VALUE / 3);
            
            for (int to = 0; to <= n; to++) {
                if (from == to) {
                    distances[from][to] = 0;
                }
            }
        }
        
        for (int[] fare : fares) {
            int from = fare[0];
            int to = fare[1];
            int weight = fare[2];
            
            distances[from][to] = weight;
            distances[to][from] = weight;
        }
        
        floydWarshall(distances);
        
        int result = Integer.MAX_VALUE;
        
        for (int mid = 1; mid <= n; mid++) {
            int current = distances[s][mid] 
                + distances[mid][a]
                + distances[mid][b];
            
            result = Math.min(result, current);
        }
        
        return result;
    }
    
    private void floydWarshall(int[][] distances) {
        for (int mid = 1; mid < distances.length; mid++) {
            for (int start = 1; start < distances.length; start++) {
                for (int end = 1; end < distances.length; end++) {
                    distances[start][end] = Math.min(
                        distances[start][end], distances[start][mid] + distances[mid][end]
                    );
                }
            }
        }
    }

}