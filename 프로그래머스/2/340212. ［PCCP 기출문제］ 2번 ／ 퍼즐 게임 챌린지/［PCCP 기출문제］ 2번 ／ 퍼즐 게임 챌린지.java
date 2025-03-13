class Solution {
    /*
    # 문제 이해
    모든 퍼즐을 풀기 위한 최소한의 레벨을 반환해야 한다
   
   - diff <= level, time_cur 시간에 푼다
   - diff > level, 
        - diff - level번 틀린다
        - 틀릴 때마다 time_cur 시간 사용
        - (diff - level) * (time_cur + time_prev) + time_cur 시간 소요
        - time_prev: 바로 전 문제
    
    # 접근
    - diffs, times의 길이는 최대 300_000
    - diff[i]의 최대값은 100_000이다
    최소 레벨을 구하는 문제고, 안되는 경우 아래를 살펴보면 된다
    이분 탐색을 사용하면 log_2{100_000} 만에 살필 수 있다
    
    # 구현 스텝
    1. level 최소 범위 1, 최대 범위 100_000을 기준으로 이진탐색한다
        2. mid 값을 구한다
        3. mid 값을 기준으로 currentLimit를 구한다
        4. currentLimit <= limit, 더 작은 level을 탐색해본다
        5. currentLimit > limit, 더 큰 level을 탐색해본다
    6. 지금까지 가능했던 가장 작은 레벨을 반환한다
    */
    public int solution(int[] diffs, int[] times, long limit) {
        int size = diffs.length;
        int minLevel = 1;
        int maxLevel = 100_000;
        
        while (minLevel < maxLevel) {
            int currentLevel = (minLevel + maxLevel) >> 1;
            long currentLimit = 0;
            int prevTime = 0;
                
            for (int i = 0; i < size; i++) {
                currentLimit += times[i];
                if (currentLevel < diffs[i]) {
                    currentLimit += (diffs[i] - currentLevel) * (times[i] + prevTime);
                }
                
                prevTime = times[i];
            }
            
            if (currentLimit > limit) { // 안되는 경우
                minLevel = currentLevel + 1;
            } else { // 되는 경우
                maxLevel = currentLevel;
            }
        }
        
        return minLevel;
    }
}
