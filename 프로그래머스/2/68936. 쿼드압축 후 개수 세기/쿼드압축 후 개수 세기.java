import java.util.Arrays;

class Solution {
    public int[] solution(int[][] arr) {
        int[] answer = {0, 0};
        compress(arr, 0, 0, arr.length, answer);
        return answer;
    }

    private boolean compress(int[][] array, int offsetRow, int offsetCol, int length, int[] answer) {
        if (length == 1) {
            answer[array[offsetRow][offsetCol]]++;
            return true;
        }

        int nextLength = length / 2;
        boolean isUniform1 = compress(array, offsetRow, offsetCol, nextLength, answer);
        boolean isUniform2 = compress(array, offsetRow, offsetCol + nextLength, nextLength, answer);
        boolean isUniform3 = compress(array, offsetRow + nextLength, offsetCol, nextLength, answer);
        boolean isUniform4 = compress(array, offsetRow + nextLength, offsetCol + nextLength, nextLength, answer);

        if (isUniform1 && isUniform2 && isUniform3 && isUniform4 &&
                array[offsetRow][offsetCol] == array[offsetRow][offsetCol + nextLength] &&
                array[offsetRow][offsetCol] == array[offsetRow + nextLength][offsetCol] &&
                array[offsetRow][offsetCol] == array[offsetRow + nextLength][offsetCol + nextLength]) {

            // 모든 사분면이 동일한 경우
            // 이미 증가된 카운트를 제거
            answer[array[offsetRow][offsetCol]] -= 3;
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] array = {{0, 0}, {0, 0}};
        int[] answer = solution.solution(array);
        System.out.println(Arrays.toString(answer));
    }
}
