class Solution {
    // row가 0으로 시작하면 변환한다.
    // col에 0이 많다면 변환한다.
    public int matrixScore(int[][] grid) {
        for (int[] row : grid) {
            if (row[0] == 0) {
                toggle(row);
            }
        }

        int halfRows = grid.length / 2;
        for (int col = 0; col < grid[0].length; col++) {
            int currentOnes = 0;

            for (int[] row : grid) {
                if (row[col] == 1) {
                    currentOnes++;
                }
            }

            if (currentOnes <= halfRows) {
                toggle(grid, col);
            }
        }

        return sum(grid);
    }

    private void toggle(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] == 0 ? 1 : 0;
        }
    }

    private void toggle(int[][] grid, int col) {
        for (int row = 0; row < grid.length; row++) {
            grid[row][col] = grid[row][col] == 0 ? 1 : 0;
        }
    }

    private int sum(int[][] grid) {
        int total = 0;

        for (int[] row : grid) {
            int current = 0;

            for (int col = 0; col < row.length; col++) {
                current = current * 2 + row[col];
            }

            total += current;
        }

        return total;
    }
}
