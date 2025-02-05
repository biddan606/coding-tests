class Solution {
    public boolean check(int[] nums) {
        int size = nums.length;
        int index = 1;
        int min = nums[0];

        while (index < size && nums[index - 1] <= nums[index]) {
            index++;
        }
        if (index == size) {
            return true;
        } else if (nums[size - 1] > min) {
            return false;
        }

        for (; index + 1 < size; index++) {
            if (nums[index] > nums[index + 1]) {
                return false;
            }
        }
        return true;
    }
}
