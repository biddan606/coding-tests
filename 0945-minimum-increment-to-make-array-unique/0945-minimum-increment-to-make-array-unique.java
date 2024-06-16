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

        int moves= 0;
        for (int i = min; i < numbers.length; i++) {
            if (numbers[i] <= 1) {
                continue;
            }

            int j = i + 1;
            while (numbers[i] > 1) {
                if (numbers[j] >= 1) {
                    j++;
                    continue;
                }

                numbers[i]--;
                nonUniques--;
                moves += j - i;
                numbers[j]++;
                j++;
            }

            if (nonUniques < 1) {
                break;
            }
        }

        return moves;
    }
}
