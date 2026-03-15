import java.util.*;

class Solution {
    private long bestLeafCount;

    public int solution(int dist_limit, int split_limit) {
        bestLeafCount = 1L;

        // depth별 자식 수(2 또는 3) 수열을 DFS로 생성
        dfs(1L, dist_limit, split_limit, new ArrayList<>());

        return (int) bestLeafCount;
    }

    /**
     * product: 현재 깊이까지의 분배도 곱
     * levels : 각 깊이에서 선택한 분기 수열 (2 또는 3)
     */
    private void dfs(long product, int distLimit, int splitLimit, List<Integer> levels) {
        bestLeafCount = Math.max(bestLeafCount, simulate(levels, distLimit));

        if (product * 2L <= splitLimit) {
            levels.add(2);
            dfs(product * 2L, distLimit, splitLimit, levels);
            levels.remove(levels.size() - 1);
        }

        if (product * 3L <= splitLimit) {
            levels.add(3);
            dfs(product * 3L, distLimit, splitLimit, levels);
            levels.remove(levels.size() - 1);
        }
    }

    /**
     * levels 수열이 고정되었을 때,
     * 위에서부터 가능한 만큼 분배 노드를 채워 넣어서 얻는 최대 리프 수를 계산한다.
     */
    private long simulate(List<Integer> levels, int distLimit) {
        long leafCount = 1L;      // 루트의 유일한 자식 1개가 초기 리프
        long currentNodes = 1L;   // 현재 깊이에서 분배 가능한 노드 수
        long remain = distLimit;  // 아직 사용할 수 있는 분배 노드 수

        for (int branch : levels) {
            if (remain == 0L || currentNodes == 0L) {
                break;
            }

            long use = Math.min(currentNodes, remain);

            // 리프 1개를 branch개 자식으로 바꾸면 리프 수는 (branch - 1)만큼 증가
            leafCount += use * (branch - 1L);

            remain -= use;
            currentNodes = use * branch;
        }

        return leafCount;
    }
}