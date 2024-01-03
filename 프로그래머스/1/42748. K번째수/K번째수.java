import java.util.Arrays;

class Solution {

    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for (int i = 0; i < answer.length; i++) {
            int beginIndex = commands[i][0];
            int endIndex = commands[i][1];
            int[] subArray = getSubArray(array, beginIndex, endIndex);

            Arrays.sort(subArray);

            int sequence = commands[i][2];
            answer[i] = getElement(subArray, sequence);
        }

        return answer;
    }

    private int[] getSubArray(int[] array, int beginIndex, int endIndex) {
        return Arrays.copyOfRange(array, beginIndex - 1, endIndex);
    }

    private int getElement(int[] array, int sequence) {
        return array[sequence - 1];
    }
}
