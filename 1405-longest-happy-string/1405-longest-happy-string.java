import java.util.HashMap;
import java.util.Map;

class Solution {
    public String longestDiverseString(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();

        int[] counts = {a, b, c};
        Map<Integer, Character> chars = new HashMap<>();
        chars.put(0, 'a');
        chars.put(1, 'b');
        chars.put(2, 'c');
        boolean satisfied = true;
        int impossibleIndex = -1;
        while (satisfied) {
            int index = findIndexOfMaxValue(counts, impossibleIndex);
            if (index == -1) {
                satisfied = false;
                continue;
            }
            Character charToAdd = chars.get(index);

            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == charToAdd) {
                impossibleIndex = index;
            } else {
                impossibleIndex = -1;
            }

            counts[index]--;
            sb.append(charToAdd);
        }

        return sb.toString();
    }

    private int findIndexOfMaxValue(int[] counts, int impossibleIndex) {
        int index = -1;
        int max = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > max && i != impossibleIndex) {
                index = i;
                max = counts[i];
            }
        }

        return index;
    }

    public static void main(String[] args) {
        int a = 1;
        int b = 1;
        int c = 7;
        String result = new Solution().longestDiverseString(a, b, c);

        System.out.println(result);
    }
}
