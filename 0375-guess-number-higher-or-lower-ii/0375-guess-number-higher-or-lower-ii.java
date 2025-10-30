/**
 * 가독성을 높인 솔루션 클래스
 */
class Solution {
    /**
     * 1부터 n까지의 숫자 중 하나를 맞추기 위해 필요한 최소 비용을 계산합니다.
     * 이 문제는 동적 프로그래밍을 사용하여 해결할 수 있습니다.
     *
     * @param n 추측할 숫자의 최대 범위
     * @return 승리를 보장하는 데 필요한 최소 금액
     */
    public int getMoneyAmount(int n) {
        // dp[i][j]: i부터 j까지의 숫자 범위에서 승리를 보장하는 데 필요한 최소 비용
        // 배열 크기를 여유롭게 잡아 (guess + 1)과 같은 인덱스 접근 시 예외가 발생하지 않도록 함
        int[][] dp = new int[n + 2][n + 2];

        // length: 우리가 고려할 숫자의 범위 길이 (예: [1,2]의 길이는 2)
        // 길이가 2인 범위부터 n인 범위까지 순서대로 계산
        for (int length = 2; length <= n; length++) {
            // start: 범위의 시작 숫자
            for (int start = 1; start <= n - length + 1; start++) {
                int end = start + length - 1;

                // 현재 범위 [start, end]에 대한 최소 비용을 최댓값으로 초기화
                dp[start][end] = Integer.MAX_VALUE;

                // guess: 현재 범위 [start, end] 내에서 우리가 선택할 숫자
                // start부터 end까지 모든 숫자를 한번씩 선택해보며 최소 비용을 찾음
                for (int guess = start; guess <= end; guess++) {
                    // 만약 'guess' 숫자를 선택했다면, 지불해야 하는 비용은 'guess'
                    // 그 후, 정답이 'guess'보다 작은지 큰지에 따라 남은 범위가 결정됨
                    // 우리는 최악의 경우(더 많은 비용이 드는 쪽)를 대비해야 하므로 Math.max 사용
                    int costForThisGuess = guess + Math.max(dp[start][guess - 1], dp[guess + 1][end]);
                    
                    // 현재까지 계산된 최소 비용과 'guess'를 선택했을 때의 비용을 비교하여 더 작은 값으로 업데이트
                    dp[start][end] = Math.min(dp[start][end], costForThisGuess);
                }
            }
        }

        // 최종적으로 [1, n] 범위 전체에 대한 최소 비용을 반환
        return dp[1][n];
    }
}