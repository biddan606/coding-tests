class Solution {
    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        // 'dp' 대신 'memo'를 사용하여 메모이제이션의 의도를 명확히 함
        int[][] memo = new int[n][n];
        
        // 전체 다각형(0부터 n-1까지)에 대한 문제 해결을 시작
        return solve(values, 0, n - 1, memo);
    }

    /**
     * values 배열의 i부터 j까지의 꼭짓점으로 이루어진 다각형의
     * 삼각분할 최소 점수를 반환합니다.
     * @param values 꼭짓점 값 배열
     * @param i      다각형의 시작 꼭짓점 인덱스
     * @param j      다각형의 끝 꼭짓점 인덱스
     * @param memo   계산 결과를 저장하기 위한 메모이제이션 테이블
     * @return       최소 점수
     */
    private int solve(int[] values, int i, int j, int[][] memo) {
        // [기저 사례] 꼭짓점이 3개 미만(선 또는 점)이면 삼각형을 만들 수 없으므로 비용은 0
        if (i + 1 >= j) {
            return 0;
        }

        // [메모이제이션] 이미 계산된 문제라면 저장된 값을 즉시 반환
        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        int minScore = Integer.MAX_VALUE;

        // [점화식] i와 j 사이의 모든 꼭짓점 k를 순회하며
        // (i, k, j)를 마지막 삼각형으로 선택하는 경우를 모두 테스트
        for (int k = i + 1; k < j; k++) {
            int currentScore = solve(values, i, k, memo)
                             + (values[i] * values[k] * values[j])
                             + solve(values, k, j, memo);
            
            minScore = Math.min(minScore, currentScore);
        }

        // 계산된 결과를 저장하고 반환
        return memo[i][j] = minScore;
    }
}