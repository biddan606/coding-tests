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

/*
(3, 1, 3) // 1, 2, 3
    (2, 1, 2) // 1, 2 // 1, 2로 되어 있는 원판을 뒤집음
        (1, 1, 3) // 1
        (1, 1, 2) // 2
        (1, 3, 2) // 1
    (1, 1, 3) // 3 // 맨 밑에 있는 원판을 원하는 to로 이동
    (2, 2, 3) // 2, 1 // 뒤집어진 원판들을 to로 이동
        (1, 2, 1) // 1
        (1, 2, 3) // 2
        (1, 1, 3) // 1
*/
