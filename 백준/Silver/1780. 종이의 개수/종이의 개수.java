import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int inputSize = Integer.parseInt(reader.readLine());
        String[][] lines = new String[inputSize][];
        for (int i = 0; i < inputSize; i++) {
            lines[i] = reader.readLine().split(" ");
        }

        // [0]: -1의 개수, [1]: 0의 개수, [2]: 1의 개수
        long[] counts = new long[3];

        count(lines, counts, 0, 0, lines.length, lines.length);

        // 출력
        for (long count : counts) {
            System.out.println(count);
        }

        reader.close();
    }

    private static boolean count(String[][] lines, long[] counts, int startRow, int startCol, int endRow, int endCol) {
        String value = lines[startRow][startCol];
        int plusValue = (endRow - startRow) / 3;
        if (plusValue == 0) {
            counts[Integer.parseInt(value) + 1]++;
            return true;
        }

        boolean result = true;
        for (int row = startRow; row < endRow; row += plusValue) {
            for (int col = startCol; col < endCol; col += plusValue) {
                if (!count(lines, counts, row, col, row + plusValue, col + plusValue)) {
                    result = false;
                }
                if (!value.equals(lines[row][col])) {
                    result = false;
                }
            }
        }

        if (result) {
            counts[Integer.parseInt(value) + 1] -= 8;
        }

        return result;
    }
}
