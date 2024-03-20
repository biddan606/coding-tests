class Solution {
    public long solution(int requiredGold, int requiredSilver, int[] gold, int[] silver, int[] weights, int[] times) {
        long minTime = 0;
        // 금과 은이 10^9, 도시는 1개, 옮길 수 있는 무게 1, time은 10^5로 왕복일 경우
        long maxTime = (long)(10e9 * 2 * 10e5 * 2);
        
        long minPossibleTime = maxTime;
        while (minTime <= maxTime) {
            long currentTime = (minTime + maxTime) / 2;
            int maxGoldAmount = 0;
            int maxSilverAmount = 0;
            int carriableWeight = 0;
            
            for (int i = 0; i < weights.length; i++) {
                int carryingGold = gold[i];
                int carryingSilver = silver[i];
                int carryingWeight = weights[i];
                long carryingTime = times[i];
                
                long moveCount = currentTime / (carryingTime * 2);
                if (currentTime % (carryingTime * 2) >= carryingTime) {
                    moveCount++;
                }
                
                maxGoldAmount += Math.min(carryingGold, moveCount * carryingWeight);
                maxSilverAmount += Math.min(carryingSilver, moveCount * carryingWeight);
                carriableWeight +=  Math.min(carryingGold + carryingSilver, moveCount * carryingWeight);
            }
            
            if (requiredGold <= maxGoldAmount && requiredSilver <= maxSilverAmount && requiredGold + requiredSilver <= carriableWeight) {
                minPossibleTime = currentTime;
                maxTime = currentTime - 1;
            } else {
                minTime = currentTime + 1;
            }
        }
        
        return minPossibleTime;
    }
}