class Solution {
    public int[] resultsArray(int[] nums, int k) {
        int size = nums.length;
        int[][] dp = new int[size][size];

        for (int i = 0; i < size; i++) {
            dp[i][i] = nums[i];
        }

        for (int d = 1; d < k; d++) {
            for (int i = 0; i + d < size; i++) {
                dp[i][i + d] = -1;
                if (dp[i][i + d - 1] != -1 && nums[i + d - 1] + 1 == nums[i + d]) {
                    dp[i][i + d] = nums[i + d];
                }
            }
        }

        int[] result = new int[size - k + 1];
        
        for (int i = 0; i + k - 1 < size; i++) {
            result[i] = dp[i][i + k - 1];
        }
        return result;
    }
}
