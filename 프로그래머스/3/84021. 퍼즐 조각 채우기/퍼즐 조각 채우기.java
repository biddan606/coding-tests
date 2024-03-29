import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

class Solution {

    private static final Point[] DIRECTIONS = {
            new Point(-1, 0),
            new Point(1, 0),
            new Point(0, -1),
            new Point(0, 1)
    };

    public int solution(int[][] gameBoard, int[][] table) {
        List<Piece> gameBoardPieces = getPieces(gameBoard, 0);
        List<Piece> tablePieces = getPieces(table, 1);

        return fitPieces(gameBoardPieces, tablePieces);
    }

    private List<Piece> getPieces(int[][] map, int targetState) {
        boolean[][] visited = new boolean[map.length][map[0].length];
        List<Piece> pieces = new ArrayList<>();

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (map[y][x] != targetState || visited[y][x]) {
                    continue;
                }

                Piece piece = getPiece(map, visited, new Point(x, y));
                pieces.add(piece);
            }
        }

        return pieces;
    }

    private Piece getPiece(int[][] map, boolean[][] visited, Point start) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(start);
        List<Point> points = new ArrayList<>();

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            if (visited[current.y][current.x]) {
                continue;
            }
            visited[current.y][current.x] = true;
            points.add(current);

            for (Point direction : DIRECTIONS) {
                Point next = new Point(current.x + direction.x, current.y + direction.y);
                if (isWithinRange(map, next) && map[next.y][next.x] == map[start.y][start.x]) {
                    queue.offer(next);
                }
            }
        }

        return new Piece(points);
    }

    private boolean isWithinRange(int[][] map, Point next) {
        if (next.y < 0 || map.length <= next.y) {
            return false;
        }
        return 0 <= next.x && next.x < map[next.y].length;
    }

    private int fitPieces(List<Piece> gameBoardPieces, List<Piece> tablePieces) {
        int result = 0;
        boolean[] visitedGameBoardPieces = new boolean[gameBoardPieces.size()];

        for (Piece source : tablePieces) {
            Piece current = source;
            boolean matched = false;

            for (int i = 0; i < gameBoardPieces.size(); i++) {
                Piece target = gameBoardPieces.get(i);

                if (visitedGameBoardPieces[i] || target.points.size() != current.points.size()) {
                    continue;
                }

                for (int rotateCount = 0; rotateCount < 4; rotateCount++) {
                    if (Objects.equals(target, current)) {
                        visitedGameBoardPieces[i] = true;
                        result += target.points.size();
                        matched = true;
                        break;
                    }

                    current = rotateRightAngle(current);
                }
                if (matched) {
                    break;
                }
            }
        }

        return result;
    }

    private Piece rotateRightAngle(Piece current) {
        List<Point> rightAnglePoints = new ArrayList<>();
        for (Point point : current.points) {
            rightAnglePoints.add(new Point(-point.y, point.x));
        }

        return new Piece(rightAnglePoints);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] gameBoard1 = {
                {1, 1, 0, 0, 1, 0},
                {0, 0, 1, 0, 1, 0},
                {0, 1, 1, 0, 0, 1},
                {1, 1, 0, 1, 1, 1},
                {1, 0, 0, 0, 1, 0},
                {0, 1, 1, 1, 0, 0}
        };
        int[][] table1 = {
                {1, 0, 0, 1, 1, 0},
                {1, 0, 1, 0, 1, 0},
                {0, 1, 1, 0, 1, 1},
                {0, 0, 1, 0, 0, 0},
                {1, 1, 0, 1, 1, 0},
                {0, 1, 0, 0, 0, 0}
        };

        int result1 = solution.solution(gameBoard1, table1);
        System.out.println(result1);

        int[][] gameBoard2 = {
                {0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1},
                {0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0},
                {0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1},
                {1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1},
                {1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0},
                {0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0},
                {1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0},
                {1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0},
                {0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0},
                {1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1},
                {1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0},
                {0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0}
        };

        int[][] table2 = {
                {1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0},
                {1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1},
                {1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1},
                {1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1},
                {0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1},
                {0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0},
                {1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                {1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1},
                {1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                {0, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0},
                {1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1},
                {0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1},
                {0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1}
        };

        int result2 = solution.solution(gameBoard2, table2);
        System.out.println(result2);
    }

    private static class Piece {

        final Set<Point> points = new HashSet<>();

        public Piece(List<Point> points) {
            List<Point> normalizedPoints = normalizePoints(points);
            this.points.addAll(normalizedPoints);
        }

        private List<Point> normalizePoints(List<Point> points) {
            int minX = Integer.MAX_VALUE;
            int minY = Integer.MAX_VALUE;

            for (Point point : points) {
                minX = Math.min(minX, point.x);
                minY = Math.min(minY, point.y);
            }

            List<Point> normalizedPoints = new ArrayList<>();

            for (Point point : points) {
                normalizedPoints.add(new Point(point.x - minX, point.y - minY));
            }

            return normalizedPoints;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Piece piece = (Piece) o;
            return Objects.equals(points, piece.points);
        }

        @Override
        public int hashCode() {
            return Objects.hash(points);
        }
    }

    private static class Point {

        final int x;
        final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
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
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "{x= " + x + ", y= " + y + '}';
        }
    }
}
