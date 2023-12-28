class Solution {

    private static int[][] DIRECTS = {
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {0, -1},
            {0, 0},
            {0, 1},
            {1, -1},
            {1, 0},
            {1, 1}
    };

    public int solution(int[][] board) {
        // 위험한 지역을 찾는다.
        boolean[][] dangerousArea = new boolean[board.length][board[0].length];
        for (int row = 0; row < dangerousArea.length; row++) {
            for (int col = 0; col < dangerousArea[0].length; col++) {
                if (board[row][col] == 0) {
                    continue;
                }

                for (int[] direct : DIRECTS) {
                    int dangerousRow = direct[0] + row;
                    int dangerousCol = direct[1] + col;

                    if (isNotWithin(dangerousArea, dangerousRow, dangerousCol)) {
                        continue;
                    }

                    dangerousArea[dangerousRow][dangerousCol] = true;
                }
            }
        }

        // 안전한 지역 개수를 구한다.
        int safePointCount = 0;
        for (boolean[] dangerousRow : dangerousArea) {
            for (int col = 0; col < dangerousArea[0].length; col++) {
                if (!dangerousRow[col]) {
                    safePointCount++;
                }
            }
        }

        return safePointCount;
    }

    private boolean isNotWithin(boolean[][] map, int row, int col) {
        if (row < 0 || map.length <= row) {
            return true;
        }

        return col < 0 || map[0].length <= col;
    }
}


/*
[
    [0, 0, 0, 0, 0], 
    [0, 0, 0, 0, 0], 
    [0, x, x, x, 0], 
    [0, x, 1, x, 0], 
    [0, x, x, x, 0]
]
result: 16
*/
