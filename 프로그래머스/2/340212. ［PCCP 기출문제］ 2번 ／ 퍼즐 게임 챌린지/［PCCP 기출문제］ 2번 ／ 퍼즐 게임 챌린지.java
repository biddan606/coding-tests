class Solution {
    // diffs: 퍼즐의 난이도
    // times: 퍼즐의 소요 시간
    // limit: 제한 시간
    public int solution(int[] diffs, int[] times, long limit) {
        // 1 ~ 100,000 시간 안에 문제를 푼다.
        // 푼 시간 <= limit 인 가장 작은 수를 찾는다.
        int size = diffs.length;
        int left = 1;
        int right = 100_000;
        int minLevel = 100_000;

        while (left <= right) {
            int currentLevel = (left + right) / 2;
            int prevTime = 1;
            long totalTime = 0;

            for (int i = 0; i < size; i++) {
                int remainingLevel = diffs[i] - currentLevel;
                int currentTime = times[i];

                totalTime += currentTime;
                if (remainingLevel > 0) {
                    totalTime += (prevTime + currentTime) * remainingLevel;
                }

                prevTime = currentTime;
            }

            if (totalTime <= limit) {
                right = currentLevel - 1;
                minLevel = Math.min(minLevel, currentLevel);
            } else {
                left = currentLevel + 1;
            }
        }

        return minLevel;
    }

    public static void main(String[] args) {
        int[] diffs = {1, 5, 3};
        int[] times = {2, 4, 7};
        int limit = 30;

        int result = new Solution().solution(diffs, times, limit);
        System.out.println(result);
    }
}
