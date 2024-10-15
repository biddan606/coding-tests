class Solution {

    private static final int[][] DIRECTIONS = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    private int min = Integer.MAX_VALUE;
    private int rows;
    private int cols;

    public int solution(int[][] maze) {
        // 모든 경우의 수를 살펴본다.
        rows = maze.length;
        cols = maze[0].length;

        // 각 포인트를 구한다.
        int[] redStartPoint = null;
        int[] blueStartPoint = null;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (maze[r][c] == 1) {
                    redStartPoint = new int[]{r, c};
                } else if (maze[r][c] == 2) {
                    blueStartPoint = new int[]{r, c};
                }
            }
        }

        dfs(maze, redStartPoint, blueStartPoint, new boolean[rows][cols], new boolean[rows][cols], 0);

        return min == Integer.MAX_VALUE ? 0 : min;
    }

    private void dfs(int[][] maze, int[] redPoint, int[] bluePoint, boolean[][] visitedRed, boolean[][] visitedBlue,
            int depth) {
        int redRow = redPoint[0];
        int redCol = redPoint[1];
        int blueRow = bluePoint[0];
        int blueCol = bluePoint[1];

        if (maze[redRow][redCol] == 3 && maze[blueRow][blueCol] == 4) {
            min = Math.min(min, depth);
            return;
        }
        visitedRed[redRow][redCol] = true;
        visitedBlue[blueRow][blueCol] = true;

        for (int[] redDirection : DIRECTIONS) {
            int nextRedRow = redRow;
            int nextRedCol = redCol;
            if (maze[redRow][redCol] != 3) {
                nextRedRow = redRow + redDirection[0];
                nextRedCol = redCol + redDirection[1];
            }
            if (nextRedRow < 0 || nextRedRow >= rows
                    || nextRedCol < 0 || nextRedCol >= cols) {
                continue;
            }
            if (maze[nextRedRow][nextRedCol] == 5) {
                continue;
            }
            if (maze[nextRedRow][nextRedCol] != 3
                    && visitedRed[nextRedRow][nextRedCol]) {
                continue;
            }

            int[] nextRedPoint = new int[]{nextRedRow, nextRedCol};

            for (int[] blueDirection : DIRECTIONS) {
                int nextBlueRow = blueRow;
                int nextBlueCol = blueCol;
                if (maze[nextBlueRow][nextBlueCol] != 4) {
                    nextBlueRow = blueRow + blueDirection[0];
                    nextBlueCol = blueCol + blueDirection[1];
                }
                if (nextBlueRow < 0 || nextBlueRow >= rows
                        || nextBlueCol < 0 || nextBlueCol >= cols) {
                    continue;
                }
                if (maze[nextBlueRow][nextBlueCol] == 5) {
                    continue;
                }
                if (maze[nextBlueRow][nextBlueCol] != 4
                        && visitedBlue[nextBlueRow][nextBlueCol]) {
                    continue;
                }

                if (nextRedRow == nextBlueRow && nextRedCol == nextBlueCol) {
                    continue;
                }
                if (nextRedRow == blueRow && nextRedCol == blueCol
                        && nextBlueRow == redRow && nextBlueCol == redCol) {
                    continue;
                }

                int[] nextBluePoint = new int[]{nextBlueRow, nextBlueCol};
                if (depth + 1 < min) {
                    dfs(maze, nextRedPoint, nextBluePoint, visitedRed, visitedBlue, depth + 1);
                }
            }
        }

        visitedRed[redRow][redCol] = false;
        visitedBlue[blueRow][blueCol] = false;
    }

    public static void main(String[] args) {
        int[][] maze = {
                {1, 0, 2},
                {0, 0, 0},
                {5, 0, 5},
                {4, 0, 3}};
        Solution solution = new Solution();

        System.out.println(solution.solution(maze));
    }
}
