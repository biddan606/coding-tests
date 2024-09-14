import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution {
    public int[] solution(int[][] dices) {
        int diceCount = dices.length;
        int diceCountToSelect = diceCount / 2;

        List<int[]> combinations = generateCombinations(dices, diceCountToSelect);

        int[] bestCombination = combinations.get(0);
        int maxWinCount = 0;
        for (int[] combination : combinations) {
            int[] opponent = getOpponent(diceCount, combination);

            int[] playerSums = generateSums(dices, combination);
            int[] opponentSums = generateSums(dices, opponent);

            int playerWinCount = 0;
            for (int playerCase : playerSums) {
                playerWinCount += countWins(playerCase, opponentSums);
            }

            if (playerWinCount > maxWinCount) {
                bestCombination = combination;
                maxWinCount = playerWinCount;
            }
        }

        int[] result = new int[bestCombination.length];
        for (int i = 0; i < bestCombination.length; i++) {
            result[i] = bestCombination[i] + 1;
        }
        return result;
    }

    private List<int[]> generateCombinations(int[][] dices, int diceCountToSelect) {
        List<int[]> combinations = new ArrayList<>();
        generateCombinationsRecursive(dices, 0, new int[diceCountToSelect], 0, combinations);
        return combinations;
    }

    private void generateCombinationsRecursive(int[][] dices, int startIndex, int[] currentCombination, int depth, List<int[]> combinations) {
        if (currentCombination.length == depth) {
            combinations.add(currentCombination.clone());
            return;
        }

        int nextDepth = depth + 1;
        for (int i = startIndex; i < dices.length; i++) {
            currentCombination[depth] = i;
            generateCombinationsRecursive(dices, i + 1, currentCombination, nextDepth, combinations);
        }
    }

    private int[] getOpponent(int diceCount, int[] combination) {
        boolean[] isPlayerDice = new boolean[diceCount];
        for (int index : combination) {
            isPlayerDice[index] = true;
        }

        int[] opponent = new int[combination.length];
        int opponentIndex = 0;
        for (int i = 0; i < diceCount; i++) {
            if (!isPlayerDice[i]) {
                opponent[opponentIndex++] = i;
            }
        }
        return opponent;
    }

    private int[] generateSums(int[][] dices, int[] indexes) {
        List<Integer> sums = new ArrayList<>();
        generateSumsRecursive(dices, indexes, 0, 0, sums);

        sums.sort(Comparator.naturalOrder());
        return sums.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private void generateSumsRecursive(int[][] dices, int[] indexes, int depth, int sum, List<Integer> cases) {
        if (indexes.length == depth) {
            cases.add(sum);
            return;
        }

        int nextDepth = depth + 1;
        for (int value : dices[indexes[depth]]) {
            generateSumsRecursive(dices, indexes, nextDepth, sum + value, cases);
        }
    }

    private int countWins(int number, int[] opponentSums) {
        int left = 0;
        int right = opponentSums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (opponentSums[mid] < number) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[][] dices = {{1, 2, 3, 4, 5, 6}, {2, 2, 4, 4, 6, 6}};
        Solution solution = new Solution();

        int[] result = solution.solution(dices);
        System.out.println(Arrays.toString(result));
    }
}
