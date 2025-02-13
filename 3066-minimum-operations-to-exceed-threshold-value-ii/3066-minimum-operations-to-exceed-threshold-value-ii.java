class Solution {
    public int minOperations(int[] nums, int k) {
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
    
        for (int i = 0; i < nums.length; i++) {
            minHeap.offer((long)nums[i]);
        }

        int operations = 0;
        while (minHeap.peek() < k) {
            long min = minHeap.poll();
            long max = minHeap.poll();

            minHeap.offer(min * 2 + max);

            operations++;
        }

        return operations;
    }
}
