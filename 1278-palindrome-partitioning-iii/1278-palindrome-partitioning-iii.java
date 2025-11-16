class Solution {
    /**
     * s: 입력 문자열
     * k: 분할할 팰린드롬의 개수
     * 반환값: s를 k개의 팰린드롬으로 분할하기 위해 필요한 최소 문자 변경 횟수
     */
    public int palindromePartition(String s, int k) {
        int n = s.length();

        // 1. 비용 배열(costs) 계산 최적화
        // costs[i][j]: 부분 문자열 s[i...j]를 팰린드롬으로 만드는 데 필요한 최소 변경 횟수
        int[][] costs = new int[n][n];
        for (int len = 2; len <= n; len++) { // 부분 문자열의 길이
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                // s[i]와 s[j]가 다르면 1, 같으면 0을 더함
                // 안쪽 부분 문자열(s[i+1...j-1])의 비용은 이미 계산되어 있음
                costs[i][j] = (s.charAt(i) == s.charAt(j) ? 0 : 1) + costs[i + 1][j - 1];
            }
        }

        // 2. DP 테이블 정의 및 초기화 단순화
        // dp[i][j]: 문자열의 첫 i개 문자(s[0...i-1])를 j개의 팰린드롬으로 분할하는 최소 비용
        // dp 배열의 크기를 (n+1) x (k+1)로 하여 인덱싱을 더 직관적으로 만듦
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            // 충분히 큰 값으로 초기화 (Integer.MAX_VALUE / 2는 오버플로우 방지)
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        }

        // 3. Base Case 설정 (j=0)
        // 0개의 문자를 0개로 분할하는 비용은 0
        dp[0][0] = 0;

        // 4. DP 점화식 계산 (변수명 가독성 개선)
        for (int currentPartitions = 1; currentPartitions <= k; currentPartitions++) { // 현재 분할 개수
            for (int end = currentPartitions; end <= n; end++) { // 현재 문자열의 끝 위치 (1-based)
                // 마지막 조각의 시작 위치를 모두 탐색
                for (int start = currentPartitions - 1; start < end; start++) {
                    // dp[end][currentPartitions] = min(
                    //     이전 값,
                    //     s[0...start-1]를 (currentPartitions-1)개로 나눈 비용 + s[start...end-1]을 1개로 만드는 비용
                    // )
                    dp[end][currentPartitions] = Math.min(
                        dp[end][currentPartitions],
                        dp[start][currentPartitions - 1] + costs[start][end - 1]
                    );
                }
            }
        }

        return dp[n][k];
    }
}