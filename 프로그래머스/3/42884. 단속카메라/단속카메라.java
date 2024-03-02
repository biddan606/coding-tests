import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int[][] sortedRoutes = new int[routes.length][];
        for (int r = 0; r < routes.length; r++) {
            sortedRoutes[r] = new int[routes[r].length];
            for (int c = 0; c < routes[c].length; c++) {
                sortedRoutes[r][c] = routes[r][c];
            }
        }
        
        Arrays.sort(sortedRoutes, (a, b) -> a[1] - b[1]);
            
        int count = 0;
        int min = Integer.MIN_VALUE;
        int max = Integer.MIN_VALUE;
        
        for (int[] r : sortedRoutes) {
            if (r[0] <= min && min <= r[1]) {
                continue;
            }
            if (r[0] <= max && max <= r[1]) {
                continue;
            }
            
            count++;
            min = Math.min(min, r[1]);
            max = Math.max(max, r[1]);
        }
        
        return count;
    }
}