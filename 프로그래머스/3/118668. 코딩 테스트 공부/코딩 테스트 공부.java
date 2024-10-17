class Solution {

    public int solution(int alp, int cop, int[][] problems) {
        // 최대 알고력과 코딩력을 구한다.
        int maxAlgo = alp;
        int maxCoding = cop;
        for (int[] p : problems) {
            maxAlgo = Math.max(maxAlgo, p[0]);
            maxCoding = Math.max(maxCoding, p[1]);
        }
        
        // [알고력][코딩력], 최대 알고력과 최대 코딩력만큼 배열을 할당하고 큰 값으로 초기화한다.
        int[][] dp = new int[maxAlgo + 1][maxCoding + 1];
        for (int i = 0; i <= maxAlgo; i++) {
            for (int j = 0; j <= maxCoding; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        // 시작 지점 설정
        dp[alp][cop] = 0;

        // 알고력과 코딩력이 목표치에 도달할 때까지 dp 갱신
        for (int algo = alp; algo <= maxAlgo; algo++) {
            for (int coding = cop; coding <= maxCoding; coding++) {
                if (algo < maxAlgo) {
                    dp[algo + 1][coding] = Math.min(dp[algo + 1][coding], dp[algo][coding] + 1);
                }
                if (coding < maxCoding) {
                    dp[algo][coding + 1] = Math.min(dp[algo][coding + 1], dp[algo][coding] + 1);
                }

                for (int[] p : problems) {
                    if (algo >= p[0] && coding >= p[1]) {
                        int nextAlgo = Math.min(maxAlgo, algo + p[2]);
                        int nextCoding = Math.min(maxCoding, coding + p[3]);
                        int cost = p[4];

                        dp[nextAlgo][nextCoding] = Math.min(dp[nextAlgo][nextCoding], dp[algo][coding] + cost);
                    }
                }
            }
        }

        return dp[maxAlgo][maxCoding];
    }

    public static void main(String[] args) {
        int alp = 0;
        int cop = 0;
        int[][] problems = {{0, 0, 2, 1, 2}, {4, 5, 3, 1, 2}, {4, 11, 4, 0, 2}, {10, 4, 0, 4, 2}};
        Solution solution = new Solution();

        int result = solution.solution(alp, cop, problems);
        System.out.println(result);
    }
}
