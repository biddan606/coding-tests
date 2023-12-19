import java.util.Arrays;

class Solution {

    private static final Point[] DIRECTS = {
            new Point(1, 0),
            new Point(0, 1),
            new Point(-1, -1)
    };

    public int[] solution(int n) {
        int[][] snailPaths = new int[n][n];

        // 초기 설정
        Point currentPoint = new Point(0, 0);
        int currentDirectIndex = 0;

        // 달팽이 채우기
        for (int pathNumber = 1; pathNumber <= n * (n + 1) / 2; pathNumber++) {
            snailPaths[currentPoint.row][currentPoint.col] = pathNumber;

            Point nextPoint = new Point(
                    currentPoint.row + DIRECTS[currentDirectIndex].row,
                    currentPoint.col + DIRECTS[currentDirectIndex].col
            );
            if (!canMove(snailPaths, nextPoint)) {
                currentDirectIndex = (currentDirectIndex + 1) % DIRECTS.length;
                nextPoint = new Point(
                        currentPoint.row + DIRECTS[currentDirectIndex].row,
                        currentPoint.col + DIRECTS[currentDirectIndex].col
                );
            }

            currentPoint = nextPoint;
        }

        // 결과를 구한다.
        int[] result = new int[n * (n + 1) / 2];
        int resultIndex = 0;
        for (int row = 0; row < snailPaths.length; row++) {
            for (int col = 0; col <= row; col++) {
                result[resultIndex] = snailPaths[row][col];
                resultIndex++;
            }
        }

        return result;
    }

    private boolean canMove(int[][] snailPaths, Point point) {
        int arraySize = snailPaths.length;

        if (point.row < 0 || point.col < 0) {
            return false;
        }

        if (point.row >= arraySize || point.col > point.row) {
            return false;
        }

        return snailPaths[point.row][point.col] == 0;
    }

    private static class Point {

        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(Arrays.toString(solution.solution(5)));
    }
}
