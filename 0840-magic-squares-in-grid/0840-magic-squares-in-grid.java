class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int magicSquareCount = 0;

        for (int endRow = 2; endRow < rows; endRow++) {
            for (int endCol = 2; endCol < cols; endCol++) {
                // 3x3 안에 1~9까지의 수가 유니크하게 존재해야 한다.
                // 각 행의 합은 15
                // 각 열의 합은 15
                // 대각선의 합은 15   
                int uniqueCheck = 0;
                int[] rowSums = new int[3];
                int[] colSums = new int[3];
                int[] diagSums = new int[2];

                for (int r = endRow - 2; r <= endRow; r++) {
                    for (int c = endCol - 2; c <= endCol; c++) {
                        int current = grid[r][c];
                        int relativeRow = r - (endRow - 2);
                        int relativeCol = c - (endCol - 2);

                        uniqueCheck ^= 1 << (current - 1);
                        rowSums[relativeRow] += current;
                        colSums[relativeCol] += current;

                        if (relativeRow == relativeCol) {
                            diagSums[0] += current;
                        }
                        if (relativeRow + relativeCol == 2) {
                            diagSums[1] += current;
                        }
                    }
                }

                boolean correct = true;

                if (uniqueCheck != 0x1FF) {
                    System.out.println("유니크하지 않습니다.");
                    correct = false;
                }

                for (int i = 0; i < rowSums.length; i++) {
                    if (rowSums[i] != 15) {
                        System.out.println("행 합 불일치");
                        correct = false;
                    }
                }

                for (int i = 0; i < colSums.length; i++) {
                    if (colSums[i] != 15) {
                        System.out.println("열 합 불일치");
                        correct = false;
                    }
                }

                for (int i = 0; i < diagSums.length; i++) {
                    if (diagSums[i] != 15) {
                        System.out.println("대각 합 불일치");
                        correct = false;
                    }
                }

                if (correct) {
                    magicSquareCount++;
                }
            }
        }

        return magicSquareCount;
    }
}
