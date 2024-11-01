import java.util.*;
import java.util.stream.*;

class Solution {
    public long solution(int n, int[] works) {
        List<Integer> sortedInDescending = Arrays.stream(works)
            .boxed()
            .sorted((a, b) -> b - a)
            .collect(Collectors.toList());
        
        while (n > 0) {
            int target = sortedInDescending.get(0) - 1;
            if (target < 0) {
                break;
            }
            
            for (int i = 0; 
                 n > 0 && i < sortedInDescending.size() && target + 1 == sortedInDescending.get(i); 
                 i++) {
                sortedInDescending.set(i, target);
                n--;
            }
        }
        
         return sortedInDescending.stream()
            .mapToLong(i -> (long) i * i)
            .sum();
    }
}
