import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    private int parentCount = 0;

    public int removeStones(int[][] stones) {
        // row: 0~10000, column: 10001~20001
        int[] parents = new int[20002];
        Arrays.fill(parents, -1);

        for (int[] stone : stones) {
            merge(parents, stone[0], stone[1] + 10001);
        }

        return stones.length - parentCount;
    }

    private void merge(int[] parents, int index1, int index2) {
        int parent1 = find(parents, index1);
        int parent2 = find(parents, index2);

        if (parent1 != parent2) {
            parents[parent2] = parent1;
            parentCount--;
        }
    }

    private int find(int[] parents, int startIndex) {
        if (parents[startIndex] == -1) {
            parents[startIndex] = startIndex;
            parentCount++;
        }

        if (parents[startIndex] != startIndex) {
            parents[startIndex] = find(parents, parents[startIndex]);
        }
        return parents[startIndex];
    }
}
