import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {

    private static Point[] DELTA_POINTS = {
            new Point(-1, 0),
            new Point(1, 0),
            new Point(0, -1),
            new Point(0, 1)
    };

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int rowSize = grid2.length;
        int colSize = grid2[0].length;

        /*
        grid2를 돌며, sub-island가 될 수 있는지 검사한다.
        sub-island가 될 수 있다면, count++한다.
        */
        boolean[][] visited = new boolean[rowSize][colSize];
        int subIslandCount = 0;

        for (int r = 0; r < rowSize; r++) {
            for (int c = 0; c < colSize; c++) {
                if (visited[r][c] || grid2[r][c] == 0) {
                    continue;
                }
                List<Point> subIslandPoints = extractPoints(grid2, visited, r, c);

                if (isSubIsland(grid1, subIslandPoints)) {
                    subIslandCount++;
                }
            }
        }

        return subIslandCount;
    }

    private List<Point> extractPoints(int[][] grid, boolean[][] visited, int startRow, int startCol) {
        List<Point> result = new ArrayList<>();
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(startRow, startCol));

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            if (!isWithIn(grid, current) ||
                    visited[current.row][current.col] ||
                    grid[current.row][current.col] == 0) {
                continue;
            }

            visited[current.row][current.col] = true;
            result.add(new Point(current.row, current.col));

            for (Point dp : DELTA_POINTS) {
                queue.offer(new Point(current.row + dp.row, current.col + dp.col));
            }
        }

        return result;
    }

    private boolean isWithIn(int[][] grid, Point point) {
        return point.row >= 0 &&
                point.row < grid.length &&
                point.col >= 0 &&
                point.col < grid[0].length;
    }

    private boolean isSubIsland(int[][] mainGrid, List<Point> subIslandPoints) {
        return subIslandPoints.stream()
                .allMatch(p -> mainGrid[p.row][p.col] == 1);
    }

    private static class Point {

        final int row;
        final int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
