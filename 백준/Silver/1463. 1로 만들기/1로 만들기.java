import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        // 입출력 설정
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int number = Integer.parseInt(reader.readLine());

        int[] dp = new int[number + 1];
        dp[number] = 0;

        for (int i = number - 1; i >= 1; i--) {
            dp[i] = dp[i + 1] + 1;

            if (i * 3 <= number) {
                dp[i] = Math.min(dp[i * 3] + 1, dp[i]);
            }

            if (i * 2 <= number) {
                dp[i] = Math.min(dp[i * 2] + 1, dp[i]);
            }
        }

        System.out.println(dp[1]);

        // 입출력 해제
        reader.close();
    }
}
