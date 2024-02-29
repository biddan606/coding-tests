import java.util.*;

class Solution {    
    private static final int POBBIDEN = -2;
    private static final int FIRST = -1;
    private static final int START = 1;
    
    public int solution(int[][] maps) {
        // 거리를 구하는 배열을 생성한다.
        int[][] distances = new int[maps.length][maps[0].length];
        for (int row = 0; row < maps.length; row++) {
            for (int col = 0; col < maps[0].length; col++) {
                distances[row][col] = FIRST;
                if (maps[row][col] == 0) {
                    distances[row][col] = POBBIDEN;
                }
            }
        }
        
        // 4방향으로 이동하면서 최소 거리를 구한다.
        Point[] directs = {
            new Point(-1, 0),
            new Point(0, 1),
            new Point(1, 0),
            new Point(0, -1)
            };
        Point start = new Point(0, 0);
        distances[start.row][start.col] = START;
        
        searchMinimumDistance(distances, directs, start);
        
        // 도착 지점까지의 최소 거리를 반환한다. 존재하지 않을 경우 -1을 반환한다.
        return distances[maps.length - 1][maps[0].length - 1];
    }
    
    private void searchMinimumDistance(int[][] distances, Point[] directs, Point start) {
        Deque<Point> queue = new ArrayDeque<>();
        queue.addLast(start);
        
        while (!queue.isEmpty()) {
            Point current = queue.removeFirst();
            int nextDistance = distances[current.row][current.col] + 1;
            
            for (Point direct : directs) {
                Point next = new Point(current.row + direct.row, current.col + direct.col);
                if (!isWithinRange(distances, next)) {
                continue;
                }
                if (distances[next.row][next.col] == POBBIDEN) {
                    continue;
                }
                
                if (distances[next.row][next.col] == FIRST || distances[next.row][next.col] > nextDistance) {
                    distances[next.row][next.col] = nextDistance;
                    if (next.row == distances.length - 1 && next.col == distances[0].length - 1) {
                        return;
                    }
                        
                    queue.addLast(next);
                }
            }
        }
    }
    
    private boolean isWithinRange(int[][] array, Point point) {
        if (point.row < 0 || array.length <= point.row) {
            return false;
        }
        return 0 <= point.col && point.col < array[point.row].length;
    }
    
    private static class Point {
        int row;
        int col;
        
        Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}