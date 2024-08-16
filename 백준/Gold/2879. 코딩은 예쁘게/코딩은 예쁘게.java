import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(reader.readLine());
        int[] currentTaps = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] correctTaps = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        reader.close();

        int prevMove = 0;
        int totalMoves = 0;

        for (int i = 0; i < size; i++) {
            int currentTap = currentTaps[i];
            int correctTap = correctTaps[i];

            int currentMove = correctTap - currentTap;

            // 이전 탭이 이동하려는 방향이 같다면, 이전 탭 이동과 현재 탭 이동 중 작은 횟수만큼 같이 이동한다.
            totalMoves += calculateMove(prevMove, currentMove);

            prevMove = currentMove;
        }

        System.out.println(totalMoves);
    }

    private static int calculateMove(int prevMove, int currentMove) {
        int absPrevMove = Math.abs(prevMove);
        int absCurrentMove = Math.abs(currentMove);

        if (isSameDirection(prevMove, currentMove)) {
            return absCurrentMove - Math.min(absPrevMove, absCurrentMove);
        }
        return absCurrentMove;
    }

    private static boolean isSameDirection(int move1, int move2) {
        return (move1 < 0 && move2 < 0) || (move1 > 0 && move2 > 0);
    }
}
