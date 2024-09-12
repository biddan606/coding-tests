class Solution {

    static class Result {

        boolean isWin;
        int moveCnt;

        public Result(boolean isWin, int moveCnt) {
            this.isWin = isWin;
            this.moveCnt = moveCnt;
        }
    }

    int rowLength;
    int colLength;

    int[] cx = {-1, 1, 0, 0};
    int[] cy = {0, 0, -1, 1};

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        rowLength = board.length;
        colLength = board[0].length;

        return dfs(board, aloc[0], aloc[1], bloc[0], bloc[1], 0, 0).moveCnt;
    }

    public Result dfs(int[][] board, int ax, int ay, int bx, int by, int aDepth, int bDepth) {

        int winCnt = 5 * 5;
        int loseCnt = aDepth + bDepth;
        boolean finalWin = false;

        if (aDepth == bDepth && board[ax][ay] == 1) {
            for (int i = 0; i < 4; i++) {
                int newX = ax + cx[i];
                int newY = ay + cy[i];

                if (newX < 0 || newX >= rowLength || newY < 0 || newY >= colLength) {
                    continue;
                }
                if (board[newX][newY] == 0) {
                    continue;
                }
                board[ax][ay] = 0;
                Result result = dfs(board, newX, newY, bx, by, aDepth + 1, bDepth);
                board[ax][ay] = 1;

                if (!result.isWin) {
                    finalWin = true;
                    winCnt = Math.min(result.moveCnt, winCnt);
                } else {
                    loseCnt = Math.max(result.moveCnt, loseCnt);
                }
            }
        } else if (aDepth > bDepth && board[bx][by] == 1) {
            for (int i = 0; i < 4; i++) {
                int newX = bx + cx[i];
                int newY = by + cy[i];

                if (newX < 0 || newX >= rowLength || newY < 0 || newY >= colLength) {
                    continue;
                }
                if (board[newX][newY] == 0) {
                    continue;
                }
                board[bx][by] = 0;
                Result result = dfs(board, ax, ay, newX, newY, aDepth, bDepth + 1);
                board[bx][by] = 1;
                if (!result.isWin) {
                    finalWin = true;
                    winCnt = Math.min(result.moveCnt, winCnt);
                } else {
                    loseCnt = Math.max(result.moveCnt, loseCnt);
                }
            }

        }

        return new Result(finalWin, finalWin ? winCnt : loseCnt);


    }
}
