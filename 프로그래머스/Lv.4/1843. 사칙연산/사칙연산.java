import java.util.*;

class Solution {
    private final int[][] maxMemory = new int[202][202];
    private final int[][] minMemory = new int[202][202];
    
    public int solution(String arr[]) {
        for (int[] row : maxMemory) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        for (int[] row : minMemory) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        return max(0, arr.length, arr);
    }
    
    private int max(int start, int end, String[] array) {
        if (end - start == 1) {
            return Integer.parseInt(array[start]);
        }
        if (maxMemory[start][end] != Integer.MIN_VALUE) {
            return maxMemory[start][end];
        }
        
        int max = Integer.MIN_VALUE;
        for (int i = start + 1; i < end; i += 2) {
            int left = max(start, i, array);
            
            int value;
            if (array[i].equals("+")) {
                int right = max(i + 1, end, array);
                value = left + right;
            } else { // array[i].equals("-")
                int right = min(i + 1, end, array);
                value = left - right;
            }
            
            max = Math.max(max, value);
        }
        
        maxMemory[start][end] = max;
        return max;
    }
    
    private int min(int start, int end, String[] array) {
        if (end - start == 1) {
            return Integer.parseInt(array[start]);
        }
        if (minMemory[start][end] != Integer.MAX_VALUE) {
            return minMemory[start][end];
        }
        
        int min = Integer.MAX_VALUE;
        for (int i = start + 1; i < end; i += 2) {
            int left = min(start, i, array);
            
            int value;
            if (array[i].equals("+")) {
                int right = min(i + 1, end, array);
                value = left + right;
            } else { // array[i].equals("-")
                int right = max(i + 1, end, array);
                value = left - right;
            }
            
            min = Math.min(min, value);
        }
        
        minMemory[start][end] = min;
        return min;
    }
}