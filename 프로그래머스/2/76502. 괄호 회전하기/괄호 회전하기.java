import java.util.*;

class Solution {
    public int solution(String s) {
        int result = 0;
        
        for (int i = 0; i < s.length(); i++) {
            String rotated = s.substring(i, s.length()) + s.substring(0, i);
            
            if (isCorrect(rotated)) {
                result++;
            }
        }
        
        return result;
    }
    
    private boolean isCorrect(String str) {
        Deque<Character> stack = new ArrayDeque<>();
        
        for (Character c : str.toCharArray()) {
            switch (c) {
                case '[':
                case '(':
                case '{':
                    stack.addFirst(c);
                    continue;
                case ']':
                    if (stack.isEmpty() || stack.removeFirst() != '[') {
                        return false;
                    }
                    break;
                case ')':
                    if (stack.isEmpty() || stack.removeFirst() != '(') {
                        return false;
                    }
                    break;
                case '}':
                    if (stack.isEmpty() || stack.removeFirst() != '{') {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
        }
        
        return stack.isEmpty();
    }
}