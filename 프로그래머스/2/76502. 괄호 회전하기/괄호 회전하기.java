import java.util.*;

class Solution {
    public int solution(String s) {
        int result = 0;
        
        for (int i = 0; i < s.length(); i++) {
            String rotated = s.substring(i, s.length()) + s.substring(0, i);
            
            if (isValid(rotated)) {
                result++;
            }
        }
        
        return result;
    }
    
    private boolean isValid(String str) {
        Deque<Character> stack = new ArrayDeque<>();
        
        for (Character c : str.toCharArray()) {
            if (c == '[' || c == '(' || c == '{') {
                stack.addFirst(c);
                continue;
            }
            
            if (stack.isEmpty()) {
                return false;
            }
            Character top = stack.removeFirst();
            
            if (c == ']') {
                if (top != '[') {
                    return false;
                }
            } else if (c == ')') {
                if (top != '(') {
                    return false;
                }
            } else if (c == '}') {
                if (top != '{') {
                    return false;
                }
            } else {
                return false;
            }
        }
        
        return stack.isEmpty();
    }
}