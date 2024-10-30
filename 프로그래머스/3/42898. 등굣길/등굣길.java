class Solution {
    public int solution(int rows, int cols, int[][] puddles) {
        // 물에 잠긴 맵 만들기
        boolean[][] puddled = new boolean[rows][cols];
        for (int[] puddle : puddles) {
            int row = puddle[0] - 1;
            int col = puddle[1] - 1;
            puddled[row][col] = true;
        }
        
        // 최소값으로 갈라면 오른쪽이나 아래로만 이동해야 함
        // 물에 잠긴 곳은 이동하지 못함
        int[][] dp = new int[rows][cols];
        dp[0][0] = 1;
        // row=0 초기화
        for (int c = 1; c < cols; c++) {
            if (puddled[0][c]) {
                continue;
            }
            dp[0][c] = dp[0][c - 1];
        }
        // col=0 초기화
        for (int r = 1; r < rows; r++) {
            if (puddled[r][0]) {
                continue;
            }
            dp[r][0] = dp[r - 1][0];
        }
        
        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                if (puddled[r][c]) {
                    continue;
                }
                dp[r][c] = (dp[r - 1][c] + dp[r][c - 1]) % 1_000_000_007;
            }
        }
        
        return dp[rows - 1][cols - 1];
    }
}
