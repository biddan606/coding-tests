class Solution {
    /*
    # 문제 이해
    첫 포인트 (0, 0) -> 마지막 포인트 (rows - 1, cols - 1) 최소 이동 시간을 반환해야 한다
    포인트로 이동 시간은 max(prevMinMoveTime + a,moveTime + a)이다
    a는 1 -> 2 -> 1 -> 2... 반복된다

    # 풀이 접근
    https://leetcode.com/problems/find-minimum-time-to-reach-last-room-i/?envType=daily-question&envId=2025-05-07 와 비슷하다
    이 풀이에서 변하는 이동 시간을 추가해주면 된다

    # 구현 스텝
    1. 우선순위 큐를 초기화한다(작은 시간부터), 초기값은 (0, 0)
        - 우선 순위 큐는 작은 수행 시간이 가장 앞에 온다. 만약 수행 시간이 같다면, 이동 시간이 작은 순서가 앞에 온다
    2. (maxRow, maxCol) 방문 전까지 큐를 반복한다
    3. 큐에서 원소를 빼서 4방향을 돌며, 다음 포인트로 이동한다
        - max(원소 도착 시간, (r, c) 최소 도착) + a
    4. (maxRow, maxCol) 최소 도착 시간을 반환한다
    */
    private static final int[][] DIRECTIONS = {
        {-1, 0},
        {1, 0},
        {0, -1},
        {0, 1}
    };

    public int minTimeToReach(int[][] moveTime) {
        int rows = moveTime.length;
        int cols = moveTime[0].length;

        PriorityQueue<Element> pq = new PriorityQueue<>((e1, e2) -> {
            if (e1.arriveTime == e2.arriveTime) {
                return e1.moveTime - e2.moveTime;
            }
            return e1.arriveTime - e2.arriveTime;
        });
        pq.offer(new Element(0, 0, 0, 1));

        boolean[][] visited = new boolean[rows][cols];
        int targetArrivalTime = -1;
        
        while (!visited[rows - 1][cols - 1]) {
            Element e = pq.poll();

            for (int[] dir : DIRECTIONS) {
                int nextRow = e.row + dir[0];
                int nextCol = e.col + dir[1];
                if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols
                || visited[nextRow][nextCol]) {
                    continue;
                }

                int nextArrivalTime = Math.max(e.arriveTime, moveTime[nextRow][nextCol]) + e.moveTime;
                int nextMoveTime = e.moveTime == 1 ? 2 : 1;

                pq.offer(new Element(nextRow, nextCol, nextArrivalTime, nextMoveTime));
                visited[nextRow][nextCol] = true;
                if (nextRow == rows - 1 && nextCol == cols - 1) {
                    targetArrivalTime = nextArrivalTime;
                }
            }
        }

        return targetArrivalTime;
    }

    private static class Element {
        final int row;
        final int col;
        final int arriveTime;
        final int moveTime;

        public Element(int row, int col, int arriveTime, int moveTime) {
            this.row = row;
            this.col = col;
            this.arriveTime = arriveTime;
            this.moveTime = moveTime;
        }
    }
}
