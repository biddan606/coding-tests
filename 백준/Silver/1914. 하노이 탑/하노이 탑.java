import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    private static boolean shouldRecordPath;
    private static final List<String> transitionPaths = new ArrayList<>();
    private static BigInteger[] moveCountCache;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int topSize = Integer.parseInt(reader.readLine());

        reader.close();

        shouldRecordPath = topSize <= 20;
        moveCountCache = new BigInteger[topSize + 1];

        BigInteger totalMoves = hanoi(topSize, 1, 2, 3);

        System.out.println(totalMoves);

        transitionPaths.forEach(System.out::println);
    }

    private static BigInteger hanoi(int n, int from, int via, int to) {
        if (n == 0) {
            return BigInteger.ZERO;
        }

        if (Objects.isNull(moveCountCache[n]) || shouldRecordPath) {
            BigInteger movesBefore = hanoi(n - 1, from, to, via);
            if (shouldRecordPath) {
                transitionPaths.add(from + " " + to);
            }
            BigInteger movesAfter = hanoi(n - 1, via, from, to);

            BigInteger totalMoves = movesBefore.add(BigInteger.ONE).add(movesAfter);
            moveCountCache[n] = totalMoves;
        }

        return moveCountCache[n];
    }
}
