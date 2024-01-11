import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        for (int row = 1; row < triangle.length; row++) {
            triangle[row][0] += triangle[row - 1][0];
            for (int col = 1; col < triangle[row].length - 1; col++) {
                int prevMax = Math.max(triangle[row - 1][col - 1], triangle[row - 1][col]);
                triangle[row][col] += prevMax;
            }
            triangle[row][triangle[row].length - 1] += triangle[row - 1][triangle[row].length - 2];
        }
        
        int pathMax = 0;
        for (int pathValue : triangle[triangle.length - 1]) {
            pathMax = Math.max(pathMax, pathValue);
        }
        
        return pathMax;
    }
}