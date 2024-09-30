import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] chars = br.readLine().toCharArray();
        br.close();

        // 팰린드롬 구하기
        boolean[][] palindromes = new boolean[chars.length][chars.length];
        for (int i = 0; i < chars.length; i++) {
            for (int j = i; j <= i + 1; j++) { // 홀수, 짝수
                for (int w = 0; w < chars.length; w++) {
                    if (i - w < 0 || j + w >= chars.length
                            || chars[i - w] != chars[j + w]) {
                        break;
                    }

                    palindromes[i - w][j + w] = true;
                }
            }
        }

        int[] dp = new int[chars.length];
        Arrays.fill(dp, Integer.MAX_VALUE);

        for (int j = 0; j < chars.length; j++) {
            if (palindromes[0][j]) {
                dp[j] = 1;
                continue;
            }

            for (int i = 1; i <= j; i++) {
                if (palindromes[i][j]) {
                    dp[j] = Math.min(dp[j], dp[i - 1] + 1);
                }
            }
        }

        System.out.println(dp[dp.length - 1]);
    }

}
