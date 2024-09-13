class Solution {
    public int countConsistentStrings(String allowed, String[] words) {
        // allowed -> set에 담습니다. 허용된 문자를 빠르게 탐색하기 위해 사용합니다.
        Set<Character> allowedSet = new HashSet<>();
        for (char c : allowed.toCharArray()) {
            allowedSet.add(c);
        }

        // words를 돌며 문자열의 문자가 allowed에 모두 포함되어 있다면 count++ 시킵니다.
        int count = 0;
        for (String word : words) {
            if (match(allowedSet, word)) {
                count++;
            }
        }

        return count;
    }

    private boolean match(Set<Character> set, String str) {
        for (char c : str.toCharArray()) {
            if (!set.contains(c)) {
                return false;
            }
        }
        
        return true;
    }
}
