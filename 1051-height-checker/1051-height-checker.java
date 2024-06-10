class Solution {
    public int heightChecker(int[] heights) {
        int[] lessThanOrEqual = new int[101];
        for (int h : heights) {
            lessThanOrEqual[h]++;
        }
        for (int i = 1; i < lessThanOrEqual.length; i++) {
            lessThanOrEqual[i] += lessThanOrEqual[i - 1];
        }

        int moveCount = 0;
        for (int i = 0; i < heights.length; i++) {
            int h = heights[i];

            int expectedMinIndex = lessThanOrEqual[h - 1];
            int expectedMaxIndex = lessThanOrEqual[h] - 1;

            if (expectedMinIndex > i || expectedMaxIndex < i) {
                moveCount++;
            }
        }

        return moveCount;
    }
}
