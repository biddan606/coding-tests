class Solution {
    public int maxCount(int[] banned, int n, int maxSum) {
        Set<Integer> bannedNumbers = Arrays.stream(banned)
            .boxed()
            .collect(Collectors.toSet());

        int count = 0;
        int sum = 0;

        for (int number = 1; number <= n; number++) {
            if (number > maxSum) {
                break;
            }
            
            if (bannedNumbers.contains(number)) {
                continue;
            }

            count++;
            maxSum -= number;
        }
        return count;
    }
}
