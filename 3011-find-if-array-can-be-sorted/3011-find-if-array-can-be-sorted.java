class Solution {
    public boolean canSortArray(int[] nums) {
        int length = nums.length;

        for (int next = 0; next < length; next++) {
            int nextNumber = nums[next];
            int nextBitCount = Integer.bitCount(nextNumber);

            for (int prev = 0; prev < next; prev++) {
                int prevNumber = nums[prev];
                int prevBitCount = Integer.bitCount(prevNumber);
                
                if (prevBitCount != nextBitCount && prevNumber > nextNumber) {
                    return false;
                }
            }
        }

        return true;
    }
}
