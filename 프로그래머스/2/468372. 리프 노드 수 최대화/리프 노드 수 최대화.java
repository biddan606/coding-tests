class Solution {
    public int solution(int dist_limit, int split_limit) {
        long answer = 1L;

        long pow2 = 1L;
        for (int twoCount = 0; pow2 <= split_limit; twoCount++) {
            long product = pow2;

            for (int threeCount = 0; product <= split_limit; threeCount++) {
                answer = Math.max(answer, calculateLeafCount(dist_limit, twoCount, threeCount));

                if (product > split_limit / 3L) {
                    break;
                }
                product *= 3L;
            }

            if (pow2 > split_limit / 2L) {
                break;
            }
            pow2 *= 2L;
        }

        return (int) answer;
    }

    private long calculateLeafCount(int distLimit, int twoCount, int threeCount) {
        long currentLeafCount = 1L;
        long usedDistCount = 0L;

        for (int i = 0; i < twoCount; i++) {
            long remainDistCount = distLimit - usedDistCount;

            if (remainDistCount <= 0) {
                return currentLeafCount;
            }

            if (remainDistCount < currentLeafCount) {
                return currentLeafCount + remainDistCount;
            }

            usedDistCount += currentLeafCount;
            currentLeafCount *= 2L;
        }

        for (int i = 0; i < threeCount; i++) {
            long remainDistCount = distLimit - usedDistCount;

            if (remainDistCount <= 0) {
                return currentLeafCount;
            }

            if (remainDistCount < currentLeafCount) {
                return currentLeafCount + remainDistCount * 2L;
            }

            usedDistCount += currentLeafCount;
            currentLeafCount *= 3L;
        }

        return currentLeafCount;
    }
}