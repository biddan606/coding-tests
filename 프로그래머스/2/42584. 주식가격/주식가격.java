import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] result = new int[prices.length];
        
        for (int i = 0; i < prices.length; i++) {
            int price = prices[i];
            
            while (!stack.isEmpty() && prices[stack.getFirst()] > price) {
                int topIndex = stack.removeFirst();
                
                result[topIndex] = i - topIndex;
            }
            
            stack.addFirst(i);
        }
        
        while (!stack.isEmpty()) {
            int topIndex = stack.removeFirst();
            
            result[topIndex] = prices.length - 1 - topIndex;
        }
        
        return result;
    }
}