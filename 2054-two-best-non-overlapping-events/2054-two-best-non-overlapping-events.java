class Solution {
    public int maxTwoEvents(int[][] events) {
        // endTime 기준으로 정렬
        Arrays.sort(events, (a, b) -> a[1] - b[1]);

        TreeMap<Integer, Integer> endTimeMinHeap = new TreeMap<>();
        endTimeMinHeap.put(0, 0);

        int eventValueMax = 0;
        int pairMax = 0;
        for (int[] event : events) {
            int startTime = event[0];
            int endTime = event[1];
            int value = event[2];

            int matchedValue = endTimeMinHeap.lowerEntry(startTime).getValue();
            pairMax = Math.max(pairMax, value + matchedValue);

            // value는 항상 이전 endTime 값보다 크도록 한다. 
            // event를 매칭할 때, 이전 endTime 중 가장 큰 값과 매칭해야 하므로
            eventValueMax = Math.max(eventValueMax, value);
            endTimeMinHeap.put(endTime, eventValueMax);
        }
        return pairMax;
    }
}
