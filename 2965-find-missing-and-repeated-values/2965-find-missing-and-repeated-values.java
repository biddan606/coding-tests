class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[] appeared = new boolean[rows * cols + 1];

        int repeatedValue = -1;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int currentValue = grid[r][c];

                if (appeared[currentValue]) {
                    repeatedValue = currentValue;
                }
                appeared[currentValue] = true;
            }
        }

        int missingValue = -1;
        for (int n = 1; n < appeared.length; n++) {
            if (!appeared[n]) {
                missingValue = n;
            }
        }

        return new int[]{repeatedValue, missingValue};
    }
}
