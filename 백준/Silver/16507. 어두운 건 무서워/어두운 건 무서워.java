import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int rows = Integer.parseInt(st.nextToken());
        int cols = Integer.parseInt(st.nextToken());
        int queryCount = Integer.parseInt(st.nextToken());

        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            matrix[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int[][] brightnessSums = new  int[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                brightnessSums[r][c] += matrix[r][c];

                if (r > 0) {
                    brightnessSums[r][c] += brightnessSums[r - 1][c];
                }
                if (c > 0) {
                    brightnessSums[r][c] += brightnessSums[r][c - 1];
                }
                if (r > 0 && c > 0) {
                    brightnessSums[r][c] -= brightnessSums[r - 1][c - 1];
                }
            }
        }
        
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < queryCount; i++) {
            int[] query = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .map(n -> n - 1)
                    .toArray();

            int[] vertex1 = new int[]{query[0], query[1]};
            int[] vertex2 = new int[]{query[2], query[3]};

            int brightnessSum = brightnessSums[vertex2[0]][vertex2[1]];
            if (vertex1[0] > 0) {
                brightnessSum -= brightnessSums[vertex1[0] - 1][vertex2[1]];
            }
            if (vertex1[1] > 0) {
                brightnessSum -= brightnessSums[vertex2[0]][vertex1[1] - 1];
            }
            if (vertex1[0] > 0 && vertex1[1] > 0) {
                brightnessSum += brightnessSums[vertex1[0] - 1][vertex1[1] - 1];
            }

            int brightnessCount = (vertex2[0] -  vertex1[0] + 1) * (vertex2[1] -  vertex1[1] + 1);

            bw.write(String.valueOf(brightnessSum / brightnessCount));
            bw.write("\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
