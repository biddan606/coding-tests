class Solution {
    public long solution(int n, int[] times) {
        long startMinute = 1;
        long endMinute = 1_000_000_000_000_000_000L;
        
        while (startMinute < endMinute) {
            long midMinute = (startMinute + endMinute) / 2;
            long throughput = calculateThroughput(times, midMinute);
            
            if (throughput < (long) n) {
                startMinute = midMinute + 1;
            } else {
                endMinute = midMinute;
            }
        }
        
        return startMinute;
    }
    
    private long calculateThroughput(int[] times, long minute) {
        long throughput = 0;
        
        for (int time : times) {
            throughput += minute / time;
        }
        
        return throughput;
    }
}