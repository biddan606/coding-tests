class Solution {
    /*
    # 문제 이해
    가장 큰 정사각형의 길이를 반환해야 한다

    # 풀이 접근
    전부 검사 -> 모든 점으로부터 크기를 키워가며 최대 정사각형을 구한다
    모든 점 -> O(n * m)
    크기를 키워가며 -> (n * m)

    O((nm)^2) -> 90_000 * 90_000 X

    예제) 정사각형의 길이가 3
    1 1 1
    1 1 1
    1 1 1

    1 1 1 1
    1 1 1 1
    1 1 1 1
    1 1 1 1

    대각 왼쪽 위가 완벽한 정사각형이라면 크기가 커질 수 있다
    작은 쪽이 있다면 작은 쪽만큼

    # 구현 스텝
    1. matrix를 순회한다
        2. 현재 값이 0이라면 continue, 1이라면 아래 진행
            3. 내 왼쪽대각선, 왼쪽, 위 값을 추출한다
            4. 추출한 값 중 작은 값 + 1로 갱신한다
    5. 최대값을 구하여 반환한다
    */
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int max = 0;
        int[][] dp = new int[rows + 1][cols + 1];

        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= cols; c++) {
                if (matrix[r - 1][c - 1] == '0') {
                    continue;
                }

                int diagonal = dp[r - 1][c - 1];
                int left = dp[r][c - 1];
                int top = dp[r - 1][c];

                int min = Math.min(diagonal, left);
                min = Math.min(min, top);

                dp[r][c] = min + 1;

                max = Math.max(dp[r][c], max);
            }
        }

        return max * max;
    }
}
