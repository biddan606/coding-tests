import java.util.Arrays;

class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] reversedArr2 = reverseArray(arr2);

        int[][] result = new int[arr1.length][arr2[0].length];
        for (int arr1Row = 0; arr1Row < arr1.length; arr1Row++) {
            for (int reversedArr2Row = 0; reversedArr2Row < reversedArr2.length; reversedArr2Row++) {
                int sum = 0;

                for (int reversedArr2Col = 0; reversedArr2Col < reversedArr2[0].length; reversedArr2Col++) {
                    sum += arr1[arr1Row][reversedArr2Col] * reversedArr2[reversedArr2Row][reversedArr2Col];
                }

                result[arr1Row][reversedArr2Row] = sum;
            }
        }

        return result;
    }

    private int[][] reverseArray(int[][] original) {
        int[][] reversed = new int[original[0].length][original.length];

        for (int originalRow = 0; originalRow < original.length; originalRow++) {
            for (int originalCol = 0; originalCol < original[0].length; originalCol++) {
                reversed[originalCol][originalRow] = original[originalRow][originalCol];
            }
        }
        return reversed;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] arr1 = new int[][]{
                {2, 3, 2},
                {4, 2, 4},
                {3, 1, 4}
        };
        int[][] arr2 = new int[][]{
                {5, 4, 3},
                {2, 4, 1},
                {3, 1, 1}
        };

        int[][] result = solution.solution(arr1, arr2);

        for (int[] ints : result) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
