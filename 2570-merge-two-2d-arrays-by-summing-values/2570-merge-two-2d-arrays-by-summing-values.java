import java.util.*;

class Solution {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        SortedMap<Integer, Integer> sortedMap = new TreeMap<>();
        
        for (int[] entry : nums1) {
            int id = entry[0];
            int value = entry[1];

            sortedMap.put(id, sortedMap.getOrDefault(id, 0) + value);
        }

        for (int[] entry : nums2) {
            int id = entry[0];
            int value = entry[1];

            sortedMap.put(id, sortedMap.getOrDefault(id, 0) + value);
        }

        int[][] result = new int[sortedMap.size()][2];
        int resultIndex = 0;

        for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
            result[resultIndex][0] = entry.getKey();
            result[resultIndex][1] = entry.getValue();
            
            resultIndex++;
        }

        return result;
    }
}
