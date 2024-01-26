import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        // 스택 원소: [index, value]
        Deque<int[]> stack = new ArrayDeque<>();
        int[] result = new int[prices.length];
        
        for (int i = 0; i < prices.length; i++) {
            int price = prices[i];
            
            while (!stack.isEmpty() && stack.getFirst()[1] > price) {
                int[] top = stack.removeFirst();
                int topIndex = top[0];
                
                result[topIndex] = i - topIndex;
            }
            
            stack.addFirst(new int[]{i, price});
        }
        
        while (!stack.isEmpty()) {
            int[] top = stack.removeFirst();
            
            result[top[0]] = prices.length - 1 - top[0];
        }
        
        return result;
    }
}