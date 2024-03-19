import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final List<String> transitionPaths = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int topSize = Integer.parseInt(reader.readLine());

        reader.close();

        /*
        원판 횟수 식 유도, 2^n + 2^(n - 1) + ... + 1 -> 기하급수적인 수열의 합 공식: a(r^n - 1) / (r - 1)
        a = 1, r = 2 이므로 2^n - 1
         */
        BigInteger totalMoves = BigInteger.TWO.pow(topSize).subtract(BigInteger.ONE);
        System.out.println(totalMoves);

        if (topSize <= 20) {
            hanoi(topSize, 1, 2, 3);
        }

        StringBuilder sb = new StringBuilder();
        transitionPaths.forEach(path -> sb.append(path).append('\n'));

        System.out.print(sb);
    }

    private static void hanoi(int n, int from, int via, int to) {
        if (n == 0) {
            return;
        }

        hanoi(n - 1, from, to, via);
        transitionPaths.add(from + " " + to);
        hanoi(n - 1, via, from, to);
    }
}
