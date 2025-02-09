class Solution {
    /*
    badPair 개수를 반환한다. 
    - badPair: i < j && (j - i != nums[j] - nums[i])

    - 배열의 개수가 10^5이므로 브루트포스(O(n^2))로는 할 수 없다.
    - 이전 값만 비교하므로, 배열을 순회할 때마다 index와 value 관계를 저장해두고,
    해당 관계가 아닌 것들만 badPair 처리한다.
    
    예제:
    [4, 1, 3, 3]
    index: 0, value: 4 :: index와 value 관계가 -4이다. 0 - 0 = 0
    index: 1, value: 1 :: index와 value 관계가 0이다. 1 - 0 = 1
    index: 2, value: 3 :: index와 value 관계가 1이다. 2 - 0 = 2
    index: 3, value: 3 :: index와 value 관계가 0이다. 3 - 1 = 2
    result = 0 + 1 + 2 + 2 = 5

    주의: (10^5)! 굉장히 큰 숫자가 될 수 있다 -> long 처리
    */
    public long countBadPairs(int[] nums) {
        long badPairs = 0;
        Map<Integer, Long> relationCounts = new HashMap<>();
        int total = 0;
        
        for(int i = 0; i < nums.length; i++) {
            int relation = i - nums[i];
            Long relationCount = relationCounts.getOrDefault(relation, 0L);

            badPairs += total - relationCount;

            relationCounts.put(relation, relationCount + 1);
            total++;
        }

        return badPairs;
    }
}
