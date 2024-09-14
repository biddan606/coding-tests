import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution {
    public int[] solution(int[][] dices) {
        int diceCount = dices.length;
        int diceCountToSelect = diceCount / 2;

        List<int[]> combinations = combine(dices, diceCountToSelect);

        int[] bestCombination = combinations.get(0);
        int maxWinCount = 0;
        for (int[] combination : combinations) {
            int[] opponent = generateOpponent(diceCount, combination);

            int[] playerCases = generateCases(dices, combination);
            int[] opponentCases = generateCases(dices, opponent);

            int playerWinCount = 0;
            for (int playerCase : playerCases) {
                playerWinCount += calculateWinCount(playerCase, opponentCases);
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

    private List<int[]> combine(int[][] dices, int diceCountToSelect) {
        List<int[]> combinations = new ArrayList<>();
        combineRecursive(dices, 0, new int[diceCountToSelect], 0, combinations);
        return combinations;
    }

    private void combineRecursive(int[][] dices, int startIndex, int[] combination, int depth, List<int[]> combinations) {
        if (combination.length == depth) {
            combinations.add(combination.clone());
            return;
        }

        int nextDepth = depth + 1;
        for (int i = startIndex; i < dices.length; i++) {
            combination[depth] = i;
            combineRecursive(dices, i + 1, combination, nextDepth, combinations);
        }
    }

    private int[] generateOpponent(int diceCount, int[] combination) {
        int[] opponent = new int[combination.length];
        int combinationIndex = 0;
        int opponentIndex = 0;
        for (int i = 0; i < diceCount; i++) {
            if (combinationIndex < combination.length && combination[combinationIndex] == i) {
                combinationIndex++;
            } else {
                opponent[opponentIndex] = i;
                opponentIndex++;
            }
        }
        return opponent;
    }

    private int[] generateCases(int[][] dices, int[] indexes) {
        List<Integer> cases = new ArrayList<>();
        generateCasesRecursive(dices, indexes, 0, 0, cases);

        cases.sort(Comparator.naturalOrder());
        return cases.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private void generateCasesRecursive(int[][] dices, int[] indexes, int depth, int sum, List<Integer> cases) {
        if (indexes.length == depth) {
            cases.add(sum);
            return;
        }

        int nextDepth = depth + 1;
        for (int value : dices[indexes[depth]]) {
            generateCasesRecursive(dices, indexes, nextDepth, sum + value, cases);
        }
    }

    private int calculateWinCount(int number, int[] opponentCases) {
        int left = 0;
        int right = opponentCases.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (opponentCases[mid] < number) {
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
