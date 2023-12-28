import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[][] solution(int n) {
        List<int[]> process  = new ArrayList<>();
        hanoi(n, 1, 3, process);

        return process.toArray(int[][]::new);
    }

    private void hanoi(int size, int from, int to, List<int[]> process) {
        if (size == 1) {
            process.add(new int[]{from, to});
            return;
        }

        int empty = 6 - from - to;

        hanoi(size - 1, from, empty, process);
        hanoi(1, from, to, process);
        hanoi(size - 1, empty, to, process);
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
