class Solution {
    public long minEnd(int n, int x) {
        long added = n - 1;
        long bitPosition = 1;
        long result = 0;
        
        for (int i = 0; i < 63; i++) {
            if ((x & bitPosition) > 0) {
                added <<= 1;
                result += bitPosition;
            } else if ((added & bitPosition) > 0) {
                result += bitPosition;
            }
            
            bitPosition <<= 1;
        }
        return result;
    }
}
