class Solution {
    private List<String> result;
    private Map<Integer, List<String>> memoization;
    private Set<String> words;

    public List<String> wordBreak(String s, List<String> wordDict) {
        words = new HashSet<>(wordDict);
        result = new ArrayList<>();
        memoization = new HashMap<>();

        backtracking(s, 0);
        return memoization.get(0);
    }

    private void backtracking(String str, int beginIndex) {
        if (memoization.containsKey(beginIndex)) {
            return;
        }

        memoization.put(beginIndex, new ArrayList<>());
        if (str.length() == beginIndex) {
            memoization.get(beginIndex).add("");
            return;
        }

        for (int endIndex = beginIndex + 1; endIndex <= str.length(); endIndex++) {
            String word = str.substring(beginIndex, endIndex);
            if (!words.contains(word)) {
                continue;
            }

            backtracking(str, endIndex);
            for (String suffix : memoization.getOrDefault(endIndex, Collections.emptyList())) {
                memoization.get(beginIndex).add(word + (suffix.equals("") ? "" : " " + suffix));
            }
        }
    }
}
