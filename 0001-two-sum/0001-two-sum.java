class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> byIndexes = new HashMap<>();
        int size = nums.length;

        for (int i = 0; i < nums.length; i++) {
            int differenceWithTarget = target - nums[i];

            if (byIndexes.contains(differenceWithTarget)) {
                return new int[]{byIndexes.get(differenceWithTarget), i};
            }
            byIndexes.put(nums[i], i);
        }
        return null;
    }
}
