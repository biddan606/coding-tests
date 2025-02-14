class Solution {
    // kmp
    public String removeOccurrences(String s, String part) {
        int sLength = s.length();
        int partLength = part.length();
  
        int[] lps = new int[partLength];
        int length = 0;

        for (int i = 1; i < partLength; i++) {
            char currentLetter = part.charAt(i);

            while (length > 0 && part.charAt(length) != currentLetter) {
                length = lps[length - 1];
            }
            if (part.charAt(length) == currentLetter) {
                length++;
            }

            lps[i] = length;
        }

        char[] result = new char[sLength];
        int resultIndex = 0;
        Deque<Integer> matchedLengths = new ArrayDeque<>();

        for (int i = 0; i < sLength; i++) {
            char currentLetter = s.charAt(i);
            result[resultIndex++] = currentLetter;

            int lastMatchedLength = 0;
            if (!matchedLengths.isEmpty()) {
                lastMatchedLength = matchedLengths.peek();
            }

            while (lastMatchedLength > 0 && part.charAt(lastMatchedLength) != currentLetter) {
                lastMatchedLength = lps[lastMatchedLength - 1];
            }
            if (part.charAt(lastMatchedLength) == currentLetter) {
                lastMatchedLength++;
            }

            matchedLengths.push(lastMatchedLength);

            if (lastMatchedLength == partLength) {
                resultIndex -= partLength;
                for (int c = 0; c < lastMatchedLength; c++) {
                    matchedLengths.pop();
                }
            }
        }

        return new String(result, 0, resultIndex);
    }
}
