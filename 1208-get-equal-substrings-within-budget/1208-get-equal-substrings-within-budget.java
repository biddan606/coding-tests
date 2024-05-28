import java.util.*;

class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        int maxLength = 0;
        int accumulatedCost = 0;
        int startIndex = 0;

        for (int i = 0; i < s.length(); i++) {
            accumulatedCost += Math.abs(s.charAt(i) - t.charAt(i));

            while (startIndex <= i && accumulatedCost > maxCost) {
                accumulatedCost -= Math.abs(s.charAt(startIndex) - t.charAt(startIndex));
                startIndex++;
            }

            maxLength = Math.max(maxLength, i - startIndex + 1);
        }

        return maxLength;
    }
}
