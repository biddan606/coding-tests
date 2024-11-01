class Solution {
    int maxUniqueSubstrings;

    public int maxUniqueSplit(String s) {
        maxUniqueSubstrings = 0;
        calculateMaxUniqueSubstirngs(s, 0, new HashMap<>());

        return maxUniqueSubstrings;
    }

    private void calculateMaxUniqueSubstirngs(String str, int startIndex, Map<String, Integer> substringCounts) {
        if (startIndex >= str.length()) {
            maxUniqueSubstrings = Math.max(maxUniqueSubstrings, substringCounts.size());
            return;
        }

        for (int length = 1; length + startIndex <= str.length(); length++) {
            String currentSubstring = str.substring(startIndex, startIndex + length);
            
            substringCounts.put(currentSubstring, substringCounts.getOrDefault(currentSubstring, 0) + 1);
            calculateMaxUniqueSubstirngs(str, startIndex + length, substringCounts);

            substringCounts.put(currentSubstring, substringCounts.get(currentSubstring) - 1);
            if (substringCounts.get(currentSubstring) == 0) {
                substringCounts.remove(currentSubstring);
            }
        }
    }
}
