class Solution {
    /*
    rows = 5;
    cols = 5;

    (0, 0) -> (0, 4)
    (0, 4) -> (4, 4)
    (4, 4) -> (4, 0)
    (4, 0) -> (1, 0)
    */
    int[][] directions = {
        {0, 1},
        {1, 0},
        {0, -1},
        {-1, 0}
    };

    public List<Integer> spiralOrder(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int totalCells = rows * cols;

        List<Integer> result = new ArrayList<>();
        int currentRow = 0;
        int currentCol = 0;
        boolean[][] visited = new boolean[rows][cols];
        int visitCount = 0;
        int di = 0;

        while (visitCount < totalCells) {
            visited[currentRow][currentCol] = true;
            visitCount++;
            result.add(matrix[currentRow][currentCol]);

            int nextRow = currentRow + directions[di][0];
            int nextCol = currentCol + directions[di][1];
            while (visitCount < totalCells 
                && (isOutOfRange(matrix, nextRow, nextCol) || visited[nextRow][nextCol])) {
                
                di = (di + 1) % 4;

                nextRow = currentRow + directions[di][0];
                nextCol = currentCol + directions[di][1];
            }

            currentRow = nextRow;
            currentCol = nextCol;
        }

        return result;
    }

    private boolean isOutOfRange(int[][] matrix, int row, int col) {
        return row < 0 || row >= matrix.length
            || col < 0 || col >= matrix[row].length;
    }
}
