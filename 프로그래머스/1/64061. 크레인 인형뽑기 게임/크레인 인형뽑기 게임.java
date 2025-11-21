import java.util.*;

class Solution {
    /*
    # 문제
    moves 순서대로 인형을 뽑아, 바구니에 넣는다.
    바구니의 인형 2개가 중복되면 터트려 사라진다.
    
    # 구조
    각 라인은 Deque, 뒤에 채우고 앞에서부터 뺀다.
    바구니는 Stack, peek와 새로운 인형이 일치하면 터트린다.
    */
    public int solution(int[][] board, int[] moves) {
        int size = board.length;
        
        // 라인 생성
        Deque<Integer>[] lines = new Deque[size];
        for (int i = 0; i < size; i++) {
            lines[i] = new ArrayDeque<>();
        }
        
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                int cell = board[r][c];
                if (cell == 0) {
                    continue;
                }
                
                lines[c].addLast(cell);
            }
        }
        
        // 바구니 생성
        Deque<Integer> basket = new ArrayDeque<>();
        
        // 게임 진행
        int score = 0;
        
        for (int move : moves) {
            int line = move - 1;
            
            if (lines[line].isEmpty()) {
                continue;
            }
            
            Integer doll = lines[line].removeFirst();
            
            basket.addFirst(doll);
            score += popBasket(basket);
        }
        
        return score;
    }
    
    private int popBasket(Deque<Integer> basket) {
        int score = 0;
        boolean processing = true;
        
        while (basket.size() >= 2 && processing) {
            Integer first = basket.removeFirst();
            if (basket.peekFirst() == first) {
                basket.removeFirst();
                score += 2;
            } else {
                basket.addFirst(first);
                processing = false;
            }
        }
        
        return score;
    }
}
