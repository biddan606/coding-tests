class Solution {
    public int tribonacci(int n) {
        if (n <= 1) {
            return n;
        } else if (n == 2) {
            return 1;
        }

        int[] memory = new int[n + 1];
        memory[0] = 0;
        memory[1] = 1;
        memory[2] = 1;

        for (int i = 3; i <= n; i++) {
            memory[i] = memory[i - 3] + memory[i - 2] + memory[i - 1];
        }

        return memory[n];
    }
}