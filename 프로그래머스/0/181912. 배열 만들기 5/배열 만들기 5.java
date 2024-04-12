import java.util.*;

class Solution {
    public int[] solution(String[] intStrs, int k, int s, int l) {
        List<Integer> result = new ArrayList<>();
        
        for (String intStr : intStrs) {
            String sub = intStr.substring(s, s + l);
            int number = Integer.parseInt(sub);
            
            if (number > k) {
                result.add(number);
            }
        }
        
        return result.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}