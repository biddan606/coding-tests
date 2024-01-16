import java.util.*;

class Solution {
    boolean solution(String s) {
        int count = 0;
        
        for (char c : s.toCharArray()) {
            switch(c) {
                    case '(' -> count++;
                    case ')' -> {
                        if (count == 0) {
                            return false;
                        }
                        
                        count--;
                    }
            }
        }
        
        return count == 0;
    }
}