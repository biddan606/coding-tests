import java.util.Arrays;

class Solution {

    public int[] solution(String str) {
        int loopCount = 0;
        int removedZeroCount = 0;

        while (str.length() != 1) {
            String newStr = removeZero(str);
            removedZeroCount += str.length() - newStr.length();

            str = Integer.toString(newStr.length(), 2);

            loopCount++;
        }

        return new int[]{loopCount, removedZeroCount};
    }

    private String removeZero(String str) {
        StringBuilder builder = new StringBuilder();

        for (char c : str.toCharArray()) {
            if (c == '0') {
                continue;
            }

            builder.append(c);
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(Arrays.toString(solution.solution("110010101001")));
    }
}
