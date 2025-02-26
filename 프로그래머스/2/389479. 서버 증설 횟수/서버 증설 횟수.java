class Solution {
    /*
    # 문제 이해
    - 항상 모든 유저가 게임할 수 있는 서버 상태가 되기 위해
        필요한 서버 증설 횟수를 반환해야 한다
    - 서버는 k시간동안 m 유저를 수용할 수 있다
    
    # 접근
    - 처음 서버가 1대 운영되고 있다 m-1명의 유저 수용 가능
    
    주어진 것: 시간대별 플레이어 수, 서버 지속시간과 수용 인원
    필요한 것: 현재 서버의 개수(서버가 지속시간동안만 유지될 수 있게)
    
    - 24시간동안만 운영된다
    - 24밖에 안되므로 많은 반복문을 사용해도 됨
    
    # 구현 스텝
    1. 각 시간당 필요한 players를 살펴본다(1 -> 24)
    2. 서버의 증설이 필요하면 serverCountsByTime[i]~serverCountsByTime[i+k]까지 추가한다
    3. 증설할 때마다 addedServerCount++
    4. addedServerCount를 반환한다
    */
    private static final int MAX_TIME = 24;
    
    public int solution(int[] players, int m, int k) {
        int addedServerCount = 0;
        int[] serverCountsByTime = new int[MAX_TIME];
        
        // 각 시간동안을 살핌
        for (int currentTime = 0; currentTime < MAX_TIME; currentTime++) {
            // 서버 증설이 필요한 개수
            int requiredServerCount = Math.max(0, players[currentTime] / m - serverCountsByTime[currentTime]);
            if (requiredServerCount == 0) {
                continue;
            }
            
            // k 시간 동안 증설
            for (int nextTime = currentTime; nextTime < Math.min(currentTime + k, MAX_TIME); nextTime++) {
                serverCountsByTime[nextTime] += requiredServerCount;
            }
            addedServerCount += requiredServerCount;
        }
        
        return addedServerCount;
    }
}
