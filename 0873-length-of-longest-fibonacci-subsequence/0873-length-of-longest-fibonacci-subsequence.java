class Solution {
    /*
    # 문제 이해
    주어진 배열로 만들 수 있는 최대 피보나치를 반환해야 한다

    # 접근
    - arr.length 최대는 1_000이다 
        시간 복잡도 O(L^2)여도 된다
    - 2개의 수를 합쳐서 다음 수가 될 수 있는지 확인해야 한다
    - 시간 복잡도 O(L^2)이 되므로 모든 조합을 확인해봐도 될 것 같다

    주어진 것: 가능한 수들
    필요한 것: 현재 조합의 다음 피보나치 수

    - value의 최대값이 10억이므로 int로 처리해도 된다

    # 구현 스텝
    1. arr에서 2개의 조합을 뽑는다
    2. 2개의 조합의 피보나치 수가 있다면 count를 증가시키고 한번 더 시행한다
    3. 조합으로 만들 수 있는 피보나치 수가 없을 때까지 수행한다
    4. maxCount를 반환한다

    - 매번 arr를 돌며 피보나치 수를 찾는 것은 비효율적이므로 Set으로 저장해둔다

    # 최적화
    이미 살펴본 조합을 다시 살펴볼 필요는 없다
    작은 수부터 살펴보기 때문에 항상 이전에 살펴본 경우가 더 클 것이다
    */
    public int lenLongestFibSubseq(int[] arr) {
        int size = arr.length;
        
        Map<Integer, Integer> valuesByIndex = new HashMap<>();
        
        for (int i = 0; i < size; i++) {
            valuesByIndex.put(arr[i], i);
        }

        int maxCount = 0;
        int[][] dp = new int[size][size];

        for (int secondIndex = 0; secondIndex < size; secondIndex++) {
            int second = arr[secondIndex];

            for (int fibonacciIndex = secondIndex + 1; fibonacciIndex < size; fibonacciIndex++) {
                int first = arr[fibonacciIndex] - second;

                dp[secondIndex][fibonacciIndex] = 2; // 기본값
                Integer firstIndex = valuesByIndex.get(first);
                if (firstIndex != null && first < second) {
                    dp[secondIndex][fibonacciIndex] = dp[firstIndex][secondIndex] + 1;
                }
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                maxCount = Math.max(maxCount, dp[i][j]);
            }
        }

        return maxCount == 2 ? 0 : maxCount;
    }
}
