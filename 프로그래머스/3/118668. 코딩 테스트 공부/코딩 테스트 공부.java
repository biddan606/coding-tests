class Solution {

    public int solution(int alp, int cop, int[][] problems) {
        // 최대 알고력과 코딩력을 구한다.
        int maxAlgo = alp;
        int maxCoding = cop;
        for (int[] p : problems) {
            maxAlgo = Math.max(maxAlgo, p[0]);
            maxCoding = Math.max(maxCoding, p[1]);
        }

        // [알고력][코딩력], 최대 알고력과 최대 코딩력만큼 배열을 할당한다.
        int[][] dp = new int[maxAlgo + 1][maxCoding + 1];

        // dp 초기값 설정, 초기값은 기본 문제들을 풀었을 때 필요한 시간을 의미한다.
        for (int algo = alp; algo < dp.length; algo++) {
            for (int coding = cop; coding < dp[algo].length; coding++) {
                dp[algo][coding] = algo - alp + coding - cop;
            }
        }

        for (int algo = alp; algo < dp.length; algo++) {
            for (int coding = cop; coding < dp[algo].length; coding++) {
                for (int[] p : problems) {
                    int requiredAlp = p[0];
                    int requiredCop = p[1];
                    if (algo < requiredAlp || coding < requiredCop) {
                        continue;
                    }
                    
                    int nextAlgo = Math.min(maxAlgo, algo + p[2]);
                    int nextCoding = Math.min(maxCoding, coding + p[3]);
                    int cost = p[4];
                    
                    dp[nextAlgo][nextCoding] = Math.min(dp[nextAlgo][nextCoding], dp[algo][coding] + cost);
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
