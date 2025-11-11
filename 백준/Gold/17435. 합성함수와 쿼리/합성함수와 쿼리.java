import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int m = Integer.parseInt(br.readLine());

        int[] f = new int[m + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            f[i] = Integer.parseInt(st.nextToken());
        }

        int maxSquare = (int) (Math.log(500_000) / Math.log(2));
        int[][] dp = new int[m + 1][maxSquare + 1];
        for (int i = 1; i <= m; i++) {
            dp[i][0] = f[i];
        }

        for (int i = 1; i <= maxSquare; i++) {
            for (int j = 1; j <= m; j++) {
                dp[j][i] = dp[dp[j][i - 1]][i - 1];
            }
        }

        int q = Integer.parseInt(br.readLine());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            for (int square = maxSquare; n > 0 && square >= 0; square--) {
                if (n >= (1 << square)) {
                    x = dp[x][square];
                    n -= (1 << square);
                }
            }

            bw.write(String.valueOf(x));
            bw.newLine();
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
