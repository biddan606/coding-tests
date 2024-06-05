class Solution {
    public int appendCharacters(String s, String t) {
        int notMachingIndex = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == t.charAt(notMachingIndex)) {
                notMachingIndex++;
            }
            if (notMachingIndex == t.length()) {
                break;
            }
        }

        return t.length() - notMachingIndex;
    }
}
