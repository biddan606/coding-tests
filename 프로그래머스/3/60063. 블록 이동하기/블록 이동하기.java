import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

class Solution {

    public int solution(int[][] board) {
        Robot robot = new Robot(new Point(0, 0),
                new Point(0, 1),
                0);
        Point destination = new Point(board.length - 1, board[0].length - 1);

        Queue<Robot> queue = new ArrayDeque<>();
        queue.offer(robot);
        boolean[][][] visited = new boolean[board.length][board[0].length][2];

        while (!queue.isEmpty()) {
            Robot current = queue.poll();
            if (current.contains(destination)) {
                return current.time;
            }

            if (visited[current.topLeft.row][current.topLeft.col][current.getOrientation().value]) {
                continue;
            }
            visited[current.topLeft.row][current.topLeft.col][current.getOrientation().value] = true;

            for (Direction direction : Direction.values()) {
                if (current.canNotMove(direction) ||
                        isNotValidPoint(board, current.topLeft.row + direction.checkingTopLeftRow,
                                current.topLeft.col + direction.checkingTopLeftCol) ||
                        isNotValidPoint(board, current.bottomRight.row + direction.checkingBottomRightRow,
                                current.bottomRight.col + direction.checkingBottomRightCol)) {
                    continue;
                }

                Robot nextRobot = makeRobot(current,
                        new Point(direction.deltaTopLeftRow, direction.deltaTopLeftCol),
                        new Point(direction.deltaBottomRightRow, direction.deltaBottomRightCol));
                queue.offer(nextRobot);
            }
        }

        return -1;
    }

    private boolean isNotValidPoint(int[][] board, int row, int col) {
        return isNotInBounds(board, row, col) || board[row][col] == 1;
    }

    private boolean isNotInBounds(int[][] board, int row, int col) {
        return row < 0 || row >= board.length || col < 0 || col >= board[0].length;
    }

    private Robot makeRobot(Robot robot, Point deltaTopLeft, Point deltaBottomRight) {
        Point topLeft = new Point(robot.topLeft.row + deltaTopLeft.row, robot.topLeft.col + deltaTopLeft.col);
        Point bottomRight = new Point(robot.bottomRight.row + deltaBottomRight.row,
                robot.bottomRight.col + deltaBottomRight.col);
        return new Robot(topLeft, bottomRight, robot.time + 1);
    }

    private class Robot {

        private final Point topLeft;
        private final Point bottomRight;
        private final int time;

        public Robot(Point topLeft, Point bottomRight, int time) {
            this.topLeft = topLeft;
            this.bottomRight = bottomRight;
            this.time = time;
        }

        public boolean canNotMove(Direction direction) {
            return direction.orientation != Orientation.BOTH && direction.orientation != getOrientation();
        }

        public Orientation getOrientation() {
            if (topLeft.row == bottomRight.row) {
                return Orientation.HORIZONTAL;
            }
            return Orientation.VERTICAL;
        }

        public boolean contains(Point point) {
            return topLeft.equals(point) || bottomRight.equals(point);
        }
    }

    private class Point {

        final int row;
        final int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Point point = (Point) o;
            return row == point.row && col == point.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    private enum Orientation {
        HORIZONTAL(0),
        VERTICAL(1),
        BOTH(-1);

        private final int value;

        Orientation(int value) {
            this.value = value;
        }
    }

    private enum Direction {
        UP(Orientation.BOTH, -1, 0, -1, 0, -1, 0, -1, 0),
        DOWN(Orientation.BOTH, 1, 0, 1, 0, 1, 0, 1, 0),
        LEFT(Orientation.BOTH, 0, -1, 0, -1, 0, -1, 0, -1),
        RIGHT(Orientation.BOTH, 0, 1, 0, 1, 0, 1, 0, 1),
        TOP_RIGHT(Orientation.HORIZONTAL, -1, 1, 0, 0, -1, 0, -1, 0),
        TOP_LEFT(Orientation.HORIZONTAL, -1, 0, 0, -1, -1, 0, -1, 0),
        BOTTOM_RIGHT(Orientation.HORIZONTAL, 0, 1, 1, 0, 1, 0, 1, 0),
        BOTTOM_LEFT(Orientation.HORIZONTAL, 0, 0, 1, -1, 1, 0, 1, 0),
        LEFT_TOP(Orientation.VERTICAL, 0, -1, -1, 0, 0, -1, 0, -1),
        LEFT_BOTTOM(Orientation.VERTICAL, 1, -1, 0, 0, 0, -1, 0, -1),
        RIGHT_TOP(Orientation.VERTICAL, 0, 0, -1, 1, 0, 1, 0, 1),
        RIGHT_BOTTOM(Orientation.VERTICAL, 1, 0, 0, 1, 0, 1, 0, 1);

        final Orientation orientation;
        final int deltaTopLeftRow;
        final int deltaTopLeftCol;
        final int deltaBottomRightRow;
        final int deltaBottomRightCol;
        final int checkingTopLeftRow;
        final int checkingTopLeftCol;
        final int checkingBottomRightRow;
        final int checkingBottomRightCol;

        Direction(Orientation orientation, int deltaTopLeftRow, int deltaTopLeftCol, int deltaBottomRightRow,
                int deltaBottomRightCol, int checkingTopLeftRow, int checkingTopLeftCol, int checkingBottomRightRow,
                int checkingBottomRightCol) {
            this.orientation = orientation;
            this.deltaTopLeftRow = deltaTopLeftRow;
            this.deltaTopLeftCol = deltaTopLeftCol;
            this.deltaBottomRightRow = deltaBottomRightRow;
            this.deltaBottomRightCol = deltaBottomRightCol;
            this.checkingTopLeftRow = checkingTopLeftRow;
            this.checkingTopLeftCol = checkingTopLeftCol;
            this.checkingBottomRightRow = checkingBottomRightRow;
            this.checkingBottomRightCol = checkingBottomRightCol;
        }
    }

    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 1, 1},
                {0, 0, 0, 1, 0},
                {0, 1, 0, 1, 1},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 0, 0}};

        int result = new Solution().solution(board);

        System.out.println(result);
    }
}
