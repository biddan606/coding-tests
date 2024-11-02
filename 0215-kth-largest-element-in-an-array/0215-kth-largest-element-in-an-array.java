class Solution {
    private int[] array;
    private int kth;

    public int findKthLargest(int[] nums, int k) {
        array = nums;
        kth = k - 1;

        sort(0, nums.length - 1);
        return array[kth];
    }

    private void sort(int left, int rightInclusive) {
        int pivot = array[rightInclusive];
        int pivotIndex = left;

        for (int i = left; i < rightInclusive; i++) {
            if (pivot < array[i]) {
                int temp = array[pivotIndex];
                array[pivotIndex] = array[i];
                array[i] = temp;
                pivotIndex++;
            }
        }

        int temp = array[pivotIndex];
        array[pivotIndex] = array[rightInclusive];
        array[rightInclusive] = temp;
        
        if (pivotIndex < kth) {
            sort(pivotIndex + 1, rightInclusive);
        } else if (pivotIndex > kth) {
            sort(left, pivotIndex - 1);
        }
    }
}
