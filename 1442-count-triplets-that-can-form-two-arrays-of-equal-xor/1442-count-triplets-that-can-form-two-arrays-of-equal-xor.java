class Solution {
    public int countTriplets(int[] arr) {
        int range = arr.length;
        int count = 0;

        for (int i = 0; i < range; i++) {
            for (int j = i + 1; j < range; j++) {
                int a = xor(arr, i, j - 1);

                for (int k = j; k < range; k++) {
                    int b = xor(arr, j, k);

                    if (a == b) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private int xor(int[] array, int startIndex, int endIndexInclude) {
        int result = 0;

        for (int i = startIndex; i <= endIndexInclude; i++) {
            result ^= array[i];
        }

        return result;
    }
}
