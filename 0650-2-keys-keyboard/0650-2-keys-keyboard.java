class Solution {

    public int minSteps(int n) {
        if (n == 1) {
            return 0;
        }

        int steps = 0;

        int current = n;
        while (n > 1) {
            if (n % 2 == 0) {
                steps++;
            }

            n = (n + 1) / 2;
            steps++;
        }

        return steps;
    }
}
