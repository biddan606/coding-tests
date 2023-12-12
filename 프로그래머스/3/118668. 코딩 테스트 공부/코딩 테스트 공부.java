class Solution {

    public int solution(int alp, int cop, int[][] problems) {
        // 최대값 구하기
        int maxRow = alp;
        int maxCol = cop;
        for (int[] problem : problems) {
            maxRow = Math.max(maxRow, problem[0]);
            maxCol = Math.max(maxCol, problem[1]);
        }

        // 공부를 통한 능력 올리기
        int[][] costs = new int[maxRow - alp + 1][maxCol - cop + 1];
        for (int row = 0; row < costs.length; row++) {
            for (int col = 0; col < costs[0].length; col++) {
                costs[row][col] = row + col;
            }
        }

        // DP
        for (int row = 0; row < costs.length; row++) {
            for (int col = 0; col < costs[0].length; col++) {
                if (row + 1 < costs.length) {
                    costs[row + 1][col] = Math.min(costs[row][col] + 1, costs[row + 1][col]);
                }
                if (col + 1 < costs[0].length) {
                    costs[row][col + 1] = Math.min(costs[row][col] + 1, costs[row][col + 1]);
                }

                for (int[] problem : problems) {
                    if (row < Math.max(0, problem[0] - alp) || col < Math.max(0, problem[1] - cop)) {
                        continue;
                    }

                    int problemRow = Math.min(costs.length - 1, row + problem[2]);
                    int problemCol = Math.min(costs[0].length - 1, col + problem[3]);

                    costs[problemRow][ problemCol] = Math.min(
                            costs[problemRow][problemCol],
                            costs[row][col] + problem[4]
                    );
                }
            }
        }

        return costs[costs.length - 1][costs[0].length - 1];
    }

    public static void main(String[] args) {
        int alp = 0;
        int cop = 0;
        int[][] problems = {
                {0, 0, 2, 1, 2},
                {4, 5, 3, 1, 2},
                {4, 11, 4, 0, 2},
                {10, 4, 0, 4, 2}
        };

        Solution solution = new Solution();
        int result = solution.solution(alp, cop, problems);

        System.out.println(result);
    }
}
