import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        // 각 열의 인형을 Deque로 관리 (위가 first)
        Deque<Integer>[] columns = buildColumns(board);
        
        // 바구니 (위가 first)
        Deque<Integer> basket = new ArrayDeque<>();
        
        int explodedCount = 0;
        
        for (int move : moves) {
            int columnIndex = move - 1;
            
            // 인형 뽑기
            Integer doll = pickDoll(columns[columnIndex]);
            if (doll == null) {
                continue;
            }
            
            // 바구니에 넣고 터트리기 확인
            if (!basket.isEmpty() && basket.peekFirst().equals(doll)) {
                basket.removeFirst();
                explodedCount += 2;
            } else {
                basket.addFirst(doll);
            }
        }
        
        return explodedCount;
    }
    
    private Deque<Integer>[] buildColumns(int[][] board) {
        int size = board.length;
        Deque<Integer>[] columns = new ArrayDeque[size];
        
        for (int i = 0; i < size; i++) {
            columns[i] = new ArrayDeque<>();
        }
        
        // 위에서 아래로 스캔하여 Deque에 추가 (위가 first)
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board[row][col] != 0) {
                    columns[col].addLast(board[row][col]);
                }
            }
        }
        
        return columns;
    }
    
    private Integer pickDoll(Deque<Integer> column) {
        return column.pollFirst();  // 비어있으면 null 반환
    }
}