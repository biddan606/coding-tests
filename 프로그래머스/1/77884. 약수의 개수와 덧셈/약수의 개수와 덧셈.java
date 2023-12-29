class Solution {
    public int solution(int left, int right) {
        int result = 0;
        for (int n = left; n <= right; n++) {
            int divisorCount = 0;
            for (int divisor = 1; divisor <= n; divisor++) {
                if (n % divisor == 0) {
                    divisorCount++;
                }
            }
            
            if (divisorCount % 2 == 0) {
                result += n;
            } else {
                result -= n;
            }
        }
        
        return result;
    }
}