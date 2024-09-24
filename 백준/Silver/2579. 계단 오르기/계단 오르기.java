import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static final int MAX_SIZE =300;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());
        int[] stairs = new int[MAX_SIZE];
        for (int i = 0; i < size; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        Integer maxScore = calculateMaxScore(size, stairs);
        System.out.println(maxScore);
    }

    private static int calculateMaxScore(int size, int[] stairs) {
        if (size <= 2) {
            return Arrays.stream(stairs).sum();
        }

        int[][] dp = new int[MAX_SIZE][2];
        dp[0][0] = stairs[0];
        dp[1][0] = stairs[1];
        dp[1][1] = dp[0][0] + stairs[1];

        for (int i = 2; i < dp.length; i++) {
            int currentStair = stairs[i];

            dp[i][0] = Math.max(dp[i - 2][0], dp[i - 2][1]) + currentStair;
            dp[i][1] = dp[i - 1][0] + currentStair;
        }

        return Math.max(dp[size - 1][0], dp[size - 1][1]);
    }
}
