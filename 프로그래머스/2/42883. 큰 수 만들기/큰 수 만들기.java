import java.util.*;

class Solution {
    public String solution(String number, int k) {
        Deque<Integer> right = new ArrayDeque<>();
        
        Arrays.stream(number.split(""))
            .map(Integer::parseInt)
            .forEach(right::addLast);
        
        Deque<Integer> left = new ArrayDeque<>();
        
        while (!right.isEmpty()) {
            left.addFirst(right.removeFirst());
            
            while (!left.isEmpty() && !right.isEmpty() && 0 < k
                   && left.getFirst() < right.getFirst()) {
                left.removeFirst();
                k--;
            }
        }
        
        while (!left.isEmpty() && 0 < k) {
            left.removeFirst();
            k--;
        }
        
        StringBuilder result = new StringBuilder();
        while (!left.isEmpty()) {
            result.append(left.removeLast());
        }
        
        return result.toString();
    }
}