class Solution {
    public int solution(int[] sticker) {
        int size = sticker.length;
        if (size == 1) {
            return sticker[0];
        }

        int[] rightDirectionSum = new int[size];
        rightDirectionSum[0] = sticker[0];
        rightDirectionSum[1] = Math.max(sticker[0], sticker[1]);

        for (int i = 2; i < size - 1; i++) {
            rightDirectionSum[i] = Math.max(rightDirectionSum[i - 2] + sticker[i], rightDirectionSum[i - 1]);
        }

        int[] leftDirectionSum = new int[size];
        leftDirectionSum[size - 1] = sticker[size - 1];
        leftDirectionSum[size - 2] = Math.max(sticker[size - 1], sticker[size - 2]);

        for (int i = size - 3; i >= 1; i--) {
            leftDirectionSum[i] = Math.max(leftDirectionSum[i + 2] + sticker[i], leftDirectionSum[i + 1]);
        }

        int maxSum = 0;
        for (int i = 0; i < size; i++) {
            maxSum = Math.max(maxSum, leftDirectionSum[i]);
            maxSum = Math.max(maxSum, rightDirectionSum[i]);
        }

        return maxSum;
    }
}
