class Solution {
    /*
    # 문제 이해
    배열의 합이 홀수인 개수를 반환한다
    [1, 3, 5]의 배열: [1], [3], [5],
                    [1, 3], [1, 5], [3, 5],
                    [1, 3, 5]
    정답: [1], [3], [5]
        [1, 3, 5]

    # 접근
    - 원소 개수가 최대 10^5이다. O(원소개수^2)는 안된다
    - 패턴을 보니 홀수가 홀수개 있는 경우에 합이 홀수가 된다
        이를 이용하면 될 것 같다
    
    주어진 것:  원소
    필요한 것:  이전 [홀수, 짝수] 집합의 개수

    *주의: sub-array 이전 조합만 계산함
    
    # 구현 스텝
    1. array를 순회한다
    2. 순회하며 홀수 집합의 개수와 짝수 집합의 개수를 업데이트한다
    3. 홀수 집합을 기준으로 totalOddSums를 업데이트한다
    4. totalOddSums를 반환한다
    */

    private static final int MODULUS = 1_000_000_007;

    public int numOfSubarrays(int[] arr) {
        int totalOddSums = 0;
        int oddSums = 0;
        int evenSums = 0;

        for (int num : arr) {
            if ((num & 1) == 1) { // num == odd
                int prevOddSums = oddSums;
                oddSums = evenSums + 1;
                evenSums = prevOddSums;
            } else { // num == even
                evenSums += 1;
            }

            totalOddSums = (totalOddSums + oddSums) % MODULUS;
        }

        return totalOddSums;
    }
}
