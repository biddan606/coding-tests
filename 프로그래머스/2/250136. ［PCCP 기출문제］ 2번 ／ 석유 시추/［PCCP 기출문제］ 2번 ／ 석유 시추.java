import java.util.*;

class Solution {
    
    private static final int START_VALUE = 10_000;
    private static final int[][] DIRECTS = {
        {-1 , 0},
        {0 , 1},
        {1 , 0},
        {0 , -1}
    };
    
    public int solution(int[][] land) {
        int value = START_VALUE;
        Map<Integer, Integer> valueCountMap = new HashMap<>();
        
        for (int row = 0; row < land.length; row++) {
            for (int col = 0; col < land[row].length; col++) {
                if (land[row][col] == 1) {
                    int count = bfs(land, row, col, value);
                    
                    valueCountMap.put(value, count);
                    
                    value++;
                }
            }
        }
        
        int maxResult = 0;
        
        for (int col = 0; col < land[0].length; col++) {
            Set<Integer> valueSet = new HashSet<>();
            
            for (int row = 0; row < land.length; row++) {
                if (land[row][col] == 0) {
                    continue;
                }
                
                valueSet.add(land[row][col]);
            }
            
            int result = 0;
            for (int v : valueSet) {
                result += valueCountMap.get(v);
            }
            
            maxResult = Math.max(maxResult, result);
        }
        
        return maxResult;
    }
    
    private int bfs(int[][] land, int startRow, int startCow, int value) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.addLast(new int[]{startRow, startCow});
        
        int count = 0;
        while (!queue.isEmpty()) {
            int[] point = queue.removeFirst();
            int row = point[0];
            int col = point[1];
            
            if (land[row][col] == value) {
                continue;
            }
            land[row][col] = value;
            count++;
            
            for (int[] direct : DIRECTS) {
                int nextRow = row + direct[0];
                int nextCol = col + direct[1];
                
                if (isNotWithinRange(land, nextRow, nextCol)) {
                    continue;
                }
                if (land[nextRow][nextCol] != 1) {
                    continue;
                }
                
                queue.addLast(new int[]{nextRow, nextCol});
            }
        }
        
        return count;
    }
    
    private boolean isNotWithinRange(int[][] array, int row, int col) {
        if (row < 0 || array.length <= row) {
            return true;
        }
        
        return col < 0 || array[row].length <= col;
    }
}