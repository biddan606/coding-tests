class Solution {
    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        int[][] dp = new int[n][n];

        // len: 다각형을 구성하는 꼭짓점의 개수 (체인의 길이)
        // 가장 작은 다각형은 삼각형(len=3)이므로 3부터 시작
        for (int len = 3; len <= n; len++) {
            // i: 다각형의 시작 꼭짓점
            for (int i = 0; i <= n - len; i++) {
                // j: 다각형의 끝 꼭짓점
                int j = i + len - 1;
                
                // 해당 범위의 최소 점수를 계산하기 위해 초깃값 설정
                dp[i][j] = Integer.MAX_VALUE;

                // k: (i, j)를 경계로 하는 마지막 삼각형(i, k, j)의 중간 꼭짓점
                // 이 루프는 점화식을 그대로 구현한 것
                for (int k = i + 1; k < j; k++) {
                    int score = dp[i][k] 
                              + (values[i] * values[k] * values[j]) 
                              + dp[k][j];
                              
                    dp[i][j] = Math.min(dp[i][j], score);
                }
            }
        }

        // 모든 계산이 완료되면 dp[0][n-1]에 전체 다각형의 최소 점수가 저장됨
        return dp[0][n - 1];
    }
}