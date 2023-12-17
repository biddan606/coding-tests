import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class Solution {

    public String[] solution(int[][] line) {
        List<Point> contactPoints = new ArrayList<>();

        // 모든 직선 쌍을 순회한다
        for (int i = 0; i < line.length; i++) {
            for (int j = i + 1; j < line.length; j++) {
                // 교차점을 구한다
                Optional<Point> optionalIntersectedPoint = intersect(line[i], line[j]);
                if (optionalIntersectedPoint.isEmpty()) {
                    continue;
                }

                contactPoints.add(optionalIntersectedPoint.get());
            }
        }

        // 결과맵을 만든다
        Point minimumPoint = getMinumumPoint(contactPoints);
        Point maximumPoint = getMaximumPoint(contactPoints);

        int width = (int) (maximumPoint.x - minimumPoint.x + 1);
        int height = (int) (maximumPoint.y - minimumPoint.y + 1);

        char[][] map = new char[height][width];
        for (char[] row : map) {
            Arrays.fill(row, '.');
        }

        // 접점에 별 표시를 한다
        for (Point contactPoint : contactPoints) {
            int contactedY = (int) (maximumPoint.y - contactPoint.y);
            int contactedX = (int) (contactPoint.x - minimumPoint.x);
            map[contactedY][contactedX] = '*';
        }

        String[] result = new String[map.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = new String(map[i]);
        }
        return result;
    }

    private Optional<Point> intersect(int[] line1, int[] line2) {
        long denominator = ((long) line1[0] * line2[1]) - ((long) line1[1] * line2[0]);
        if (denominator == 0L) {
            return Optional.empty();
        }

        long xNumerator =  ((long) line1[1] * line2[2]) - ((long) line1[2] * line2[1]);
        long yNumerator = ((long) line1[2] * line2[0]) - ((long) line1[0] * line2[2]);

        double x = (double) xNumerator / denominator;
        double y = (double) yNumerator / denominator;

        if (x % 1 != 0d || y % 1 != 0d) {
            return Optional.empty();
        }
        return Optional.of(new Point((long) x, (long) y));
    }

    private Point getMinumumPoint(List<Point> points) {
        long minX = Long.MAX_VALUE;
        long minY = Long.MAX_VALUE;

        for (Point point : points) {
            minX = Math.min(point.x, minX);
            minY = Math.min(point.y, minY);
        }
        return new Point(minX, minY);
    }

    private Point getMaximumPoint(List<Point> points) {
        long maxX = Long.MIN_VALUE;
        long maxY = Long.MIN_VALUE;

        for (Point point : points) {
            maxX = Math.max(point.x, maxX);
            maxY = Math.max(point.y, maxY);
        }
        return new Point(maxX, maxY);
    }

    private static class Point {

        final long x;
        final long y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        int[][] line = new int[][]{
                {0, 1, -1},
                {1, 0, -1},
                {1, 0, 1}
        };

        Solution solution = new Solution();

        System.out.println(Arrays.toString(solution.solution(line)));
    }
}
