import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int inputSize = Integer.parseInt(reader.readLine());
        String[][] lines = new String[inputSize][];
        for (int i = 0; i < inputSize; i++) {
            lines[i] = reader.readLine().split(" ");
        }

        long[] counts = new long[3]; // -1, 0, 1의 개수를 저장할 배열
        countPapers(lines, counts, 0, 0, inputSize);

        for (long count : counts) {
            System.out.println(count);
        }
    }

    private static void countPapers(String[][] lines, long[] counts, int startRow, int startCol, int size) {
        if (isUniform(lines, startRow, startCol, size)) {
            String value = lines[startRow][startCol];
            counts[Integer.parseInt(value) + 1]++;
            return;
        }

        int newSize = size / 3;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                countPapers(lines, counts, startRow + row * newSize, startCol + col * newSize, newSize);
            }
        }
    }

    private static boolean isUniform(String[][] lines, int startRow, int startCol, int size) {
        String firstValue = lines[startRow][startCol];
        for (int i = startRow; i < startRow + size; i++) {
            for (int j = startCol; j < startCol + size; j++) {
                if (!lines[i][j].equals(firstValue)) {
                    return false;
                }
            }
        }
        return true;
    }
}
