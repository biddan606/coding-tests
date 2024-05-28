import java.util.*;

class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        Queue<Integer> costQueue = new ArrayDeque<>();
        int accumulatedCostsInQueue = 0;
        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {
            int currentCost = Math.abs(s.charAt(i) - t.charAt(i));

            while (!costQueue.isEmpty() && accumulatedCostsInQueue + currentCost > maxCost) {
                accumulatedCostsInQueue -= costQueue.poll();
            }

            if (accumulatedCostsInQueue + currentCost <= maxCost) {
                accumulatedCostsInQueue += currentCost;
                costQueue.offer(currentCost);
            }

            maxLength = Math.max(maxLength, costQueue.size());
        }

        return maxLength;
    }
}
