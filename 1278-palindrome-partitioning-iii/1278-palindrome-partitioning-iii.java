class Solution {
    // s 문자열 -> k개 팰린드롬로 분할
    // k개의 팰린드롬을 만들어야 함
    public int palindromePartition(String s, int k) {
        int[][] costs = new int[s.length()][s.length()];
        for (int i = 0; i < costs.length; i++) {
            for (int j = i; j < costs[i].length; j++) {
                costs[i][j] = calculateCost(s, i, j);
            }
        }

        int[][] dp = new int [s.length()][k + 1];
        for (int l = 1; l < s.length(); l++) {
            for (int c = 0; c <= k; c++) {

            }
        }

        for (int i = 0; i < s.length(); i++) {
            for (int j = 1; j <= k; j++) {
                if (i == 0 && j == 1) {
                    continue;
                }

                dp[i][j] = Integer.MAX_VALUE / 2;
            }
        }

        for (int i = 0; i < s.length(); i++) {
            dp[i][1] = costs[0][i];
        }

        for (int c = 2; c <= k; c++) {
            for (int l = c - 1; l < s.length(); l++) {
                for (int start = c - 1; start <= l; start++) {
                    dp[l][c] = Math.min(dp[l][c], dp[start - 1][c - 1] + costs[start][l]);
                }
            }
        }

        return dp[s.length() - 1][k];
    }

    private int calculateCost(String str, int stratInclusive, int endInclusive) {
        int left = stratInclusive;
        int right = endInclusive;
        int cost = 0;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                cost++;        
            }

            left++;
            right--;
        }

        return cost;
    }
}