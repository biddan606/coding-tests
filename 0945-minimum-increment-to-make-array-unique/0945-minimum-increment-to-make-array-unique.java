class Solution {
    public int minIncrementForUnique(int[] nums) {
        int[] counts = new int[200_000];
        for (int num : nums) {
            counts[num]++;
        }
        
        int moves = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] < 2) {
                continue;
            }

            int currentMoves = counts[i] - 1;
            counts[i + 1] += currentMoves;
            moves += currentMoves;
            counts[i] = 1;
        }

        return moves;
    }
}
