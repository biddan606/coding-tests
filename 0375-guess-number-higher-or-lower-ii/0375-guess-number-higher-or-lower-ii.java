class Solution {
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];

        for (int range = 1; range < n; range++) {
            for (int start = 1; start <= n - range; start++) {
                int end = start + range;
                dp[start][end] = Math.min(start + dp[start + 1][end], dp[start][end - 1] + end);

                for (int choiceNumber = start + 1; choiceNumber < end; choiceNumber++) {
                    int max = Math.max(dp[start][choiceNumber - 1], dp[choiceNumber + 1][end]);
                    dp[start][end] = Math.min(dp[start][end], max + choiceNumber);
                }
            }
        }

        return dp[1][n];
    }
}
