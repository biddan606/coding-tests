class Solution {
    public long continuousSubarrays(int[] nums) {
        TreeMap<Integer, Integer> numberCounts = new TreeMap<>();
        int left = 0;
        int right = 0;
        long subarrayCount = 0L;
        
        while (right < nums.length) {
            numberCounts.put(nums[right], numberCounts.getOrDefault(nums[right], 0) + 1);

            while (!numberCounts.isEmpty() && numberCounts.lastKey() - numberCounts.firstKey() > 2) {
                subarrayCount += right - left;
                
                numberCounts.put(nums[left], numberCounts.get(nums[left]) - 1);
                if (numberCounts.get(nums[left]) == 0) {
                    numberCounts.remove(nums[left]);
                }
                left++;
            }

            right++;
        }

        long remaingSize = right - left;
        return subarrayCount + (long) remaingSize * (remaingSize + 1) / 2;
    }
}
