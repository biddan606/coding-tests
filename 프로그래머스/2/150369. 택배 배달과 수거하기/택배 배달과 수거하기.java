import java.util.*;

class Solution {
    
    /*
    # 문제 이해
    n개의 집에 배달/수거를 해야 합니다.
    - 수거는 빈 공간이 있을 때 할 수 있습니다
    - 배달은 물건을 싣고 있는 경우 할 수 있습니다
    
    최소한의 거리를 반환해야 합니다
    n번째 집으로 갈 경우, 왔다갔다 n*2 거리가 발생합니다
    
    # 접근
    n은 최대 100_000입니다
    delivery, pickup은 각각 최대 50입니다
    
    최대 연산 수를 생각해보면, 
    cap = 1, delivery, pickup = 50, n = 100_000
    1집당 100번을 방문해야 합니다 100 * 100_000 = 10_000_000
    
    최소한 거리가 되기 위해 내가 방문해야할 곳을 알야아 함
    필요한 것: 배달해야할 곳, 수거해야할 곳
    
    가는 길에 배달할 곳들을 배달한다
    돌아오는 길에 수거해야 할 곳들을 수거한다
    이렇게 되면 5 -> 4 -> 5 이런 식으로 방문하지 않고 한 방향으로만 진행할 수 있다
    (최소한의 거리로 물건을 옮김)
    
    # 구현 스텝
    1. deliveries, pickups를 각 deque에 저장한다
        - 마지막 집이 가장 앞에 있음
    2. 두 deque가 비어있지 않을 때까지 반복한다
    3. 각 반복마다 cap만큼을 제거할 수 있다
    4. deliveryDeque의 현재 마지막 집에 배송한다
        - cap이 남으면 다음 집도 배송한다
        - cap이 부족하면 부족한 크기만큼 deliveryDeque에 추가한다(가장 앞)
    5. pickupDeque의 현재 마지막 집에 수거한다
        - cap이 남으면 다음 집도 수거한다
        - cap이 부족하면 부족한 크기만큼 pickupDeque의 수거한다(가장 앞)
    6. 현재 반복에서 방문한 가장 먼 집 * 2만큼 거리로 추가한다
    7. 반복이 끝나면 지금까지의 거리를 반환한다
    */
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        // [0]: 거리, [1]: 값
        Deque<int[]> deliveryDeque = new ArrayDeque<>();
        Deque<int[]> pickupDeque = new ArrayDeque<>();
        
        for (int i = 0; i < n; i++) {
            if (deliveries[i] > 0) {
                deliveryDeque.addFirst(new int[]{i + 1, deliveries[i]});
            }
            if (pickups[i] > 0) {
                pickupDeque.addFirst(new int[]{i + 1, pickups[i]});
            }
        }
        
        long totalDistance = 0;
        
        while (!deliveryDeque.isEmpty() || !pickupDeque.isEmpty()) {
            int deliveryCap = cap;
            int currentMaxDistance = 0;
            
            while (!deliveryDeque.isEmpty() && deliveryCap > 0) {
                int[] delivery = deliveryDeque.removeFirst();
                
                int remainingDelivery = Math.max(delivery[1] - deliveryCap, 0);
                if (remainingDelivery > 0) {
                    deliveryDeque.addFirst(new int[]{delivery[0], remainingDelivery});
                }
                
                deliveryCap -= delivery[1] - remainingDelivery;
                currentMaxDistance = Math.max(currentMaxDistance, delivery[0]);
            }
            
            int pickupCap = cap;
            
            while (!pickupDeque.isEmpty() && pickupCap > 0) {
                int[] pickup = pickupDeque.removeFirst();
                
                int remainingPickup = Math.max(pickup[1] - pickupCap, 0);
                if (remainingPickup > 0) {
                    pickupDeque.addFirst(new int[]{pickup[0], remainingPickup});
                }
                
                pickupCap -= pickup[1] - remainingPickup;
                currentMaxDistance = Math.max(currentMaxDistance, pickup[0]);
            }
            
            totalDistance += currentMaxDistance * 2;
        }
        
        return totalDistance;
    }
}
