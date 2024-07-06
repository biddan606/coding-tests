class Solution {
    /**
     * 가장 작은 숫자와 가장 큰 숫자의 최소한의 차이를 구해야 합니다.
     * 최대 3개의 숫자를 원하는 숫자로 변경할 수 있습니다.
     * -----------------------------------------------
     * 같은 숫자일 경우 차이는 0입니다.
     * 숫자를 변경하여 차이가 최소화되려면, 변경된 숫자는 배열에 이미 존재하는 숫자여야 합니다.
     * 또한, 변경된 숫자가 배열에 이미 존재한다면 존재하는 숫자로 대체할 수 있으므로 삭제한다고도 표현할 수 있습니다.
     */
    public int minDifference(int[] nums) {
        // nums.length가 4이하라면, 모든 숫자를 동일한 숫자로 변경할 수 있으므로 0을 반환합니다.
        int size = nums.length;
        if (size <= 4) {
            return 0;
        }

        // 숫자 변경 후 차이가 최소화되려면, minNumber 또는 maxNumber를 변경해야 합니다.
        // minNumber 또는 maxNumber를 변경하는 모든 경우의 수를 해봅니다.
        Arrays.sort(nums);
        
        int minDifference = Integer.MAX_VALUE;
        for (int i = 0; i <= 3; i++) {
            int currentDifference = nums[size - 1 - (3 - i)] - nums[i];
            minDifference = Math.min(minDifference, currentDifference);
        }

        return minDifference;
    }
}
