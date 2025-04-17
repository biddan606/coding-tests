class Solution {
    /*
    # 문제 이해
    nums[i] == nums[j] && i * j % k == 0인 개수를 반환해야 한다

    # 풀이 접근
    nums.length는 최대 100이다
    모든 경우의 수를 살펴봐도 넉넉한 길이이므로 브루트 포스로 구현할 수 있다
        - 100 * 100 = 10_000

    # 구현 스텝
    1. nums를 0 -> nums.length까지 순회한다
    2. nums[i]를 뽑는다(i의 범위는 0 -> nums.length)
    3. nums[j]를 뽑는다(j의 범위는 i + 1 -> nums.length)
    4. nums[i] == nums[j] && i * j % k == 0를 만족한다면 count++ 한다
    5. 3으로 돌아가 j를 끝까지 순회한다
    6. 2로 돌아가 i를 끝까지 순회한다
    7. count를 반환한다

    ---

    모든 경우의 수를 확인할 필요가 있을까?

    nums[i] == nums[j] && i * j % k == 0를 만족하는 경우는 2가지 조건이다
    1. nums[i] == nums[j]
    2. i * j % k == 0

    1. nums[i] == nums[j]를 만족하기 위해서는
    같은 숫자들끼리 묶이면 된다(Map)

    2. i * j % k == 0를 만족하기 위해서는
    i 또는 j 1개의 수만 %k를 만족하면 된다

    # 구현 스텝
    1. nums 값들을 같은 숫자끼리 묶는다(key: number, value: index)
    2. 각 key를 순회한다
    3. key의 index들 중 %k를 만족하는 개수를 찾는다
    4. 모든 경우의 수 - %k를 만족하지 않는 경우의 수 = %k를 만족하는 경우의 수를 count에 더한다
    5. count를 반환한다

    **안됨**
    index 2개가 합쳐져서 %k를 만족할 수 있음
    예) i = 2, j = 3, k = 6
    */
    public int countPairs(int[] nums, int k) {
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j] && i * j % k == 0) {
                    count++;
                    
                }
            }
        }

        // Map<Integer, List<Integer>> byNumber = new HashMap<>();
        // for (int i = 0; i < nums.length; i++) {
        //     int number = nums[i];
        //     byNumber.computeIfAbsent(number, _ -> new ArrayList<>())
        //         .add(i);
        // }

        // for (List<Integer> indexes : byNumber.values()) {
        //     int divisibleCount = 0;
        //     for (int index : indexes) {
        //         if (index % k == 0) {
        //             divisibleCount++;                    
        //         }
        //     }

        //     int size = indexes.size();
        //     int prevCount = count;
        //     count += (size - 1) * size / 2 - (size - 1 - divisibleCount) * (size - divisibleCount) / 2;

        //     System.out.println(count - prevCount);
        // }

        return count;
    }
}
