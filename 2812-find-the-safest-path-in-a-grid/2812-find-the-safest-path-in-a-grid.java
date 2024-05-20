class Solution {
    private Point[] DIRECTIONS = {new Point(-1, 0), new Point(1, 0), new Point(0, -1), new Point(0, 1)};
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int[][] manhattanDistances = getManhattanDistances(grid);

        Point start = new Point(0, 0);
        Point end = new Point(manhattanDistances.length - 1, manhattanDistances[0].length - 1);
        return getMaxSafenessFactor(manhattanDistances, start, end);
    }

    private int[][] getManhattanDistances(List<List<Integer>> grid) {
        int rows = grid.size();
        int cols = grid.get(0).size();
        int[][] manhattanDistances = new int[rows][cols];
        for (int[] row : manhattanDistances) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        Queue<Point> queue = new LinkedList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid.get(row).get(col) == 1) {
                    queue.offer(new Point(row, col));
                    manhattanDistances[row][col] = 0;
                }
            }
        }
        
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            int currentDistance = manhattanDistances[current.row][current.col];

            for (Point d : DIRECTIONS) {
                Point next = new Point(current.row + d.row, current.col + d.col);
                if (!validateRange(grid, next) || manhattanDistances[next.row][next.col] != Integer.MAX_VALUE) {
                    continue;
                }

                manhattanDistances[next.row][next.col] = currentDistance + 1;
                queue.offer(next);
            }
        }

        return manhattanDistances;
    }

    private boolean validateRange(List<List<Integer>> grid, Point p) {
        if (p.row < 0 || grid.size() <= p.row) {
            return false;
        }

        return 0 <= p.col && p.col < grid.get(0).size();
    }

    private int getMaxSafenessFactor(int[][] manhattanDistances, Point start, Point end) {
        PriorityQueue<HeapElement> maxHeap = new PriorityQueue<>((a, b) -> b.safenessFactor - a.safenessFactor);
        boolean[][] visited = new boolean[manhattanDistances.length][manhattanDistances[0].length];

        visited[start.row][start.col] = true;
        maxHeap.offer(new HeapElement(manhattanDistances[start.row][start.col], start));

        while (!maxHeap.isEmpty()) {
            HeapElement e = maxHeap.poll();
            
            if (e.current.row == end.row && e.current.col == end.col) {
                return e.safenessFactor;
            }

            for (Point d : DIRECTIONS) {
                Point next = new Point(e.current.row + d.row, e.current.col + d.col);
                if (!validateRange(manhattanDistances, next) || visited[next.row][next.col]) {
                    continue;
                }

                visited[next.row][next.col] = true;
                int newSafenessFactor = Math.min(e.safenessFactor, manhattanDistances[next.row][next.col]);
                maxHeap.offer(new HeapElement(newSafenessFactor, next));
            }
        }

        return -1;
    }

    private boolean validateRange(int[][] map, Point p) {
        if (p.row < 0 || map.length <= p.row) {
            return false;
        }

        return 0 <= p.col && p.col < map[p.row].length;
    }

    private static class Point {
        final int row;
        final int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static class HeapElement {
        final int safenessFactor;
        final Point current;

        public HeapElement(int safenessFactor, Point current) {
            this.safenessFactor = safenessFactor;
            this.current = current;
        }
    }
}
