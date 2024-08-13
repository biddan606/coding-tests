import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private int[] sortedCandidates;
    private List<List<Integer>> combinations;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        sortedCandidates = Arrays.stream(candidates)
            .sorted()
            .toArray();
        combinations = new ArrayList<>();

        generateCombinations(0, target, new ArrayList<>());

        return combinations;
    }

    private void generateCombinations(
        int sortedCandidatesStartIndex,
        int remainingSum,
        List<Integer> currentCombination) {
        if (remainingSum == 0) {
            combinations.add(new ArrayList<>(currentCombination));
            return;
        }

        for (int i = sortedCandidatesStartIndex; i < sortedCandidates.length; i++) {
            if (i > sortedCandidatesStartIndex && 
                sortedCandidates[i - 1] == sortedCandidates[i]) {
                continue;
            }
            if (sortedCandidates[i] > remainingSum) {
                break;
            }

            int currentCandidate = sortedCandidates[i];

            currentCombination.add(currentCandidate);
            generateCombinations(i + 1, remainingSum - currentCandidate, currentCombination);
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}
