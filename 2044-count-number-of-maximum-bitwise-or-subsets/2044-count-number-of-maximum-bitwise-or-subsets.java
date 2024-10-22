class Solution {
    private int subsetCount;
    private int totalOrNumber;

    public int countMaxOrSubsets(int[] nums) {
        totalOrNumber = 0;
        for (int num : nums) {
            totalOrNumber |= num;
        }

        subsetCount = 0;

        backtracking(nums, 0, 0);

        return subsetCount;
    }

    private void backtracking(int[] nums, int startIndex, int currentNumber) {
        if (currentNumber == totalOrNumber) {
            subsetCount++;
        }

        for (int i = startIndex; i < nums.length; i++) {
            backtracking(nums, i + 1, currentNumber | nums[i]);
        }
    }
}
