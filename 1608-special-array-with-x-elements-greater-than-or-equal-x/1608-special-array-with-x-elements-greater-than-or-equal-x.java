class Solution {
    public int specialArray(int[] nums) {
        for (int x = 1; x < 1000; x++) {
            int greaterAndEqualCount = 0;

            for (int n : nums) {
                if (n >= x) {
                    greaterAndEqualCount++;
                }
            }

            if (x == greaterAndEqualCount) {
                return x;
            }
        }

        return -1;
    }
}
