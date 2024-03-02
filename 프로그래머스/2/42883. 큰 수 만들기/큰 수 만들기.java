import java.util.*;

class Solution {
    public String solution(String number, int k) {
        Deque<Character> deque = new ArrayDeque<>();
        
        for (char c : number.toCharArray()) {
            while (k > 0 && !deque.isEmpty() && deque.getLast() < c) {
                k--;
                deque.removeLast();
            }
            
            deque.addLast(c);
        }
        
        while (k-- > 0) {
            deque.removeLast();
        }
        
        StringBuilder result = new StringBuilder();
        while (!deque.isEmpty()) {
            result.append(deque.removeFirst());
        }
        return result.toString();
    }
}