import java.util.*;

class Solution {
    public int solution(int n, int k) {
        String[] tokens = Long.toString(n, k).split("0+");
        
        int primeCount = 0;
        for (String token : tokens) {
            if (isPrime(Long.parseLong(token))) {
                primeCount++;
            }
        }
        
        return primeCount;
    }
    
    private boolean isPrime(long number) {
        if (number <= 1) {
            return false;
        }
        
        for (long l = 2; l * l <= number; l++) {
            if (number % l == 0) {
                return false;
            }
        }
        
        return true;
    }
}