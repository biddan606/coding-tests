import java.util.Arrays;

class Solution {
    private static final int BOARD_SIZE = 11;
    private static final int MAX_SCORE = 10;

    private int[] bestStrategy = {-1};
    private int maxScoreDifference = 1;

    public int[] solution(int totalArrows, int[] apeachScores) {
        findBestStrategy(new int[BOARD_SIZE], 0, totalArrows, apeachScores);
        return bestStrategy;
    }

    private void findBestStrategy(int[] ryanScores, int index, int remainingArrows, int[] apeachScores) {
        if (index >= BOARD_SIZE) {
            ryanScores[BOARD_SIZE - 1] += remainingArrows;
            updateBestStrategyIfBetter(ryanScores, apeachScores);
            ryanScores[BOARD_SIZE - 1] = 0;
            return;
        }

        if (remainingArrows > apeachScores[index]) {
            ryanScores[index] = apeachScores[index] + 1;
            findBestStrategy(ryanScores, index + 1, remainingArrows - ryanScores[index], apeachScores);
            ryanScores[index] = 0;
        }

        findBestStrategy(ryanScores, index + 1, remainingArrows, apeachScores);
    }

    private void updateBestStrategyIfBetter(int[] ryanScores, int[] apeachScores) {
        int scoreDifference = calculateScoreDifference(ryanScores, apeachScores);
        
        if (scoreDifference > maxScoreDifference || 
            (scoreDifference == maxScoreDifference && hasMoreLowScores(ryanScores))) {
            bestStrategy = Arrays.copyOf(ryanScores, BOARD_SIZE);
            maxScoreDifference = scoreDifference;
        }
    }

    private int calculateScoreDifference(int[] ryanScores, int[] apeachScores) {
        int difference = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (ryanScores[i] == 0 && apeachScores[i] == 0) {
                continue;                
            }
            
            int score = MAX_SCORE - i;
            difference += (ryanScores[i] > apeachScores[i]) ? score : -score;
        }
        return difference;
    }

    private boolean hasMoreLowScores(int[] ryanScores) {
        for (int i = BOARD_SIZE - 1; i >= 0; i--) {
            if (bestStrategy[i] != ryanScores[i]) {
                return ryanScores[i] > bestStrategy[i];
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] apeachScores = {0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1};
        int[] result = new Solution().solution(9, apeachScores);
        System.out.println(Arrays.toString(result));
    }
}
