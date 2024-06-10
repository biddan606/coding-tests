class Solution {
    public int heightChecker(int[] heights) {
        int moveCount = 0;
        for (int i = 0; i < heights.length; i++) {
            int expectedMinIndex = 0;
            int equalValueCount = 0;
            int currentHeight = heights[i];
            
            // 앞 인덱스는 자신보다 작아야 한다.
            for (int frontIndex = 0; frontIndex < i; frontIndex++) {
                if (heights[frontIndex] < currentHeight) {
                    expectedMinIndex++;
                } else if (heights[frontIndex] == currentHeight){
                    equalValueCount++;
                }
            }

            // 뒤 인덱스는 자신보다 커야 한다.
            for (int backIndex = i + 1; backIndex < heights.length; backIndex++) {
                if (heights[backIndex] < currentHeight) {
                    expectedMinIndex++;
                } else if (heights[backIndex] == currentHeight){
                    equalValueCount++;
                }
            }

            int expectedMaxIndex = expectedMinIndex + equalValueCount;
            if (expectedMinIndex > i || expectedMaxIndex < i) {
                moveCount++;
            }
        }

        return moveCount;
    }
}
