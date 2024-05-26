public class Solution {

    private static final int MOD = 1_000_000_000 + 7;
    private static final int ABSENT_LIMIT = 2;
    private static final int LATE_LIMIT = 3;

    public int checkRecord(int n) {
        int[][] dp = new int[ABSENT_LIMIT][LATE_LIMIT];
        dp[0][0] = 1;
        dp[1][0] = 1;
        dp[0][1] = 1;

        for (int day = 1; day < n; day++) {
            int[][] nextDp = new int[2][3];
            
            for (int absent = 0; absent < ABSENT_LIMIT; absent++) {
                for (int late = 0; late < LATE_LIMIT; late++) {
                    // 출석한 경우
                    nextDp[absent][0] += dp[absent][late];
                    nextDp[absent][0] %= MOD;

                    // 결석한 경우
                    if (absent > 0) {
                        nextDp[absent][0] += dp[absent - 1][late];
                        nextDp[absent][0] %= MOD;
                    }

                    // 지각한 경우
                    if (late > 0) {
                        nextDp[absent][late] += dp[absent][late - 1];
                        nextDp[absent][late] %= MOD;
                    }
                }
            }

            dp = nextDp;
        }

        int awardCases = 0;
        for (int absent = 0; absent < ABSENT_LIMIT; absent++) {
                for (int late = 0; late < LATE_LIMIT; late++) {
                    awardCases += dp[absent][late];
                    awardCases %= MOD;
                }
        }

        return awardCases;
    }
}
