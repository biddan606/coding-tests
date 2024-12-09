class Solution {
    public int minimumSize(int[] nums, int maxOperations) {
        // maxOperations보다 작거나 같은 연산을 사용하여, 가능한 min-value를 찾는다.
        int left = 1;
        int right = 1_000_000_000;

        while (left <= right) {
            int mid = (left + right) / 2;
            
            if (operate(nums, mid) <= maxOperations) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static int operate(int[] nums, int target) {
        int operations = 0;

        for (int num : nums) {
            operations += Math.max(0, ((num + target - 1) / target) - 1);
        }
        return operations;
    }
}
