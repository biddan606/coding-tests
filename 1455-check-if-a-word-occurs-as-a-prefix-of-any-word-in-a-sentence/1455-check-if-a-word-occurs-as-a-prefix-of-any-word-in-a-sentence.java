class Solution {
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] words = sentence.split(" ");

        for (int i = 0; i < words.length; i++) {
            if (isPrefix(words[i], searchWord)) {
                return i + 1;
            }
        }
        return -1;
    }

    private static boolean isPrefix(String target, String source) {
        for (int i = 0; i < source.length(); i++) {
            if (i >= target.length() || source.charAt(i) != target.charAt(i)) {
                    return false;
            }
        }
        return true;
    }
}
