class Solution {
    /*
    # 문제 이해
    positives와 negatives의 개수 중 많은 개수를 반환해야 한다
    positives: 0보다 큰 수
    negatives: 0보다 작은 수

    # 접근
    nums는 오름차순으로 정렬되어 있다
    negatives -> 0 -> positives의 개수를 순서대로 셀 수 있다

    # 구현 스텝
    1. index를 증가시키며, negatives의 개수를 센다
    2. negatives 개수를 저장한다
    3. index를 증가시키며, 0을 지나친다
    4. index를 증가시키며, positives의 개수를 센다
    5. positives의 개수를 저장한다
    6. negatives, positives 개수를 비교하여 큰 값을 찾는다
    7. 큰 값을 반환한다
    ---

    # 다른 풀이
    이진 탐색을 이용해서 해결할 수 있다
    
    first-0와 last-0를 찾을 수 있다면, 
    first-0 앞에는 last-negative가 오게되고
    last-0 뒤에는 first-positive가 온다
    - last-negative 앞에는 전부 negative이므로 총 negatives를 알 수 있음
    - first-positive 뒤에는 전부 positive이므로 총 positives를 알 수 있음

    first-0와 last-0는 이진 탐색으로 빠르게 찾을 수 있다
    */
    public int maximumCount(int[] nums) {
        int index = 0;
        int size = nums.length;

        while (index < size && nums[index] < 0) {
            index++;
        }

        int negativeCount = index;

        while (index < size && nums[index] == 0) {
            index++;
        }

        int positiveCount = nums.length - index;

        return (int) Math.max(negativeCount, positiveCount);
    }
}
