class Solution {
    public int minIncrementForUnique(int[] nums) {
        int[] numbers = new int[200_000];
        int nonUniques = 0;
        int min = 0;
        for (int num : nums) {
            numbers[num]++;
            if (numbers[num] > 1) {
                nonUniques++;
            }
            min = Math.min(min, num);
        }

        int moves = 0;
        int unallocated = 0;
        int currentNumber = min;
        while (nonUniques > 0) {
            int currentCount = numbers[currentNumber];

            if (currentCount > 1) {
                int currentUnallocated = currentCount - 1;
                moves -= currentUnallocated * currentNumber;
                unallocated += currentUnallocated;
            } else if (unallocated > 0 && currentCount == 0){
                moves += currentNumber;
                nonUniques--;
                unallocated--;
            }

            currentNumber++;
        }

        return moves;
    }
}
