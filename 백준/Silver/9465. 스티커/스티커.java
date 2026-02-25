import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();

        int T = Integer.parseInt(reader.readLine());
        for (int i = 0; i < T; i++) {
            int colSize = Integer.parseInt(reader.readLine());
            int[][] values = new int[2][colSize];

            for (int row = 0; row < 2; row++) {
                StringTokenizer colTokenizer = new StringTokenizer(reader.readLine());
                for (int col = 0; col < colSize; col++) {
                    values[row][col] = Integer.parseInt(colTokenizer.nextToken());
                }
            }

            int[][] dp = new int[2][colSize];

            for (int col = 0; col < colSize; col++) {
                // row=0
                if (col >= 2) {
                    dp[0][col] = dp[0][col - 2];
                    dp[0][col] = Math.max(dp[0][col], dp[1][col - 2]);
                }
                if (col >= 1) {
                    dp[0][col] = Math.max(dp[0][col], dp[1][col - 1]);
                }

                dp[0][col] += values[0][col];

                // row=1
                if (col >= 2) {
                    dp[1][col] = dp[0][col - 2];
                    dp[1][col] = Math.max(dp[1][col], dp[1][col - 2]);
                }
                if (col >= 1) {
                    dp[1][col] = Math.max(dp[1][col], dp[0][col - 1]);
                }

                dp[1][col] += values[1][col];
            }

            int answer = 0;
            for (int row = 0; row < 2; row++) {
                for (int col = Math.max(0, colSize - 2); col < colSize; col++) {
                    answer = Math.max(answer, dp[row][col]);
                }
            }

            stringBuilder.append(answer).append("\n");
        }

        reader.close();
        System.out.println(stringBuilder);
    }
}
