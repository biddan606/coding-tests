class Solution {
    /**
    - 첫번째 로봇은 두번째 로봇의 점수 최소화
    - 두번째 로봇은 최대 점수

    grid.row는 최대 2 이므로
    첫번쨰 로봇은 두번째 로봇이 획득하는 값을 2개로 분리할 수 있다.
    [0][c]~[0][grid.length - 1]과 [1][0]~[1][c-2]
    두 값의 최대값이 최소값이 되게 만들어야 한다.
     */
    public long gridGame(int[][] grid) {
        var firstPathBranch = findFirstPath(grid);
        move(grid, firstPathBranch);

        // 두번째 값 획득
        long[] cumulativeLines = new long[2];
        int columnLength = grid[0].length;
        for (int i = 0; i < columnLength; i++) {
            cumulativeLines[0] += grid[0][i];
            cumulativeLines[1] += grid[1][i];
        }
        return Math.max(cumulativeLines[0], cumulativeLines[1]);
    }

    private int findFirstPath(int[][] grid) {
        int columnLength = grid[0].length;

        // 누적합을 구한다
        long[][] dp = new long[2][columnLength];
        dp[0][0] = grid[0][0];
        dp[1][0] = grid[1][0];
        for (int i = 1; i < columnLength; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
            dp[1][i] = dp[1][i - 1] + grid[1][i];
        }

        // 두번째 값이 최소화가 되는 값을 찾는다
        long minScore = dp[0][columnLength - 1];
        int branch = 0;
        for (int i = 1; i < columnLength; i++) {
            long firstLineScore = dp[0][columnLength - 1] - dp[0][i];
            long secondLineScore = dp[1][i - 1];

            long currentMaxScore = Math.max(firstLineScore, secondLineScore);
            if (currentMaxScore < minScore) {
                minScore = currentMaxScore;
                branch = i;
            }
        }

        return branch;
    }

    private void move(int[][] grid, int branch) {
        Arrays.fill(grid[0], 0, branch + 1, 0);
        Arrays.fill(grid[1], branch, grid[1].length, 0);
    }
}
