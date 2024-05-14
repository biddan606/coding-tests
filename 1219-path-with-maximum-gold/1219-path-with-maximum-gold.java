class Solution {
    private static int[][] DIRECTIONS = {
        {0, -1},
        {0, 1},
        {-1, 0},
        {1, 0}
    };

    public int getMaximumGold(int[][] grid) {
        int maxGold = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                maxGold = Math.max(maxGold, backtracking(grid, row, col));
            }
        }

        return maxGold;
    }

    private static int backtracking(int[][] grid, int row, int col) {
        if (!validateRange(grid, row, col) || grid[row][col] == 0) {
            return 0;
        }

        int originalGold = grid[row][col];
        int maxGold = originalGold;
        grid[row][col] = 0;

        for (int[] direction : DIRECTIONS) {
            int nextRow = direction[0] + row;
            int nextCol = direction[1] + col;

            maxGold = Math.max(maxGold, originalGold + backtracking(grid, nextRow, nextCol));
        }

        grid[row][col] = originalGold;

        return maxGold;
    }

    private static boolean validateRange(int[][] grid, int row, int col) {
        if (row < 0 || grid.length <= row) {
            return false;
        }

        return 0 <= col && col < grid[row].length;
    }
}
