import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        
        long startMinute = 1;
        long endMinute = (long) n * times[0];
        
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