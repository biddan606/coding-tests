class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < k - 1; i++) {
            removeLessThanOrEqual(nums, deque, nums[i]);
            deque.addLast(i);
        }

        int[] result = new int[nums.length - k + 1];

        for (int i = k - 1; i < nums.length; i++) {
            removeOutOfRange(deque, i - k + 1);
            removeLessThanOrEqual(nums, deque, nums[i]);
            
            deque.addLast(i);

            result[i - k + 1] = nums[deque.getFirst()];
        }

        return result;
    }

    private void removeLessThanOrEqual(int[] nums, Deque<Integer> deque, int value) {
        while (!deque.isEmpty() && nums[deque.getLast()] <= value) {
            deque.removeLast();
        }
    }

    private void removeOutOfRange(Deque<Integer> deque, int rangeIndex) {
        if (!deque.isEmpty() && deque.getFirst() < rangeIndex) {
            deque.removeFirst();
        }
    }
}