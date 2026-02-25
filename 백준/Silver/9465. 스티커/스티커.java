import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(reader.readLine());
        while (T-- > 0) {
            int colSize = Integer.parseInt(reader.readLine());
            int[][] values = new int[2][colSize];

            for (int row = 0; row < 2; row++) {
                StringTokenizer st = new StringTokenizer(reader.readLine());
                for (int col = 0; col < colSize; col++) {
                    values[row][col] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] dp = new int[2][colSize];

            for (int col = 0; col < colSize; col++) {
                for (int row = 0; row < 2; row++) {
                    int other = 1 - row;
                    int best = 0;

                    if (col >= 2) {
                        best = Math.max(dp[row][col - 2], dp[other][col - 2]);
                    }
                    if (col >= 1) {
                        best = Math.max(best, dp[other][col - 1]);
                    }

                    dp[row][col] = best + values[row][col];
                }
            }

            int answer = 0;
            for (int row = 0; row < 2; row++) {
                for (int col = Math.max(0, colSize - 2); col < colSize; col++) {
                    answer = Math.max(answer, dp[row][col]);
                }
            }

            sb.append(answer).append("\n");
        }

        reader.close();
        System.out.println(sb);
    }
}