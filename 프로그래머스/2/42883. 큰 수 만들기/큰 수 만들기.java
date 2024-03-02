import java.util.*;
import java.util.stream.*;

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
        
        for (int i = 0; i < k; i++) {
            deque.removeLast();
        }
        
        return deque.stream()
            .map(String::valueOf)
            .collect(Collectors.joining());
    }
}