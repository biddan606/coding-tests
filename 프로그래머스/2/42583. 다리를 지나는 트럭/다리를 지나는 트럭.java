import java.util.*;

class Solution {
    public int solution(int bridgeLength, int weight, int[] truckWeights) {
        Deque<int[]> proceeding = new ArrayDeque<>();
        int dequeWeight = 0;
        int index = 0;
        int second = 0;
        
        while (index < truckWeights.length || !proceeding.isEmpty()) {
            if (!proceeding.isEmpty() 
                && second - proceeding.getFirst()[0] == bridgeLength) {
                dequeWeight -= proceeding.removeFirst()[1];
            }
            
            if (index < truckWeights.length 
                   && dequeWeight + truckWeights[index] <= weight
                   && proceeding.size() < bridgeLength) {
                proceeding.addLast(new int[]{second, truckWeights[index]});
                dequeWeight += truckWeights[index];
                index++;
            }
                
            second++;
        }
        
        return second;
    }
}