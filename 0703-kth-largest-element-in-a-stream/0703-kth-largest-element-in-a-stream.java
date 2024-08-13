class KthLargest {
    private final LinkedList<Integer> sorted;
    private final int nthIndex;

    public KthLargest(int k, int[] nums) {
        nthIndex = k - 1;

        // 정렬된 LinkedList로 보관한다. 
        // LinkedList가 add, get시 O(N)으로 빠름
        sorted = Arrays.stream(nums)
            .boxed()
            .sorted(Collections.reverseOrder())
            .collect(Collectors.toCollection(LinkedList::new));
    }
    
    public int add(int val) {
        int indexToAdd = 0;
        
        for (int v : sorted) {
            if (v <= val) {
                break;
            }

            indexToAdd++;
        }

        sorted.add(indexToAdd, val);

        return sorted.get(nthIndex);
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
 