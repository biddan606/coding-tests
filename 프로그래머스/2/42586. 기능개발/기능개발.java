import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> deploymentCounts = new ArrayList<>();
        
        int index = 0;
        int day = 0;
        while (index < progresses.length) {
            int nextIndex = index;
            while (nextIndex < progresses.length 
                   && progresses[nextIndex] + speeds[nextIndex] * day >= 100) {
                nextIndex++;
            }
            
            if (nextIndex - index > 0) {
                deploymentCounts.add(nextIndex - index);
            }
            
            index = nextIndex;
            day++;
        }
        
        return deploymentCounts.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}