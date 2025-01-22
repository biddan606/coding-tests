class Solution {
    private static final int[][] DIRECTIONS = new int[][]{
        {-1, 0},
        {1, 0},
        {0, -1},
        {0, 1}
    };

    public int[][] highestPeak(int[][] isWater) {
        // water cell을 얻는다
        int rows = isWater.length;
        int cols = isWater[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (isWater[r][c] == 1) {
                    queue.offer(new int[]{r, c, 0});
                }
            }
        }
        
        // water cell에 인접한 land cell들을 얻는다
        // land cell에 인접한 land cell을 얻는다
        // result에는 각 cell이 발견된 단계를 기입한다
        // 한번 방문한 cell은 다시 방문하지 않는다
        int[][] result = new int[rows][cols];
        for (int r = 0; r < rows; r++) {
            Arrays.fill(result[r], -1);
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            if (result[current[0]][current[1]] != -1) {
                continue;
            }
            
            result[current[0]][current[1]] = current[2];

            for (int[] direction : DIRECTIONS) {
                int[] next = new int[]{current[0] + direction[0], current[1] + direction[1], current[2] + 1};
                if (next[0] < 0 || next[0] >= rows || next[1] < 0 || next[1] >= cols) {
                    continue;
                }

                queue.offer(next);
            }
        }

        return result;
    }
}
