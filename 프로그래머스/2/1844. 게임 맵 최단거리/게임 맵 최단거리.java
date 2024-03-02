import java.util.*;

class Solution {    
    private static final int OBSTACLE = -2;
    private static final int UNVISITED = -1;
    private static final int START = 1;
    
    Point[] DIRECTIONS = {
            new Point(-1, 0), // 상
            new Point(1, 0),  // 하
            new Point(0, -1), // 좌
            new Point(0, 1)   // 우
            };
    
    public int solution(int[][] maps) {
        int[][] distances = initializeDistanceArray(maps);
        
        Point start = new Point(0, 0);
        distances[start.row][start.col] = START;
        
        bfsToFindMinumumDistance(distances, start);
        
        return distances[maps.length - 1][maps[0].length - 1];
    }
    
    private int[][] initializeDistanceArray(int[][] maps) {
        int rowLength = maps.length;
        int colLength = maps[0].length;
        int[][] distances = new int[rowLength][colLength];
        
        for (int row = 0; row < rowLength; row++) {
            Arrays.fill(distances[row], UNVISITED);
            for (int col = 0; col < colLength; col++) {
                if (maps[row][col] == 0) {
                    distances[row][col] = OBSTACLE;
                }
            }
        }
        
        return distances;
    }
    
    private void bfsToFindMinumumDistance(int[][] distances, Point start) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);
        
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            int nextDistance = distances[current.row][current.col] + 1;
            
            for (Point direct : DIRECTIONS) {
                Point next = new Point(current.row + direct.row, current.col + direct.col);
                if (isValidLocation(distances, next) 
                    && distances[next.row][next.col] == UNVISITED) {
                    distances[next.row][next.col] = nextDistance;
                    queue.offer(next);
                }
            }
        }
    }
    
    private boolean isValidLocation(int[][] array, Point point) {
        if (point.row < 0 || point.row >= array.length) {
            return false;
        }
        return point.col >= 0 && point.col < array[point.row].length;
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