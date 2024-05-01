class Solution {
    public String reversePrefix(String word, char ch) {
        int firstOccurrenceIndex = word.indexOf(ch);
        if (firstOccurrenceIndex == -1) {
            return word;
        }
        
        String reversed = new StringBuilder(word.substring(0, firstOccurrenceIndex + 1)).reverse().toString();
        return reversed + word.substring(firstOccurrenceIndex + 1);
    }
}