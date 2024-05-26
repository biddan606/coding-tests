class Solution {
    List<String> result;

    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        result = new ArrayList<>();
        backtracking(s, 0, new ArrayList<>(), words);

        return result;
    }

    private void backtracking(String str, int beginIndex, List<String> substrings, Set<String> words) {
        if (str.length() == beginIndex) {
            result.add(
              substrings.stream()
                .collect(Collectors.joining(" "))  
            );
            return;
        }

        for (int endIndex = beginIndex + 1; endIndex <= str.length(); endIndex++) {
            String word = str.substring(beginIndex, endIndex);
            if (!words.contains(word)) {
                continue;
            }

            substrings.add(word);
            backtracking(str, endIndex, substrings, words);
            substrings.remove(substrings.size() - 1);
        }
    }
}
