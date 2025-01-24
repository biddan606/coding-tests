class Solution {
    public int countServers(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[][] parents = new int[rows][cols];
        for (int r = 0; r < rows; r++) {
            Arrays.fill(parents[r], -1);

            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    parents[r][c] = r * cols + c;
                }
            }
        }

        Map<Integer, int[]> recentRowServers = new HashMap<>();
        Map<Integer, int[]> recentColServers = new HashMap<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 0) {
                    continue;
                }

                int[] current = new int[]{r, c};

                if (recentRowServers.containsKey(r)) {
                    union(parents, current, recentRowServers.get(r));
                }
                if (recentColServers.containsKey(c)) {
                    union(parents, current, recentColServers.get(c));
                }

                recentRowServers.put(r, current);
                recentColServers.put(c, current);
            }
        }

        Set<Integer> rootParents = new HashSet<>();
        int childCount = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1 && parents[r][c] != r * cols + c) {
                    childCount++;
                    rootParents.add(findRootParent(parents, new int[]{r, c}));
                }
            }
        }
        
        return rootParents.size() + childCount;
    }

    private static void union(int[][] parents, int[] point1, int[] point2) {
        int point1ParentNumber = findRootParent(parents, point1);
        int point2ParentNumber = findRootParent(parents, point2);

        int[] point1Parent = new int[]{point1ParentNumber / parents[0].length, point1ParentNumber % parents[0].length};
        parents[point1Parent[0]][point1Parent[1]] = point2ParentNumber;
    }

    private static int findRootParent(int[][] parents, int[] current) {
        int currentNumber = parents[current[0]][current[1]];
        int cols = parents[0].length;
        int[] parent = new int[]{currentNumber / cols, currentNumber % cols};
        int expectedParentNumber = parent[0] * cols + parent[1];
        
        if (parents[parent[0]][parent[1]] == expectedParentNumber) {
            return expectedParentNumber;
        }

        return parents[current[0]][current[1]] = findRootParent(parents, parent);
    }
}
