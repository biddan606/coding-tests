import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

class Solution {

    // points: 좌표
    // routes: 로봇의 운송 경로, i번째 로봇이 j번째로 방문하는 포인트 번호
    public int solution(int[][] points, int[][] routes) {
        // 로봇의 시작 위치, 목적지를 정합니다
        Queue<Robot> robots = new ArrayDeque<>();
        for (int[] route : routes) {
            int startIndex = route[0] - 1;
            Point start = new Point(points[startIndex][0], points[startIndex][1]);

            Deque<Point> endPoints = new ArrayDeque<>();
            for (int i = 1; i < route.length; i++) {
                int endIndex = route[i] - 1;
                endPoints.add(new Point(points[endIndex][0], points[endIndex][1]));
            }

            robots.add(new Robot(start, endPoints));
        }

        // 로봇이 목적지로 이동합니다.
        int riskySituations = 0;
        while (!robots.isEmpty()) {
            int size = robots.size();
            Set<Point> set = new HashSet<>();
            Set<Point> riskSet = new HashSet<>();

            for (int i = 0; i < size; i++) {
                Robot robot = robots.poll();

                // 로봇의 위치를 Set에 넣습니다. 만약 Set에 존재한다면, riskSet에 넣습니다.
                Point element = new Point(robot.current);
                if (set.contains(element)) {
                    riskSet.add(element);
                } else {
                    set.add(element);
                }

                if (robot.destinations.isEmpty()) {
                    continue;
                }
                Point destination = robot.destinations.removeFirst();

                // 목적지로 이동합니다. row가 먼저 변합니다.
                if (robot.current.row != destination.row) {
                    if (robot.current.row < destination.row) {
                        robot.current.row++;
                    } else {
                        robot.current.row--;
                    }
                } else if (robot.current.col != destination.col) {
                    if (robot.current.col < destination.col) {
                        robot.current.col++;
                    } else {
                        robot.current.col--;
                    }
                }

                if (!robot.current.equals(destination)) {
                    robot.destinations.addFirst(destination);
                }
                robots.add(robot);
            }

            // riskSet.size() 만큼 riskySituations를 증가시킵니다.
            riskySituations += riskSet.size();
        }

        return riskySituations;
    }

    private static class Robot {

        final Point current;
        final Deque<Point> destinations;

        public Robot(Point current, Deque<Point> destinations) {
            this.current = current;
            this.destinations = destinations;
        }
    }

    private static class Point {

        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Point(Point point) {
            this.row = point.row;
            this.col = point.col;
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

    public static void main(String[] args) {
        int[][] points = new int[][]{{3, 2}, {6, 4}, {4, 7}, {1, 4}};
        int[][] routes = new int[][]{{4, 2}, {1, 3}, {4, 2}, {4, 3}};

        int result = new Solution().solution(points, routes);
        System.out.println(result);
    }
}
