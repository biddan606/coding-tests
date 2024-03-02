import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int[][] sortedRoutes = Arrays.stream(routes)
            .map(r -> Arrays.copyOf(r, r.length))
            .toArray(int[][]::new);
        
        Arrays.sort(sortedRoutes, (a, b) -> Integer.compare(a[1], b[1]));
            
        int count = 1;
        int camera = sortedRoutes[0][1];
        
        for (int i = 1; i < sortedRoutes.length; i++) {
            if (sortedRoutes[i][0] > camera) {
                count++;
                camera = sortedRoutes[i][1];
            }
        }
        
        return count;
    }
}