import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    private static int queenCaseCount;
    private static int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1},
            {1, 1},
            {-1, -1},
            {1, -1},
            {-1, 1},
    };

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(reader.readLine());
        reader.close();

        int[][] availableCounts = new int[size][size];
        queenCaseCount = 0;

        dfs(availableCounts, 0);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        writer.write(Integer.toString(queenCaseCount));
        writer.flush();
        writer.close();
    }

    private static void dfs(int[][] available, int row) {
        if (available.length == row) {
            queenCaseCount++;
            return;
        }

        for (int col = 0; col < available.length; col++) {
            if (available[row][col] > 0) {
                continue;
            }

            check(available, row, col, 1);
            dfs(available, row + 1);
            check(available, row, col, -1);
        }
    }

    private static void check(int[][] availableCounts, int row, int col, int count) {
        availableCounts[row][col] += count;

        for (int[] direction : directions) {
            int currentRow = row + direction[0];
            int currentCol = col + direction[1];

            while (isInRange(availableCounts, currentRow, currentCol)) {
                availableCounts[currentRow][currentCol] += count;

                currentRow = currentRow + direction[0];
                currentCol = currentCol + direction[1];
            }
        }
    }

    private static boolean isInRange(int[][] available, int row, int col) {
        return  row >= 0 && row < available.length && col >= 0 && col < available[0].length;
    }
}
