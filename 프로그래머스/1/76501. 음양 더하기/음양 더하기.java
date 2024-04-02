class Solution {
    public int solution(int[] absolutes, boolean[] signs) {
        int result = 0;
        
        for (int i = 0; i < absolutes.length; i++) {
            int current = absolutes[i];
            if (!signs[i]) {
                current *= -1;
            }
            
            result += current;
        }
        
        return result;
    }
}