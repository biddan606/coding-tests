import java.util.*;

class Solution {
    private int[][] oilsKeys;
    private Map<Integer, Integer> oils = new HashMap<>();
    
    public int solution(int[][] land) {
        int rows = land.length;
        int cols = land[0].length;
        int key = 1;
        oilsKeys = new int[rows][cols];
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // (r, c)에서 석유를 추출할 수 있다면, 석유를 추출한다. 
                if (land[r][c] == 0) {
                    continue;
                }
                    
                // 추출 후 해당 포인트인 석유를 추출할 수 없다.
                // 석유가 추출된 포인트들에 total 추출량을 기록한다.
                extractOils(land, new int[]{r, c}, key);
                key++;
            }
        }
        
        int max = 0;
        for (int c = 0; c < cols; c++) {
            // column의 모든 row에서 total 추출량을 합산한다.
            // 합산한 추출량의 max를 갱신한다.
            int currentSum = 0;
            Set<Integer> duplicatedKeys = new HashSet<>();
            for (int r = 0; r < rows; r++) {
                int currentKey = oilsKeys[r][c];
                if (currentKey == 0) {
                    continue;
                }
                if (duplicatedKeys.contains(currentKey)) {
                    continue;
                }
                
                duplicatedKeys.add(currentKey);
                currentSum += oils.get(currentKey);
            }
            
            max = Math.max(max, currentSum);
        }
        
        // max를 반환한다.
        return max;
    }
    
    private void extractOils(int[][] land, int[] startPoint, int key) {
        int rows = land.length;
        int cols = land[0].length;
        
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(startPoint);
        
        int sum = 0;
        int[][] directions = new int[][]{
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
        };
        
        while (!queue.isEmpty()) {
            int[] currentPoint = queue.poll();
            int currentRow = currentPoint[0];
            int currentCol = currentPoint[1];
            if (land[currentRow][currentCol] == 0) {
                continue;
            }
            
            oilsKeys[currentRow][currentCol] = key;
            sum += land[currentRow][currentCol];
            land[currentRow][currentCol] = 0;
            
            for (int[] d : directions) {
                int nextRow = currentRow + d[0];
                int nextCol = currentCol + d[1];
                if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols) {
                    continue;
                }
                if (land[nextRow][nextCol] == 0) {
                    continue;
                }
                
                queue.offer(new int[]{nextRow, nextCol});
            }
        }
        
        oils.put(key, sum);
    }
}
