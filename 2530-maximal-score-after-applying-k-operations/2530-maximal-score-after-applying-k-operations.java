class Solution {
    public long maxKelements(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int num : nums) {
            pq.offer(num);
        }

        long sum = 0;
        for (int i = 0; i < k; i++) {
            int num = pq.poll();

            sum += num;

            pq.offer((num + 2)/ 3);
        }

        return sum;
    }
}
