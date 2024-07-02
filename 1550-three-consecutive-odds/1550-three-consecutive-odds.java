class Solution {
    public boolean threeConsecutiveOdds(int[] arr) {
        int consecutive = 0;
        
        for (int number : arr) {
            if (number % 2 == 1) {
                consecutive++;
                if (consecutive >= 3) {
                    return true;
                }
            } else {
                consecutive = 0;
            }
        }

        return false;
    }
}
