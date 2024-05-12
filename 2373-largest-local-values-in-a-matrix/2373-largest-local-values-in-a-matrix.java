class Solution {
    public int[][] largestLocal(int[][] grid) {
        int n = grid.length;
        
        int[][] maxLocal = new int[n - 2][n - 2];
        for (int row = 0; row < maxLocal.length; row++) {
            for (int col = 0; col < maxLocal[row].length; col++) {
                maxLocal[row][col] = getMax(grid, row, row + 2, col, col + 2);
            }
        }

        return maxLocal;
    }

    private int getMax(int[][] grid, int startRow, int endRow, int startCol, int endCol) {
        int max = 0;
        
        for (int row = startRow; row <= endRow; row++) {
            for (int col = startCol; col <= endCol; col++) {
                max = Math.max(max, grid[row][col]);
            }
        }

        return max;
    }
}
