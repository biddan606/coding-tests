import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public int solution(String[][] relation) {
        List<List<Integer>> allKeys = createAllKeys(relation);

        // 최소성을 보장할 수 있게 작은 사이즈부터 추가
        allKeys.sort(Comparator.comparingInt(List::size));

        List<Set<Integer>> candidateKeys = new ArrayList<>();

        for (List<Integer> key : allKeys) {
            if (isSuperKey(relation, key) && isMinimalKey(candidateKeys, new HashSet<>(key))) {
                candidateKeys.add(new HashSet<>(key));
            }
        }

        return candidateKeys.size();
    }

    private List<List<Integer>> createAllKeys(String[][] relation) {
        List<List<Integer>> allKeys = new ArrayList<>();

        backtrack(allKeys, new ArrayList<>(), 0, relation[0].length);
        return allKeys;
    }

    private void backtrack(List<List<Integer>> allKeys, List<Integer> currentKey, int startIndex, int excludedEnd) {
        if (!currentKey.isEmpty()) {
            allKeys.add(new ArrayList<>(currentKey));
        }

        for (int i = startIndex; i < excludedEnd; i++) {
            currentKey.add(i);
            backtrack(allKeys, currentKey, i + 1, excludedEnd);
            currentKey.remove(currentKey.size() - 1);
        }
    }

    private boolean isSuperKey(String[][] relation, List<Integer> key) {
        Set<String> uniqueKeys = new HashSet<>();

        for (String[] record : relation) {
            StringBuilder keyBuilder = new StringBuilder();
            for (int index : key) {
                keyBuilder.append(record[index]).append(",");
            }

            if (!uniqueKeys.add(keyBuilder.toString())) {
                return false;
            }
        }
        return true;
    }

    private boolean isMinimalKey(List<Set<Integer>> candidateKeys, Set<Integer> key) {
        return candidateKeys.stream()
                .noneMatch(key::containsAll);
    }
}
