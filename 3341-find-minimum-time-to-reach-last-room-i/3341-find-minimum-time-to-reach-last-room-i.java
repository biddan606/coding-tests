class Solution {
    /*
    # 문제 이해
    (i, j) 포인트에는 도착 시간이 moveTime[i][j]인 경우에만 갈 수 있다
    (maxRow, maxCol) 포인트에 도착하는 데에 걸리는 최소 시간을 반환해야 한다

    # 풀이 접근
    (0, 0)에서부터 4방향을 움직여 (maxRow, maxCol)에 도착하는 시간을 구할 수 있다
        - 아래, 오른쪽으로만 가지 않는 이유는 돌아가는 것이 더 빠를 수 있기 때문에
    이를 구현하기 위해 bfs를 이용할 수 있다

    bfs는 두가지 방식으로 같이 진행할 수 있다
    1. 항상 작은 시간만 움직이기, 이미 도착한 지점은 재도착X -> 작은 시간이 마지막에 도달하면 종료
    2. 재도착 가능, 이전 도착시간보다 작은 시간일 때만 -> queue에 원소가 없을 때 종료 -> 마지막 도달 시간 확인

    1번은 최소한의 원소만 움직인다. 다만 매번 작은 시간을 움직여야 하기 떄문에 O(N^logN)
    2번은 정렬할 필요가 없지만, 갱신이 여러번 이루어져 O(n^4)까지 올라갈 수 있다

    1번 방식으로 구

    # 구현 스텝
    1. 우선순위 큐를 초기화한다(작은 시간부터), 초기값은 (0, 0)
    2. (maxRow, maxCol) 방문 전까지 큐를 반복한다
    3. 큐에서 원소를 빼서 4방향을 돌며, 다음 포인트로 이동한다
        - max(원소 도착 시간 + 1, (r, c) 최소 도착 + 1)
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

        PriorityQueue<Element> pq = new PriorityQueue<>((e1, e2) -> e1.arriveTime - e2.arriveTime);
        pq.offer(new Element(0, 0, 0));

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

                int nextArrivalTime = Math.max(e.arriveTime + 1, moveTime[nextRow][nextCol] + 1);

                pq.offer(new Element(nextRow, nextCol, nextArrivalTime));
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

        public Element(int row, int col, int arriveTime) {
            this.row = row;
            this.col = col;
            this.arriveTime = arriveTime;
        }
    }
}
