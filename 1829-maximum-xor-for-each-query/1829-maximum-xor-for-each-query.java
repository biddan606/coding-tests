class Solution {
    public int[] getMaximumXor(int[] nums, int maximumBit) {
        int size = nums.length;
        int max = (1 << maximumBit) - 1;

        int xor = Arrays.stream(nums).reduce(0, (a, b) -> a ^ b);
        int[] result = new int[size];

        for (int i = 0; i < size; i++) {
            result[i] = max - xor;
            xor ^= nums[size - 1 - i];
        }

        return result;
    }
}
