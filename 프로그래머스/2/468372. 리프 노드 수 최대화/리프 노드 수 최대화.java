class Solution {
    int answer = 1;

    public int solution(int dist_limit, int split_limit) {
        answer = 1;
        dfs(1L, 1L, 1L, 0L, dist_limit, split_limit);
        return answer;
    }

    // cur  : 현재 depth에서 확장 가능한 노드 수
    // used : 지금까지 사용한 분배 노드 수
    // split: 현재까지의 분배도 곱
    // leaf : 이미 확정된 리프 수
    private void dfs(long cur, long used, long split, long leaf,
                     int distLimit, int splitLimit) {

        if (used > distLimit) return;

        // 지금 멈추면 남은 cur개는 전부 리프
        answer = (int) Math.max(answer, leaf + cur);

        for (int child = 2; child <= 3; child++) {
            long nextSplit = split * child;
            if (nextSplit > splitLimit) continue;

            long nextNodes = cur * child;
            long remain = distLimit - used;

            // 다음 depth로 넘길 분배 노드는 항상 최대한 많이
            long nextCur = Math.min(nextNodes, remain);
            long nextLeaf = leaf + (nextNodes - nextCur);

            dfs(nextCur, used + nextCur, nextSplit, nextLeaf,
                distLimit, splitLimit);
        }
    }
}