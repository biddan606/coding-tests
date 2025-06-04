class Solution {
    public String answerString(String word, int numFriends) {
        if (numFriends == 1) {
            return word;
        }

        char largestAlphabet = 'a';
        
        for (char ch : word.toCharArray()) {
            if (largestAlphabet < ch) {
                largestAlphabet = ch;
            }
        }

        int size = word.length();
        int longestLength = size - numFriends + 1;
        String result = "";

        for (int i = 0; i < size; i++) {
            char currentChar = word.charAt(i);
            if (currentChar != largestAlphabet) {
                continue;
            }

            int endIndex = Math.min(size, i + longestLength);
            String current = word.substring(i, endIndex);
            
            if (result.compareTo(current) < 0) {
                result = current;
            }
        }

        return result;
    }
}
