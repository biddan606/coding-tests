import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] pathCounts = new int[n][m];
        for (int[] row : pathCounts) {
            Arrays.fill(row, -1);
        }
        
        pathCounts[0][0] = 1;
        for (int[] puddle : puddles) {
            pathCounts[puddle[1] - 1][puddle[0] - 1] = 0;
        }
        
        for (int row = 1; row < n; row++) {
            if (pathCounts[row][0] == 0) {
                    continue;
            }
            
            pathCounts[row][0] = pathCounts[row - 1][0];
        }
        for (int col = 1; col < m; col++) {
            if (pathCounts[0][col] == 0) {
                    continue;
            }
            
            pathCounts[0][col] = pathCounts[0][col - 1];
        }
        
        for (int row = 1; row < pathCounts.length; row++) {
            for (int col = 1; col < pathCounts[row].length; col++) {
                if (pathCounts[row][col] == 0) {
                    continue;
                }
                
                pathCounts[row][col] = (pathCounts[row - 1][col] + pathCounts[row][col - 1]) % 1_000_000_007;
            }
        }
        
        return pathCounts[pathCounts.length - 1][pathCounts[pathCounts.length - 1].length - 1];
    }
}
/*
1  1  1  1
1  0 -1 -1
1 -1 -1 -1

1  1  1  1
1  0  1  2
1  1  2  4
*/