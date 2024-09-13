import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int maxMoveCount = 0;
    private static final int[][] DIRECTIONS = {
            {1, 0},
            {-1, 0},
            {0, -1},
            {0, 1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] lengths = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[][] matrix = new int[lengths[0]][];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = Arrays.stream(br.readLine().split(""))
                    .map(s -> s.equals("H") ? "-1" : s)
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        br.close();

        boolean[][] visited = new boolean[lengths[0]][lengths[1]];
        int[][] dp = new int[lengths[0]][lengths[1]];
        game(matrix, visited, dp, 0, 0, 1);

        System.out.println(maxMoveCount);
    }

    private static void game(int[][] matrix, boolean[][] visited, int[][]dp, int row, int col, int currentMoveCount) {
        if (visited[row][col]) {
            maxMoveCount = -1;
        }
        if (maxMoveCount == -1 || matrix[row][col] == -1) {
            return;
        }

        visited[row][col] = true;
        maxMoveCount = Math.max(maxMoveCount, currentMoveCount);
        dp[row][col] = currentMoveCount;

        int currentNumber = matrix[row][col];
        int nextMoveCount = currentMoveCount + 1;

        for (int[] d : DIRECTIONS) {
            int nextRow = row + d[0] * currentNumber;
            int nextCol = col + d[1] * currentNumber;

            if (isWithinRange(matrix, nextRow, nextCol) && dp[nextRow][nextCol] < nextMoveCount) {
                game(matrix, visited, dp, nextRow, nextCol, nextMoveCount);
            }
        }

        visited[row][col] = false;
    }

    private static boolean isWithinRange(int[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length
                && col >= 0 && col < matrix[0].length;
    }
}
