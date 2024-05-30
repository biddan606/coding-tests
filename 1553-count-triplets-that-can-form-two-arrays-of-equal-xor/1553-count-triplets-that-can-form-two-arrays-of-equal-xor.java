class Solution {
    public int countTriplets(int[] arr) {
        int size = arr.length;
        int count = 0;

        for (int i = 0; i < size; i++) {
            int xor = arr[i];

            for (int k = i + 1; k < size; k++) {
                xor ^= arr[k];

                if (xor == 0) {
                    count += k - i;
                }
            }
        }

        return count;
    }
}
