class Solution {
    /*
    [1, 2, 3] -> [1, 1, 3](1) -> [1, 1, 1](2) => 3
    [1, 2, 3] -> [2, 2, 3](1) -> [2, 2, 2][1] => 2
    */
    public int minMoves2(int[] nums) {
        int median = select(nums, nums.length / 2);
        int moveCount = 0;
        
        for (int i = 0; i < nums.length; i++) {
            moveCount += Math.abs(nums[i] - median);
        }

        return moveCount;
    }

    // median of median
    private int select(int[] nums, int k) {
        int[] copied = new int[nums.length];
        for (int i = 0; i < copied.length; i++) {
            copied[i] = nums[i];
        }

        return select(copied, k, 0, copied.length - 1);
    }

    // k=1, left=0, right=2
    // 
    private int select(int[] nums, int k, int left, int right) {
        if (left == right) {
            return nums[left];
        }

        int pivotValue = findPivotValue(nums, left, right);
        
        int pivotIndex = -1;
        for (int i = left; i <= right; i++) {
            if (nums[i] == pivotValue) {
                pivotIndex = i;
                break;
            }
        }
        swap(nums, pivotIndex, right);
        
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (nums[i] < pivotValue) {
                swap(nums, i, storeIndex);
                storeIndex++;
            }
        }

        swap(nums, storeIndex, right);

        if (k == storeIndex) {
            return nums[k];
        } else if (k < storeIndex) {
            return select(nums, k, left, storeIndex - 1);
        } else {
            return select(nums, k, storeIndex + 1, right);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private int findPivotValue(int[] nums, int left, int right) {
        if (right - left <= 5) {
            return findMedian(nums, left, right);
        }

        int rangeSize = right - left + 1;
        int groupCount = (rangeSize + 4) / 5;
        int[] medians = new int[groupCount];

        for (int i = 0; i < groupCount; i++) {
            int groupLeft = left + i * 5;
            int groupRight = Math.min(groupLeft + 4, right);

            medians[i] = findMedian(nums, groupLeft, groupRight);
        }

        return findPivotValue(medians, 0, medians.length - 1);
    }

    private int findMedian(int[] nums, int left, int right) {
        int[] sorted = Arrays.copyOfRange(nums, left, right + 1);
        Arrays.sort(sorted);

        return sorted[sorted.length / 2];
    }
}
