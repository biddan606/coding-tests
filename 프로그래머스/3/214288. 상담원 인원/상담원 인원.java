import java.util.*;

class Solution {
    
    private int minWaitingTime = Integer.MAX_VALUE;
    
    public int solution(int k, int n, int[][] reqs) {
        // 멘토들의 초기화을 설정한다.
        // 멘토들은 타입당 최소 1명씩 배정되어 있어야 한다.
        int[] mentos = new int[k];
        Arrays.fill(mentos, 1);
        n -= k; 
            
        // 모든 경우의 수를 계산한다.
        calcaulateAllCase(mentos, 0, n, reqs);
        
        return minWaitingTime;
    }
    
    // dfs
    private void calcaulateAllCase(int[] typeMentos, int index, int remainingMentos, int[][] requests) {
        if (typeMentos.length == index) {
            int waitingTime = calculateWaitingTime(typeMentos, requests);
            minWaitingTime = Math.min(minWaitingTime, waitingTime);
            return;
        }
        
        for (int n = 0; n <= remainingMentos; n++) {
            typeMentos[index] += n;
            
            calcaulateAllCase(typeMentos, index + 1, remainingMentos - n, requests);
            
            typeMentos[index] -= n;
        }
    }
    
    // queue
    private int calculateWaitingTime(int[] typeMentos, int[][] requests) {
        // 각 타입멘토들의 끝나는 시간을 저장해둔다.
        PriorityQueue<Integer>[] typePq = new PriorityQueue[typeMentos.length];
        for (int i = 0; i < typeMentos.length; i++) {
            typePq[i] = new PriorityQueue<>();
            
            for (int j = 0; j < typeMentos[i]; j++) {
                typePq[i].offer(0);
            }
        }
        
        int waitingTime = 0;
        for (int[] request : requests) {
            int arrivalTime = request[0];
            int requiredTime = request[1];
            int type = request[2] - 1;
            
            PriorityQueue<Integer> pq = typePq[type];
            int availableTime = pq.poll();
            
            // 참가자가 도착할 때부터 상담이 종료할 때까지 걸린 총 시간
            int processingTime = Math.max(arrivalTime, availableTime) + requiredTime - arrivalTime;
            waitingTime += processingTime - requiredTime;
            
            // 상담 종료된 시간을 다시 넣는다.
            pq.offer(arrivalTime + processingTime);
        }
            
        return waitingTime;
    }
}
