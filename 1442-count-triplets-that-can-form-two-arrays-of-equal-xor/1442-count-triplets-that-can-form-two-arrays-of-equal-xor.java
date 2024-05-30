class Solution {
    public int countTriplets(int[] arr) {
        int range = arr.length;
        int[][] prefixXor = new int[range][range];

        for (int startIndex = 0; startIndex < range; startIndex++) {
            prefixXor[startIndex][startIndex] = arr[startIndex];

            for (int endIndexInclude = startIndex + 1; endIndexInclude < range; endIndexInclude++) {
                prefixXor[startIndex][endIndexInclude] = prefixXor[startIndex][endIndexInclude - 1] ^ arr[endIndexInclude];
            }
        }

        int count = 0;

        for (int i = 0; i < range; i++) {
            for (int j = i + 1; j < range; j++) {
                for (int k = j; k < range; k++) {
                    if (prefixXor[i][j - 1] == prefixXor[j][k]) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
