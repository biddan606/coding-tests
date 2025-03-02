class Solution {
    public int[] applyOperations(int[] nums) {
        for (int i = 0; i + 1 < nums.length; i++) {
            if (nums[i] == nums[i + 1]) {
                nums[i] *= 2;
                nums[i + 1] = 0;
            }
        }

        int[] result = new int[nums.length];
        int resultIndex = 0;
        
        for (int num : nums) {
            if (num != 0) {
                result[resultIndex] = num;
                resultIndex++;
            }
        }

        return result;
    }
}
