class Solution {
    public int maximumBeauty(int[] nums, int k) {
        // nums[i] -> nums[i] - k <= x <= nums[i] + k로 변환할 수 있다.
        // 모든 수가 가장 많이 변환할 수 있는 수를 찾는다.

        // nums[i]를 카운트한다.
        int[] cumulativeCounts = new int[100_000 + 2];
        for (int num : nums) {
            cumulativeCounts[num]++;
        }

        // 카운트를 내림차순으로 누적한다.
        for (int i = cumulativeCounts.length - 2; i >= 0; i--) {
            cumulativeCounts[i] += cumulativeCounts[i + 1];
        }

        // nums[i - k] <= x <= nums[i + k] 범위의 값이 가장 높은 값을 반환한다.
        int maxCount = 0;
        for (int i = 0; i < cumulativeCounts.length; i++) {
            int left = Math.max(0, i - k);
            int right = Math.min(cumulativeCounts.length - 1, i + k + 1);

            maxCount = Math.max(maxCount, cumulativeCounts[left] - cumulativeCounts[right]);
        }

        return maxCount;
    }
}
