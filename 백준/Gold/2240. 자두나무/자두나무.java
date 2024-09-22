import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] firstLine = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int seconds = firstLine[0];
        int maxMoves = firstLine[1];

        int[] plumLocations = new int[seconds];
        for (int i = 0; i < seconds; i++) {
            plumLocations[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        // [초][이동 횟수]
        int[][] dp = new int[seconds + 1][maxMoves + 1];

        for (int s = 1; s <= seconds; s++) {
            int plumLocation = plumLocations[s - 1];
            dp[s][0] = dp[s - 1][0] + match(1, plumLocation);

            for (int m = 1; m <= maxMoves; m++) {
                int prevLocation = (m - 1) % 2 + 1;
                int currentLocation = m % 2 + 1;

                dp[s][m] = Math.max(dp[s - 1][m - 1] + match(prevLocation, plumLocation),
                        dp[s - 1][m] + match(currentLocation, plumLocation));
            }
        }

        int maxCount = 0;
        for (int m = 0; m <= maxMoves; m++) {
            maxCount = Math.max(maxCount, dp[seconds][m]);
        }

        System.out.println(maxCount);
    }

    private static int match(int currentLocation, int plumLocation) {
        if (currentLocation == plumLocation) {
            return 1;
        }
        return 0;
    }
}
