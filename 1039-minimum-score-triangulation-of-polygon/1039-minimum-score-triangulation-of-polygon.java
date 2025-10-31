class Solution {
    private int[][] dp;

    public int minScoreTriangulation(int[] values) {
        dp = new int[values.length][values.length];

        return triangulate(values, 0, values.length - 1);
    }

    private int triangulate(int[] values, int leftBoundary, int rightBoundary) {
        if (leftBoundary + 2 > rightBoundary) {
            return 0;
        }
        if (dp[leftBoundary][rightBoundary] != 0) {
            return dp[leftBoundary][rightBoundary];
        }

        int min = Integer.MAX_VALUE;

        for (int mid = leftBoundary + 1; mid < rightBoundary; mid++) {
            int current = triangulate(values, leftBoundary, mid)
                + (values[leftBoundary] * values[mid] * values[rightBoundary])
                + triangulate(values, mid, rightBoundary);

            min = Math.min(min, current);
        }

        dp[leftBoundary][rightBoundary] = min;
        return min;
    }
}
