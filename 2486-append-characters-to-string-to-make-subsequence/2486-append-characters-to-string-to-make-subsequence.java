class Solution {
    public int appendCharacters(String s, String t) {
        int notMachingIndex = 0;

        for (int i = 0; i < s.length(); i++) {
            if (notMachingIndex == t.length()) {
                break;
            }
            if (s.charAt(i) == t.charAt(notMachingIndex)) {
                notMachingIndex++;
            }
        }

        return t.length() - notMachingIndex;
    }
}
