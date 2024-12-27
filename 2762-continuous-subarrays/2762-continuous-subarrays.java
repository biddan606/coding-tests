class Solution {
    public long continuousSubarrays(int[] nums) {
        Map<Integer, Integer> numberCounts = new HashMap<>();
        TreeSet<Integer> treeSet = new TreeSet<>();
        Queue<Integer> queue = new LinkedList<>();
        long subarrayCount = 0L;
        
        for (int num : nums) {
            while (!queue.isEmpty() 
            && (Math.abs(treeSet.first() - num) > 2 || Math.abs(treeSet.last() - num) > 2)) {
                subarrayCount += queue.size();
                
                int removed = queue.poll();
                numberCounts.put(removed, numberCounts.get(removed) - 1);
                if (numberCounts.get(removed) == 0) {
                    treeSet.remove(removed);
                }
            }

            queue.offer(num);
            treeSet.add(num);
            numberCounts.put(num, numberCounts.getOrDefault(num, 0) + 1);
        }

        return subarrayCount + (long) queue.size() * (queue.size() + 1) / 2;
    }
}
