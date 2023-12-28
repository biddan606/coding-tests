import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[][] solution(int n) {
        List<int[]> result = hanoi(n, 1, 3);

        return result.toArray(int[][]::new);
    }

    private List<int[]> hanoi(int size, int from, int to) {
        ArrayList<int[]> result = new ArrayList<>();

        if (size == 1) {
            result.add(new int[]{from, to});

            return result;
        }

        int empty = 6 - from - to;

        result.addAll(hanoi(size - 1, from, empty));
        result.addAll(hanoi(1, from, to));
        result.addAll(hanoi(size - 1, empty, to));

        return result;
    }
}
