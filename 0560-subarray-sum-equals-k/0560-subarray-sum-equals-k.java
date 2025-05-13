class Solution {
    public int subarraySum(int[] nums, int k) {
        int[] prefixSum = new int[nums.length];
        
        prefixSum[0] = nums[0];
        for (int i = 1; i < prefixSum.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }

        int sumEqualsKCount = 0;
        
        for (int i = 0; i < prefixSum.length; i++) {
            if (prefixSum[i] == k) {
                sumEqualsKCount++;
            }
        }

        for (int i = 0; i < prefixSum.length; i++) {
            for (int j = i + 1; j < prefixSum.length; j++) {
                int currentSum = prefixSum[j] - prefixSum[i];
                if (currentSum == k) {
                    sumEqualsKCount++;
                }
            }
        }

        return sumEqualsKCount;
    }
}
