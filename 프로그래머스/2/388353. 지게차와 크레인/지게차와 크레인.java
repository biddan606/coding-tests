import java.util.*;

class Solution {
    /*
    # 문제 이해
    - request 문자열의 길이가 1개이면 접근 가능한 문자 컨테이너만 제거
    - request 문자열의 길이가 2개이면 모든 문자 컨테이너 제거
    남은 컨테이너 개수를 반환해야 한다
    
    # 접근
    - storage의 최대 크기는 250이고, requests의 최대 요청 개수는 100이다
        단순 구현 문제
    
    주어진 것: 컨테이너들
    필요한 것: 접근 가능한 컨테이너와 접근 불가능한 컨테이너
    
    - 접근 가능 여부를 저장해두어, 요청별로 처리하면 될 듯
    
    # 구현 스텝
    1. 접근 가능 여부를 확인하는 배열을 만든다
    2. 모든 request를 처리한다
    3-1. request의 길이가 1개면 접근 가능한 컨테이너만 처리
    3-2. request의 길이가 2개면 모든 문자 컨테이너만 처리
    4. 접근 가능한 컨테이너들을 갱신한다
    5. 남은 컨테이너 개수를 반환한다
    */
    private static final int[][] DIRECTIONS = {
        {-1, 0},
        {1, 0},
        {0, -1},
        {0, 1}
    };
    
    public int solution(String[] storage, String[] requests) {
        int rows = storage.length;
        int cols = storage[0].length();
        boolean[][] shipped = new boolean[rows][cols];
        int shippedCount = 0;
        
        boolean[][] accessible = new boolean[rows][cols];
        updateAccessibleArray(shipped, accessible);
        
        for (String request : requests) {
            char target = request.charAt(0);
            if (request.length() == 1) {
                shippedCount += shipAccessible(storage, shipped, accessible, target);
            } else if (request.length() == 2) {
                shippedCount += shipAll(storage, shipped, target);
            }
            updateAccessibleArray(shipped, accessible);
        }
        
        return rows * cols - shippedCount;
    }
    
    private static int shipAccessible(String[] storage, boolean[][] shipped, boolean[][] accessible, char target) {
        int shippedCount = 0;
        
        for (int r = 0; r < storage.length; r++) {
            for (int c = 0; c < storage[r].length(); c++) {
                if (!shipped[r][c] 
                    && accessible[r][c] 
                    && storage[r].charAt(c) == target) {
                    shipped[r][c] = true;
                    shippedCount++;
                }
            }
        }
        
        return shippedCount;
    }
    
    private static int shipAll(String[] storage, boolean[][] shipped, char target) {
        int shippedCount = 0;
        
        for (int r = 0; r < storage.length; r++) {
            for (int c = 0; c < storage[r].length(); c++) {
                if (!shipped[r][c] 
                    && storage[r].charAt(c) == target) {
                    shipped[r][c] = true;
                    shippedCount++;
                }
            }
        }
        
        return shippedCount;
    }
    
    private static void updateAccessibleArray(boolean[][] shipped, boolean[][] accessible) {
        int rows = shipped.length;
        int cols = shipped[0].length;
        
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new ArrayDeque<>();
        
        for (int c = 0; c < cols; c++) {
            queue.offer(new int[]{0, c});
            queue.offer(new int[]{rows - 1, c});
        }
        for (int r = 1; r < rows - 1; r++) {
            queue.offer(new int[]{r, 0});
            queue.offer(new int[]{r, cols - 1});
        }
        
        while (!queue.isEmpty()) {
            int[] e = queue.poll();
            int currentRow = e[0];
            int currentCol = e[1];
            
            if (isOutOfRange(rows, cols, currentRow, currentCol)
                || visited[currentRow][currentCol]) {
                continue;
            }
            visited[currentRow][currentCol] = true;
            accessible[currentRow][currentCol] = true;
            
            if (!shipped[currentRow][currentCol]) {
                continue;
            }
            for (int[] direction : DIRECTIONS) {
                queue.offer(new int[]{currentRow + direction[0], currentCol + direction[1]});
            }
        }
    }
    
    private static boolean isOutOfRange(int rows, int cols, int currentRow, int currentCol) {
        return currentRow < 0
            || rows <= currentRow
            || currentCol < 0
            || cols <= currentCol;
    }
}
