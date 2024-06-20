class Solution {
    public boolean judgeSquareSum(int c) {
        double left = 0;
        double right = Math.floor(Math.sqrt(c));
        while (left <= right) {
            double sum = left * left + right * right;
            if (sum == c) {
                return true;
            } else if (sum > c) {
                right--;
            } else { // sum < c
                left++;
            }
        }

        return false;
    }
}
