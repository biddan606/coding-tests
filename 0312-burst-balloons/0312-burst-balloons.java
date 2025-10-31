class Solution {
    int[] ballons;
    int[][] dp;

    public int maxCoins(int[] nums) {
        // [3, 1, 5, 8] -> [1, 3, 1, 5, 8, 1]
        ballons = new int[nums.length + 2];
        
        ballons[0] = 1;
        ballons[ballons.length - 1] = 1;
        for (int i = 0; i < nums.length; i++) {
            ballons[i + 1] = nums[i];
        }

        dp = new int[ballons.length][ballons.length];

        return burst(1, ballons.length - 2);
    }

    private int burst(int startInclusive, int endInclusive) {
        if (startInclusive > endInclusive) {
            return 0;
        }
        if (dp[startInclusive][endInclusive] != 0) {
            return dp[startInclusive][endInclusive];
        }

        int left = startInclusive - 1;
        int right = endInclusive + 1;
        int max = 0;
        
        for (int i = startInclusive; i <= endInclusive; i++) {
            int current = burst(startInclusive, i - 1)
                + (ballons[left] * ballons[i] * ballons[right])
                + burst(i + 1, endInclusive);

            max = Math.max(max, current);
        }

        dp[startInclusive][endInclusive] = max;
        return max;
    } 
}
