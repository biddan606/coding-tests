class Solution {
    public int solution(int[] money) {
        int size = money.length;
        int max = 0;
        
        int[] dp1 = new int[size];
        dp1[0] = money[0];
        dp1[1] = Math.max(money[0], money[1]);
        for (int i = 2; i < size - 1; i++) {
            dp1[i] = Math.max(dp1[i - 2] + money[i], dp1[i - 1]);
            max = Math.max(max, dp1[i]);
        }
        
        int[] dp2 = new int[size];
        dp2[size - 1] = money[size - 1];
        dp2[size - 2] = Math.max(money[size - 1], money[size - 2]);
        for (int i = size - 3; i >= 1; i--) {
            dp2[i] = Math.max(dp2[i + 2] + money[i], dp2[i + 1]);
            max = Math.max(max, dp2[i]);
        }
        
        return max;
    }
}
