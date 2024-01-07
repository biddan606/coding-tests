import java.util.*;
import java.util.stream.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> completionMap = new HashMap<>();
        for (String s : completion) {
            int prevValue = completionMap.getOrDefault(s, 0);
            
            completionMap.put(s, prevValue + 1);
        }
        
        for (String s : participant) {
            int value = completionMap.getOrDefault(s, 0);
            
            if (value == 0) {
                return s;
            }
            completionMap.put(s, value - 1);
        }
        
        return null;
    }
}
