import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int inputSize = Integer.parseInt(reader.readLine());
        String[][] lines = new String[inputSize][];
        for (int i = 0; i < inputSize; i++) {
            lines[i] = reader.readLine().split("");
        }

        String result = compress(lines, 0, 0, inputSize);
        System.out.println(result);
    }

    private static String compress(String[][] lines, int startRow, int startCol, int size) {
        if (isUniform(lines, startRow, startCol, size)) {
            return lines[startRow][startCol];
        }

        int newSize = size / 2;
        StringBuilder result = new StringBuilder();
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 2; col++) {
                result.append(compress(lines, startRow + row * newSize, startCol + col * newSize, newSize));
            }
        }

        return "(" + result.toString() + ")";
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
