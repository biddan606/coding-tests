class Solution {
    public int maxCoins(int[] nums) {
        // 1. 가독성을 위해 배열 길이를 변수로 추출
        int n = nums.length;
        int[] paddedNums = new int[n + 2];

        // 2. 경계값 1 추가
        paddedNums[0] = 1;
        paddedNums[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            paddedNums[i + 1] = nums[i];
        }

        // 3. DP 테이블 초기화 (0으로 자동 초기화)
        // dp[i][j]: paddedNums의 i와 j 인덱스 사이(i, j 제외)의 풍선을 모두 터뜨렸을 때의 최대 점수
        int[][] dp = new int[n + 2][n + 2];

        // 원본 배열에 해당하는 범위는 (0, n+1)
        return solve(paddedNums, dp, 0, n + 1);
    }

    /**
     * paddedNums의 (leftBoundary, rightBoundary) 범위 내 풍선들을 터뜨려 얻는 최대 점수를 반환합니다.
     */
    private int solve(int[] paddedNums, int[][] dp, int leftBoundary, int rightBoundary) {
        // 4. 더 이상 터뜨릴 풍선이 없는 경우
        if (leftBoundary + 1 == rightBoundary) {
            return 0;
        }

        // 5. 이미 계산된 값이면 즉시 반환 (Memoization)
        if (dp[leftBoundary][rightBoundary] != 0) {
            return dp[leftBoundary][rightBoundary];
        }

        int max = 0;
        // 6. (leftBoundary, rightBoundary) 사이의 풍선 k를 가장 마지막에 터뜨리는 경우를 모두 시도
        for (int k = leftBoundary + 1; k < rightBoundary; k++) {
            // k를 마지막에 터뜨릴 때의 점수 = 왼쪽 부분 문제 + 오른쪽 부분 문제 + k를 터뜨리는 점수
            int currentCoins = solve(paddedNums, dp, leftBoundary, k)
                             + solve(paddedNums, dp, k, rightBoundary)
                             + (paddedNums[leftBoundary] * paddedNums[k] * paddedNums[rightBoundary]);

            max = Math.max(max, currentCoins);
        }

        dp[leftBoundary][rightBoundary] = max;
        return max;
    }
}