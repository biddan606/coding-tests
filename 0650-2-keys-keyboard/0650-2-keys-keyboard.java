import java.util.Arrays;

class Solution {

    public int minSteps(int n) {
        if (n == 1) {
            return 0;
        }

        int[] minSteps = new int[n + 1];
        Arrays.fill(minSteps, Integer.MAX_VALUE);
        minSteps[1] = 0;

        for (int copiedLength = 1; copiedLength <= n / 2; copiedLength++) {
            int nextSteps = minSteps[copiedLength] + 2;
            for (int i = copiedLength * 2; i <= n; i += copiedLength) {
                minSteps[i] = Math.min(minSteps[i], nextSteps);
                nextSteps++;
            }
        }

        return minSteps[n];
    }
}
