import java.util.*;

class Solution {    
    private static final int POBBIDEN = -2;
    private static final int UNVISITED = -1;
    private static final int START = 1;
    
    public int solution(int[][] maps) {
        // 거리를 구하는 배열을 생성한다.
        int rowLength = maps.length;
        int colLength = maps[0].length;
        int[][] distances = new int[rowLength][colLength];
        for (int row = 0; row < rowLength; row++) {
            Arrays.fill(distances[row], UNVISITED);
            for (int col = 0; col < colLength; col++) {
                if (maps[row][col] == 0) {
                    distances[row][col] = POBBIDEN;
                }
            }
        }
        
        // 최소 거리를 구한다.
        Point start = new Point(0, 0);
        distances[start.row][start.col] = START;
        
        searchMinimumDistance(distances, start);
        
        // 도착 지점까지의 최소 거리를 반환한다. 존재하지 않을 경우 -1을 반환한다.
        return distances[rowLength - 1][colLength - 1];
    }
    
    private void searchMinimumDistance(int[][] distances, Point start) {
        int rowLength = distances.length;
        int colLength = distances[0].length;
        
        Deque<Point> queue = new ArrayDeque<>();
        queue.addLast(start);
        
        Point[] directions = {
            new Point(-1, 0),
            new Point(0, 1),
            new Point(1, 0),
            new Point(0, -1)
            };
        
        while (!queue.isEmpty()) {
            Point current = queue.removeFirst();
            int nextDistance = distances[current.row][current.col] + 1;
            
            for (Point direct : directions) {
                Point next = new Point(current.row + direct.row, current.col + direct.col);
                if (isNotWithinRange(distances, next)) {
                    continue;
                }
                
                if (distances[next.row][next.col] == UNVISITED || distances[next.row][next.col] > nextDistance) {
                    distances[next.row][next.col] = nextDistance;
                    queue.addLast(next);
                }
            }
        }
    }
    
    private boolean isNotWithinRange(int[][] array, Point point) {
        if (point.row < 0 || array.length <= point.row) {
            return true;
        }
        return point.col < 0 || array[point.row].length <= point.col;
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