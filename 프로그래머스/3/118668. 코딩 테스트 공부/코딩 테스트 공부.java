import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

class Solution {

    public int solution(int alp, int cop, int[][] problems) {
        // 최대값 구하기
        int maxRow = alp;
        int maxCol = cop;
        for (int[] problem : problems) {
            maxRow = Math.max(maxRow, problem[0]);
            maxCol = Math.max(maxCol, problem[1]);
        }

        // 코스트 초기화
        int[][] costs = new int[maxRow - alp + 1][maxCol - cop + 1];
        for (int row = 0; row < costs.length; row++) {
            for (int col = 0; col < costs[0].length; col++) {
                costs[row][col] = Integer.MAX_VALUE;
            }
        }
        costs[0][0] = 0;

        List<int[]> problemsWithAddedStudy = new ArrayList<>(Arrays.stream(problems)
                .map(a -> new int[]{a[0] - alp, a[1] - cop, a[2], a[3], a[4]})
                .collect(Collectors.toList()));
        problemsWithAddedStudy.add(new int[]{0, 0, 1, 0, 1});
        problemsWithAddedStudy.add(new int[]{0, 0, 0, 1, 1});

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0, 0});

        while (!queue.isEmpty()) {
            int[] currentPointInformation = queue.poll();

            problemsWithAddedStudy.stream()
                    .filter(problem -> currentPointInformation[0] >= problem[0]
                            && currentPointInformation[1] >= problem[1])
                    .forEach(problem -> {
                        int nextRow = Math.min(currentPointInformation[0] + problem[2], costs.length - 1);
                        int nextCol = Math.min(currentPointInformation[1] + problem[3], costs[0].length - 1);
                        int nextCost = currentPointInformation[2] + problem[4];

                        if (nextCost >= costs[nextRow][nextCol]) {
                            return;
                        }
                        costs[nextRow][nextCol] = nextCost;

                        queue.offer(new int[]{nextRow, nextCol, nextCost});
                    });
        }

        return costs[costs.length - 1][costs[0].length - 1];
    }

    public static void main(String[] args) {
        int alp = 0;
        int cop = 0;
        int[][] problems = {
                {0, 0, 2, 1, 2},
                {4, 5, 3, 1, 2},
                {4, 11, 4, 0, 2},
                {10, 4, 0, 4, 2}
        };

        Solution solution = new Solution();
        int result = solution.solution(alp, cop, problems);

        System.out.println(result);
    }
}
