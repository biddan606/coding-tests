import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> deploymentCounts = new ArrayList<>();
        
        int count = 1;
        int days = (int) Math.ceil((double) (100 - progresses[0]) / speeds[0]);
        
        for (int i = 1; i < progresses.length; i++) {
            int expiration = (int) Math.ceil((double) (100 - progresses[i]) / speeds[i]);
            
            if (expiration > days) {
                deploymentCounts.add(count);
                count = 0;
                days = expiration;
            }
            
            count++;
        }
        
        deploymentCounts.add(count);
        
        return deploymentCounts.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}