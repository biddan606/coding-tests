class Solution {
    private static final Point[] DIRECTIONS = {
        new Point(-1, 0),
        new Point(1, 0),
        new Point(0, -1),
        new Point(0, 1)
    };

    public int numIslands(char[][] grid) {
        int numberOfIslands = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == '0' || visited[r][c]) {
                    continue;
                }

                visit(grid, r, c, visited);
                numberOfIslands++;
            }
        }

        return numberOfIslands;
    }

    private void visit(char[][] grid, int startRow, int startCol, boolean[][] visited) {
        Deque<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(startRow, startCol));

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            int currentRow = p.row;
            int currentCol = p.col;
            
            if (visited[currentRow][currentCol]) {
                continue;
            }
            visited[currentRow][currentCol] = true;

            for (Point d : DIRECTIONS) {
                int nextRow = currentRow + d.row;
                int nextCol = currentCol + d.col;
                Point next = new Point(nextRow, nextCol);
                
                if (!isWithinRange(grid, next)) {
                    continue;
                }
                if (grid[next.row][next.col] == '0') {
                    continue;
                }

                queue.offer(next);
            }
        }
    }

    private boolean isWithinRange(char[][] grid, Point p) {
        if (p.row < 0 || grid.length <= p.row) {
            return false;
        }

        return 0 <= p.col && p.col < grid[0].length;
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