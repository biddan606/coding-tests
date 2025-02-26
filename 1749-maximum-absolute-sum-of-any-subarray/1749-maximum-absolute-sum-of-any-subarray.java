class Solution {
    /*
    # 문제 이해
    sub-array의 합의 절대가 가장 큰 것을 반환해야 한다

    # 접근
    - nums의 최대 길이가 10^5이므로 O(L^2)은 안된다
    - 원소는 최대 10_000이고 최대 개수는 100_000이므로 
        최대값들이 오더라도 10억이다 int로 저장해도 된다
    - 원소들을 합한 후 절대값을 씌우는 형태이므로 
        이전 합이 음수라면 초기화한다
    - 현재 값이 음수라면 이전 합이 음수일 때 더 큰 수가 나온다
        음수합과 양수합을 별도로 관리해야 할 것 같다

    # 구현 스텝
    1. nums를 순회한다
    2. 이전 합(음수 합, 양수 합)에 현재 num을 추가한다
    3. 최대 절대 합을 갱신한다
    4-1. 음수 합이 양수가 되면 초기화한다
    4-2. 양수 합이 음수가 되면 초기화한다
    5. 최대 절대 합을 반환한다

    # 최적화
    sub-array[i+k]+[i+k-1]... - (sub-array[j+l]+[j+l-1]...)
    이 형태이다
    `최대값 - 최소값`거나 `최소값 - 최대값`을 빼는 형태가 된다

    다만, 첫 상태는 항상 0이다 왜냐하면 0도 유효한 상태이기 때문이다
    */
    public int maxAbsoluteSum(int[] nums) {
        int prefixSum = 0;
        int maxSum = 0;
        int minSum = 0;

        for (int num : nums) {
            prefixSum += num;

            maxSum = Math.max(maxSum, prefixSum);
            minSum = Math.min(minSum, prefixSum);
        }

        return maxSum - minSum;
    }
}
