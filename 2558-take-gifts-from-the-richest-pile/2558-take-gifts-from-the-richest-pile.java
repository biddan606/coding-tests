class Solution {
    public long pickGifts(int[] gifts, int k) {
        /*
        # 요구사항
        - k초 동안 gifts를 줄일 수 있다.(k=4, 4번 줄일 수 있음)
        - 가장 많이 gifts를 줄여야 한다.(최소 sumOfGifts 반환)
        - 줄일 때는 gift의 제곱근까지 줄일 수 있다.
        */
        
        /*
        # 동작 방식
        1. 가장 큰 gift를 선택한다.
        2. gift를 제곱근만큼 줄인다.
        3. k번 반복한다.
        */

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int gift : gifts) {
            pq.offer(gift);
        }

        for (int i = 0; i < k; i++) {
            int max = pq.poll();
            pq.offer((int) Math.sqrt(max));
        }        

        long sum = 0;
        while (!pq.isEmpty()) {
            sum += pq.poll();
        }
        return sum;
    }
}
