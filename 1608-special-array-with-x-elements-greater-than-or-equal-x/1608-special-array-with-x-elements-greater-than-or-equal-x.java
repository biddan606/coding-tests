class Solution {
    public int specialArray(int[] nums) {
        Arrays.sort(nums);
        int prevMax = -1;
        
        for (int x = nums.length; x >= 1; x--) {
            int i = nums.length - x;

            if (prevMax >= x) {
                prevMax = Math.max(prevMax, nums[i]);
                continue;
            }

            if (x <= nums[i]) {
                return x;
            }

            prevMax = Math.max(prevMax, nums[i]);
        }

        return -1;
    }
}
