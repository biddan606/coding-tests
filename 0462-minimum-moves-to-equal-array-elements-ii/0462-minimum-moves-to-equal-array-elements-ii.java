class Solution {
    /*
    [1, 2, 3] -> [1, 1, 3](1) -> [1, 1, 1](2) => 3
    [1, 2, 3] -> [2, 2, 3](1) -> [2, 2, 2][1] => 2
    */
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);

        int median = nums[nums.length / 2];
        int moveCount = 0;
        
        for (int i = 0; i < nums.length; i++) {
            moveCount += Math.abs(nums[i] - median);
        }

        return moveCount;
    }
}
