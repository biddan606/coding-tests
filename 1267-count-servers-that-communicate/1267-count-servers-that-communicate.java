class Solution {
    public static int countServers(int[][] grid) {
        int servers = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        
        int[] serversInRow = new int[rows];
        int[] serversInCol = new int[cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    serversInRow[r]++;
                    serversInCol[c]++;
                    servers++;
                }
            }
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1 && serversInRow[r] == 1 && serversInCol[c] == 1) {
                    servers--;
                }
            }
        }
        return servers;
    }
}
