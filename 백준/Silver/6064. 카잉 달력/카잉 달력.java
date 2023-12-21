import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        // 입출력 설정
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // 입력을 받는다.
        int caseCount = Integer.parseInt(reader.readLine());

        int[][] cases = new int[caseCount][];
        for (int i = 0; i < cases.length; i++) {
            cases[i] = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        // 몇번째 해인지 구한다.
        int[] results = new int[cases.length];
        for (int i = 0; i < results.length; i++) {
            results[i] = getYear(cases[i][0], cases[i][1], cases[i][2], cases[i][3]);
        }

        // 출력
        for (int result : results) {
            System.out.println(result);
        }

        // 입출력 해제
        reader.close();
    }

    private static int getYear(int maxX, int maxY, int targetX, int targetY){
        int maxYear = lcm(maxX, maxY);
        int x = targetX;
        int y = targetY;

        while (x <= maxYear && y <= maxYear) {
            if (x == y) {
                return x;
            }

            if (x > y) {
                y += maxY;
            } else {
                x += maxX;
            }
        }

        return -1;
    }

    private static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    private static int gcd(int a, int b) {
        if (a < b) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }

        return a;
    }
}
