class Solution {

    private static final int[][] DIRECTIONS = {
            {1, 0},
            {-1, 0},
            {0, -1},
            {0, 1}
    };

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        /*
        a부터 게임을 진행한다. a가 게임을 이길 수 있다면, 최소 이동 횟수를 리턴한다.
        이길 수 없다면 최대 이동 횟수를 리턴한다.
        */
        return game(board, aloc, bloc).turns;
    }

    private GameResult game(int[][] board, int[] self, int[] other) {
        if (board[self[0]][self[1]] == 0 || isImmovable(board, self)) {
            return new GameResult(false, 0);
        }
        board[self[0]][self[1]] = 0;

        boolean win = false;
        int maxTurns = 0;
        int minTurns = Integer.MAX_VALUE;

        for (int[] d : DIRECTIONS) {
            int x = self[0] + d[0];
            int y = self[1] + d[1];

            if (!isWithinRange(board, x, y)) {
                continue;
            }

            GameResult progressResult = game(board, other, new int[]{x, y});
            if (progressResult.win) {
                maxTurns = Math.max(maxTurns, progressResult.turns + 1);
            } else {
                win = true;
                minTurns = Math.min(minTurns, progressResult.turns + 1);
            }
        }

        board[self[0]][self[1]] = 1;
        if (win) {
            return new GameResult(true, minTurns);
        }
        return new GameResult(false, maxTurns);
    }

    private boolean isImmovable(int[][] board, int[] self) {
        for (int[] d : DIRECTIONS) {
            int x = self[0] + d[0];
            int y = self[1] + d[1];

            if (isWithinRange(board, x, y) && board[x][y] == 1) {
                return false;
            }
        }

        return true;
    }

    private boolean isWithinRange(int[][] board, int x, int y) {
        return x >= 0 && x < board.length
                && y >= 0 && y < board[0].length;
    }

    private static class GameResult {

        final boolean win;
        final int turns;

        public GameResult(boolean win, int turns) {
            this.win = win;
            this.turns = turns;
        }
    }
}
