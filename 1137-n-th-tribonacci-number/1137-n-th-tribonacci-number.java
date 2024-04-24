class Solution {
    public int tribonacci(int n) {
        if (n < 2) {
            return n;
        }

        int[] memory = {0, 1, 1};

        for (int i = 3; i <= n; i++) {
            int next = memory[0] + memory[1] + memory[2];
            memory[0] = memory[1];
            memory[1] = memory[2];
            memory[2] = next;
        }

        return memory[2];
    }
}