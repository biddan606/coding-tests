class Solution {
    private int[] mask;

    public int minimumSubarrayLength(int[] nums, int k) {
        mask = new int[31];
        int left = 0;
        int min = Integer.MAX_VALUE;
        for (int right = 0; right < nums.length; right++) {
            addNumber(nums[right]);
            int current = getNumber();

            while (left <= right && current >= k) {
                min = Math.min(min, right - left + 1);
                removeNumber(nums[left]);
                current = getNumber();
                left++;
            }
        }

        if (min == Integer.MAX_VALUE) {
            return -1;
        }
        return min;
    }

    private void addNumber(int number) {
        for (int i = 0; i < mask.length && number > 0; i++) {
            mask[i] += number & 1;
            number >>= 1;
        }
    }

    private int getNumber() {
        int number = 0;
        int bitPosition = 1;
        for (int i = 0; i < mask.length; i++) {
            if (mask[i] > 0) {
                number += bitPosition;
            }
            bitPosition <<= 1;
        }
        return number;
    }

    private void removeNumber(int number) {
        for (int i = 0; i < mask.length && number > 0; i++) {
            mask[i] -= number & 1;
            number >>= 1;
        }
    }
}
