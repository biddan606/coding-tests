class Solution {
    public boolean threeConsecutiveOdds(int[] arr) {
        int oddConsecutiveCount = 0;

        for (int num : arr) {
            if (num % 2 == 1) {
                oddConsecutiveCount++;
            } else {
                oddConsecutiveCount = 0;
            }

            if (oddConsecutiveCount == 3) {
                return true;
            }
        }
        return false;
    }
}
