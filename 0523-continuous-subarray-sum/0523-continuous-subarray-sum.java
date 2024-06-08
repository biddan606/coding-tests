class Solution {
    /**
     * 1. 0 -> nums.length까지의 합을 구합니다.
     * 2. 각 인덱스마다 합을 구한 뒤, 배수가 되기 위한 나머지 값을 찾습니다.
     * 3. 해당 나머지 값이 이전에 존재했다면 true를 반환합니다, 없다면 현재 나머지 값을 맵에 추가합니다.
     * 4. 다음 인덱스로 넘어갑니다.
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0;
        Map<Integer, Integer> remainderToIndex = new HashMap<>();
        remainderToIndex.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            int remainder = sum % k;
            int pairIndex = remainderToIndex.getOrDefault(remainder, i);
            if (i - pairIndex >= 2) {
                return true;
            }

            // 가장 긴 subarray를 만듭니다.
            remainderToIndex.putIfAbsent(remainder, i);
        }

        return false;
    }
}
