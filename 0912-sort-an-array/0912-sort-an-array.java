class Solution {
    /**
     * 오름차순으로 정렬한다.
     * 제약: 시간복잡도 O(NlongN), 공간 복잡도: 미정, 최소
     */
    public int[] sortArray(int[] nums) {
        return mergeSort(nums, 0, nums.length);
    }

    private int[] mergeSort(int[] nums, int start, int end) {
        int size = end - start;
        if (size <= 1) {
            return Arrays.copyOfRange(nums, start, end);
        }

        int mid = (start + end) / 2;
        int[] left = mergeSort(nums, start, mid);
        int[] right = mergeSort(nums, mid, end);

        return merge(left, right);
    }

    private int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        int l = 0;
        int r = 0;

        while (l < left.length && r < right.length) {
            if (left[l] <= right[r]) {
                result[i++] = left[l++];
            } else {
                result[i++] = right[r++];
            }
        }

        while (l < left.length) {
            result[i++] = left[l++];
        }
        
        while (r < right.length) {
            result[i++] = right[r++];
        }

        return result;
    }
}
