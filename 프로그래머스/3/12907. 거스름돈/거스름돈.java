class Solution {
    // 주어진 코인을 활용해서 n을 만들어야 한다.
    // 코인은 여러 번 사용할 수 있다.
    // 조합이 중복되면 안된다.
    public int solution(int n, int[] money) {
        // money[0]으로 만들 수 있는 조합, money[0] + money[1]로 만들 수 있는 조합..., money[0] + ... + money[money.length - 1]로 만들 수 있는 조합을 늘려간다.
        int[] dp = new int[n + 1];
        dp[0] = 1;
        
        for (int mI = 0; mI < money.length; mI++) {
            int currentMoney = money[mI];
            
            for (int dI = currentMoney; dI < dp.length; dI++) {
                dp[dI] += dp[dI - currentMoney];
            }
        }
        
        return dp[n];
    }
}
