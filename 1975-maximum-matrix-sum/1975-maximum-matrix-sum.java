class Solution {
    public long maxMatrixSum(int[][] matrix) {
        int negativeCount = 0;
        int minAbsoluteValue = Integer.MAX_VALUE;
        long absoluteSum = 0;
        for (int[] row : matrix) {
            for (int col : row) {
                if (col < 0) {
                    negativeCount++;
                }

                int absoluteColumn = Math.abs(col);
                absoluteSum += absoluteColumn;
                minAbsoluteValue = Math.min(minAbsoluteValue, absoluteColumn);
            }
        }

        if (negativeCount % 2 == 1) {
            absoluteSum -= minAbsoluteValue * 2;
        }
        return absoluteSum;
    }
}
