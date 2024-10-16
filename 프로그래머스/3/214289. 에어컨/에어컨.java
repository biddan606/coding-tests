import java.util.*;

class Solution {
    private static int MIN_TEMPERATURE = -10;
    private static int MAX_TEMPERATURE = 40;
    private static int MAX_VALUE = 1_000 * 100;
    
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int times = onboard.length;
        
        // 시간별 온도의 최소 비용을 저장한다.
        int[][] dp = new int[times + 1][MAX_TEMPERATURE - MIN_TEMPERATURE + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], MAX_VALUE);
        }
        dp[1][getIndex(temperature)] = 0;
        if (temperature > MIN_TEMPERATURE) {
            dp[1][getIndex(temperature - 1)] = a;
        }
        if (temperature < MAX_TEMPERATURE) {
            dp[1][getIndex(temperature + 1)] = a;
        }
        
        for (int i = 1; i < times; i++) {
            /*
            가능한 상태
            - 에어컨을 끈다.
            - 에어컨을 현재 온도보다 낮춘다.
            - 에어컨을 현재 온도보다 높인다.
            - 에어컨을 현재 온도와 동일하게 한다.
            */
            // 현재 온도 + 상태 설정 -> 다음 온도
            int startTemperature = MIN_TEMPERATURE;
            int endTemperature = MAX_TEMPERATURE;
            if (onboard[i] == 1) {
                startTemperature = t1;
                endTemperature = t2;
            }
            
            for (int currentTemp = startTemperature; currentTemp <= endTemperature; currentTemp++) {
                int index = getIndex(currentTemp);
                
                // 에어컨을 끈다. 비용이 들지 않는다.
                if (temperature == currentTemp) {
                    dp[i + 1][index] = Math.min(dp[i + 1][index], dp[i][index]);
                } else if (temperature > currentTemp && index + 1 < dp[i + 1].length) {
                    dp[i + 1][index + 1] = Math.min(dp[i + 1][index + 1], dp[i][index]);
                } else if (temperature < currentTemp && 0 <= index - 1) {
                    dp[i + 1][index - 1] = Math.min(dp[i + 1][index - 1], dp[i][index]);
                }
                
                // 에어컨을 현재 온도보다 낮춘다.
                if (0 <= index - 1) {
                    dp[i + 1][index - 1] = Math.min(dp[i + 1][index - 1], dp[i][index] + a);
                }
                
                // 에어컨을 현재 온도보다 높인다.
                if (index + 1 < dp[i + 1].length) {
                    dp[i + 1][index + 1] = Math.min(dp[i + 1][index + 1], dp[i][index] + a);
                }
                
                // 에어컨을 현재 온도와 동일하게 한다.
                dp[i + 1][index] = Math.min(dp[i + 1][index], dp[i][index] + b);
            }
        }
        
        // 마지막 온도는 onboard 이후의 온도이므로 최소값만 찾으면 된다.
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < dp[times].length; i++) {
            min = Math.min(min, dp[times][i]);
        }
        return min;
    }
    
    private int getIndex(int temperature) {
        return temperature - MIN_TEMPERATURE;
    }
}
