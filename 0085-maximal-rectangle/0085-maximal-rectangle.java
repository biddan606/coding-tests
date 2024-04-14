import java.util.*;

class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rowLength = matrix.length;
        int columnLength = matrix[0].length;
        int[] heightsByColumn = new int[columnLength];
        int maxRectangleSize = 0;

        /*
        1. row를 증가시켜가며, 히스토그램을 축적합니다.
        2. 축적된 히스토그램을 이용해서 현재 최대 직사각형의 크기를 계산합니다.
        3. 현재 최대와 지금까지의 최대를 비교하여 수정합니다.

        column이 이어져있으면 히스토그램 값이 증가하고, 끊어지면 0으로 초기화됩니다.
        */
        for (int row = 0; row < rowLength; row++) {
            for (int col = 0; col < columnLength; col++) {
                if (matrix[row][col] == '1') {
                    heightsByColumn[col]++;
                } else { // matrix[row][col] == '0'
                    heightsByColumn[col] = 0;
                }
            }

            int currentMaxRectangleSize = calculateLargestAreaHistogram(heightsByColumn);

            maxRectangleSize = Math.max(maxRectangleSize, currentMaxRectangleSize);
        }

        return maxRectangleSize;
    }

    private int calculateLargestAreaHistogram(int[] histogram) {
        int[] leftSmall = new int[histogram.length];
        int[] rightSmall = new int[histogram.length];
        Deque<Integer> temp = new ArrayDeque<>();
        
        // 현재 column의 값보다 작은 값을 가진 왼쪽의 column을 얻습니다. (-> 방향 진행)
        for (int i = 0; i < histogram.length; i++) {
            while (!temp.isEmpty() && histogram[temp.getFirst()] >= histogram[i]) {
                temp.removeFirst();
            }

            leftSmall[i] = temp.isEmpty() ? 0 : temp.getFirst() + 1;
            
            temp.addFirst(i);
        }
        
        // 임시 스택을 비웁니다.
        temp = new ArrayDeque<>();

        // 현재 column의 값보다 작은 값을 가진 오른쪽의 column을 얻습니다. (<- 방향 진행)
        for (int i = histogram.length - 1; i >= 0; i--) {
            while (!temp.isEmpty() && histogram[temp.getFirst()] >= histogram[i]) {
                temp.removeFirst();
            }

            rightSmall[i] = temp.isEmpty() ? histogram.length - 1 : temp.getFirst() - 1;
            
            temp.addFirst(i);
        }

        // 최대 직사각형의 값을 얻습니다.
        int maxArea = 0;
        for (int i = 0; i < histogram.length; i++) {
            int currentArea = histogram[i] * (rightSmall[i] - leftSmall[i] + 1);
            
            maxArea = Math.max(maxArea, currentArea);
        }

        return maxArea;
    }
}
