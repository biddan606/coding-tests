import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] memory = new int[m + 1][n + 1];
        for (int[] row : memory) {
            Arrays.fill(row, -1);
        }
        
        boolean[][] isPuddle = new boolean[m + 1][n + 1];
        for (int[] puddle : puddles) {
            isPuddle[puddle[0]][puddle[1]] = true;
        }
        
        return count(1, 1, m, n, isPuddle, memory);
    }
    
    private int count(int x, int y, int w, int h, boolean[][] isPuddle, int[][] memory) {
        if (x > w || y > h) {
            return 0;
        }
        if (isPuddle[x][y]) {
            return 0;
        }
        
        if (memory[x][y] != -1) {
            return memory[x][y];
        }
        if (x == w && y == h) {
            return 1;
        }
        
        memory[x][y] = (count(x + 1, y, w, h, isPuddle, memory) + count(x, y + 1, w, h, isPuddle, memory)) % 1_000_000_007;
        return memory[x][y];
    }
}