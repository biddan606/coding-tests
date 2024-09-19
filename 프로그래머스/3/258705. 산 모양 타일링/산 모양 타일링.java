class Solution {

    public int solution(int n, int[] tops) {
        int[][] dp = new int[n + 1][2];
        dp[0][0] = 1;
        int mod = 10_007;

        for (int i = 1; i <= n; i++) {
            int prevSum = dp[i - 1][0] + dp[i - 1][1];

            dp[i][0] = prevSum + dp[i - 1][0];
            if (tops[i - 1] == 1) {
                dp[i][0] += prevSum;
            }
            dp[i][1] = prevSum;

            dp[i][0] %= mod;
            dp[i][1] %= mod;
        }

        return (dp[n][0] + dp[n][1]) % mod;
    }

    public static void main(String[] args) {
        int n = 2;
        int[] tops = {0, 1};
        Solution solution = new Solution();

        int result = solution.solution(n, tops);
        System.out.println(result);
    }
}
