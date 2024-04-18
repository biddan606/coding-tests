class Solution {

    public int islandPerimeter(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Set<LineSegment> lineSegments = new HashSet<>();

        /*
        사각형 꼭짓점으로 선분을 만들어 저장한다.

        1. 사각형 조각의 꼭짓점을 4개로 나눈다.
            - 각 꼭짓점은 (x, y), (x + 1, y), (x, y + 1), (x + 1, y + 1)으로 나눌 수 있다.
        2. 이 꼭짓점들을 이어 선분의 값을 만든다.
            - (x, y)와 (x + 1, y)이 이어진 선분은 (x, y, x + 1, y) 값을 가진다.
            - 선분은 (startX, startY, endX, endY)를 가진다.
        */
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (grid[y][x] == 1) {
                    // up (x, y) -> (x + 1, y)
                    LineSegment up = new LineSegment(x, y, x + 1, y);
                    toggleLineSegment(lineSegments, up);

                    // down (x, y + 1) -> (x + 1, y + 1)
                    LineSegment down = new LineSegment(x, y + 1, x + 1, y + 1);
                    toggleLineSegment(lineSegments, down);

                    // left (x, y) -> (x, y + 1)
                    LineSegment left = new LineSegment(x, y, x, y + 1);
                    toggleLineSegment(lineSegments, left);

                    // right (x + 1, y) -> (x + 1, y + 1)
                    LineSegment right = new LineSegment(x + 1, y, x + 1, y + 1);
                    toggleLineSegment(lineSegments, right);
                }
            }
        }

        return lineSegments.size();
    }

    private void toggleLineSegment(Set<LineSegment> lineSegments, LineSegment lineSegment) {
        if (!lineSegments.add(lineSegment)) {
            lineSegments.remove(lineSegment);
        }
    }

    private static class LineSegment {

        final int startX;
        final int startY;
        final int endX;
        final int endY;

        public LineSegment(int startX, int startY, int endX, int endY) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            LineSegment that = (LineSegment) o;
            return startX == that.startX && startY == that.startY && endX == that.endX && endY == that.endY;
        }

        @Override
        public int hashCode() {
            int startXHash = startX & 0xFF;
            int startYHash = (startY << 8) & 0xFF00;
            int endXHash = (endX << 16) & 0xFF0000;
            int endYHash = (endY << 24) & 0xFF000000;

            return startXHash | startYHash | endXHash | endYHash;
        }
    }
}
