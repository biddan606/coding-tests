class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        int cumulativeSum = 0;
        Map<Integer, Integer> remainderCountMap = new HashMap<>();
        remainderCountMap.put(0, 1);
        int subarrayCount = 0;

        for (int num : nums) {
            cumulativeSum += num;

            int remainder = cumulativeSum % k;
            if (remainder < 0) {
                remainder += k;
            }

            subarrayCount += remainderCountMap.getOrDefault(remainder, 0);

            remainderCountMap.put(remainder, remainderCountMap.getOrDefault(remainder, 0) + 1);
        }

        return subarrayCount;
    }
}
