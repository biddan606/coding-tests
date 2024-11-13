class Solution {
    public long countFairPairs(int[] nums, int lower, int upper) {
        // left index <= right index 하기 위해 정렬
        int size = nums.length;
        Arrays.sort(nums);

        long count = 0;
        for (int i = 0; i < size; i++) {
            int currentValue = nums[i];

            // lowerIndex 최소: i + 1, 최대 size
            int lowerIndex = getLowerIndex(nums, i + 1, size - 1, lower - currentValue);
            // upperIndex 최소: i, 최대 size - 1
            int upperIndex = getUpperIndex(nums, i + 1, size - 1, upper - currentValue);
            
            count += upperIndex - lowerIndex + 1;
        }

        return count;
    }

    // lower보다 크거나 같은 값을 만족하는 인덱스 중 가장 작은 인덱스를 반환한다.
    // 만족하는 인덱스가 없을 경우 right + 1을 반환한다.
    private static int getLowerIndex(int[] nums, int left, int right, int lower) {
        while (left <= right) {
            int mid = (left + right) / 2;

            if (nums[mid] >= lower) {
                right = mid - 1;
            } else { // nums[mid] < lower
                left = mid + 1;
            }
        }

        return left;
    }

    // upper보다 작거나 같은 값을 만족하는 인덱스 중 가장 큰 인덱스를 반환한다.
    // 만족하는 인덱스가 없을 경우 left - 1를 반환한다.
    private static int getUpperIndex(int[] nums, int left, int right, int upper) {
        while (left <= right) {
            int mid = (left + right) / 2;

            if (nums[mid] <= upper) {
                left = mid + 1;
            } else { // nums[mid] > upper
                right = mid - 1;
            }
        }

        return right;
    }
}
