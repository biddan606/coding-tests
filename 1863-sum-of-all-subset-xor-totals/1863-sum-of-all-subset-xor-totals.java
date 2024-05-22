class Solution {
    private int sum = 0;

    public int subsetXORSum(int[] nums) {
        dfs(nums, 0, 0);
        return sum;
    }

    private void dfs(int[] nums, int value, int index) {
        if (nums.length == index) {
            return;
        }

        // nums[index]값을 XOR하지 않거나 한다.
        dfs(nums, value, index + 1);

        int xor = value ^ nums[index];
        sum += xor;
        dfs(nums, xor, index + 1);
    }
}
