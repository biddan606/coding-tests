class Solution {
    public int[] resultsArray(int[] nums, int k) {
        if (k == 1) {
            return nums;
        }

        int size = nums.length;
        int[] result = new int[size - k + 1];

        // result[0]를 구한다
        int nonConsecutiveCount = 0;
        for (int i = 0; i < k - 1; i++) {
            if (nums[i] + 1 != nums[i + 1]) {
                nonConsecutiveCount++;
            }
        }
        result[0] = -1;
        if (nonConsecutiveCount == 0) {
            result[0] = nums[0 + k - 1];
        }
        
        // 전체 result를 구한다
        // 맨 앞 pair 제거, 현재 pair 추가
        for (int i = 1; i + k - 1 < size; i++) {
            if (nums[i - 1] + 1 != nums[i]) {
                nonConsecutiveCount--;
            }
            if (nums[i + k - 2] + 1 != nums[i + k - 1]) {
                nonConsecutiveCount++;
            }

            result[i] = -1;
            if (nonConsecutiveCount == 0) {
                result[i] = nums[i + k - 1];
            }
        }
        return result;
    }
}
