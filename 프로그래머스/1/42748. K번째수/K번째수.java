import java.util.Arrays;
import java.util.Collections;

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
        int[] subArray = new int[endIndex - beginIndex + 1];
        System.arraycopy(array, beginIndex - 1, subArray, 0, subArray.length);

        return subArray;
    }

    private int getElement(int[] array, int sequence) {
        return array[sequence - 1];
    }
}
