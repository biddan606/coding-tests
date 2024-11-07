class Solution {
    public int largestCombination(int[] candidates) {
        int[] bitDigits = new int[31];

        for (int candidate : candidates) {
            for (int i = 0; i < bitDigits.length; i++) {
                bitDigits[i] += 1 & candidate;
                candidate >>= 1;
            }
        }

        return Arrays.stream(bitDigits)
                .max().getAsInt();
    }
}
