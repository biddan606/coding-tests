import java.util.*;

class Solution {
    boolean solution(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        
        for (char c : s.toCharArray()) {
            switch(c) {
                    case '(' -> stack.addFirst(c);
                    case ')' -> {
                        if (stack.isEmpty()) {
                            return false;
                        }
                        stack.removeFirst();
                    }
            }
        }
        
        return stack.isEmpty();
    }
}