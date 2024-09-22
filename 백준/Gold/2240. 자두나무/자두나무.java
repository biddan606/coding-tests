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

        // [초][이동 횟수], [0]: 현재 위치, [1]: 획득한 자두의 개수
        int[][][] dp = new int[seconds + 1][maxMoves + 1][2];
        for (int m = 0; m <= maxMoves; m++) {
            dp[0][m] = new int[]{1, 0};
        }

        for (int s = 1; s <= seconds; s++) {
            int plumLocation = plumLocations[s - 1];
            dp[s][0] = new int[]{1, dp[s - 1][0][1] + match(1, plumLocation)};

            for (int m = 1; m <= maxMoves; m++) {
                int currentLocation = dp[s - 1][m][0];
                int stayed = dp[s - 1][m][1] + match(currentLocation, plumLocation);
                int moved = dp[s - 1][m - 1][1] + 1;

                if (stayed < moved) {
                    dp[s][m] = new int[]{plumLocation, moved};
                } else {
                    dp[s][m] = new int[]{currentLocation, stayed};
                }
            }
        }

        int maxCount = 0;
        for (int m = 0; m <= maxMoves; m++) {
            maxCount = Math.max(maxCount, dp[seconds][m][1]);
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
