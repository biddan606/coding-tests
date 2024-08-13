class KthLargest {
    private final PriorityQueue<Integer> minHeap;
    private final int k;

    public KthLargest(int k, int[] nums) {
        // 모든 값을 저장할 필요 없음, k개만 유지하면 됨
        this.k = k;
        minHeap = new PriorityQueue<>(k);

        for (int n : nums) {
            minHeap.offer(n);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
    }
    
    public int add(int val) {
        minHeap.offer(val);

        // 새로운 값이 추가되는 순간 k를 만족할 수 있음, 이 때는 제거해서는 안됨
        if (minHeap.size() > k) {
            minHeap.poll();
        }

        return minHeap.peek();
    }
}
