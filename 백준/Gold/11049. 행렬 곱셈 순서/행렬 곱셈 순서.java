import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());
        int[][] matrix = new int[size][];
        for (int i = 0; i < size; i++) {
            matrix[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int[][] operationCounts = new int[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(operationCounts[i], Integer.MAX_VALUE);
            operationCounts[i][i] = 0;
        }

        for (int range = 1; range < size; range++) {
            for (int i = 0; i + range < size; i++) {
                for (int j = i; j + 1 <= i + range; j++) {
                    operationCounts[i][i + range] = Math.min(operationCounts[i][i + range],
                            operationCounts[i][j] + operationCounts[j + 1][i + range]
                                    + matrix[i][0] * matrix[j][1] * matrix[i + range][1]);
                }
            }
        }

        System.out.println(operationCounts[0][size - 1]);
    }
}
