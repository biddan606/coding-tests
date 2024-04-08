import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        if (n > s) {
            return new int[]{-1};
        }
        
        int[] result = new int[n];
        int quotient = s / n;
        int remainder = s % n;
        
        Arrays.fill(result, quotient);
        
        for (int i = n - remainder; i < n; i++) {
            result[i]++;
        }
            
        return result;
    }
}