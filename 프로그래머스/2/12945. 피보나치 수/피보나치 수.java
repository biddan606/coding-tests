class Solution {
    public int solution(int n) {
        long[] fibonacci = new long[100_000 + 1];
        fibonacci[0] = 0;
        fibonacci[1] = 1;
        
        for (int i = 2; i < fibonacci.length; i++) {
            fibonacci[i] = (fibonacci[i - 1] + fibonacci[i - 2]) % 1234567;
        }
        
        return (int) fibonacci[n];
    }
}