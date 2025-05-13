class Solution {
    public int missingNumber(int[] nums) {
        boolean[] used = new boolean[nums.length + 1];

        for (int num : nums) {
            used[num] = true;
        } 

        for (int i = 0; i < used.length; i++) {
            if (!used[i]) {
                return i;
            }
        }

        return -1;
    }
}
