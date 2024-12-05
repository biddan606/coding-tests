class Solution {
    public boolean canMakeSubsequence(String str1, String str2) {
        int str2Index = 0;

        for (int str1Index = 0; str1Index < str1.length() && str2Index < str2.length(); str1Index++) {
            char str1Char = str1.charAt(str1Index);
            char str2Char = str2.charAt(str2Index);

            if (str1Char == str2Char || nextChar(str1Char) == str2Char) {
                str2Index++;
            }
        }

        if (str2Index == str2.length()) {
            return true;
        }
        return false;
    }

    private static char nextChar(char ch) {
        int nextOrder = ch - 'a' + 1;
        return (char)('a' + (nextOrder % ('z' - 'a' + 1)));
    }
}
