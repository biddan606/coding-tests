import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int lineCount = Integer.parseInt(reader.readLine());
        int[][] lines = new int[lineCount][];
        for (int i = 0; i < lineCount; i++) {
            lines[i] = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        reader.close();

        // 값이 21억까지오므로 dp 사용불가, 수식을 통해 표현해야 됨
        // 1 -> 121 -> 12321 -> 1234321 -> 123454321 이런 식으로 올 수 있음
        // 1(1번) -> 4(3번) -> 9(5번) -> 16(7번) -> 25(9번)
        // sqrt(n) * 2 - 1
        // 제곱보다 크다면 항상 +1, 그리고 어느 순간 1개를 더 증가시키다가 제곱이 됨
        // 어느 순간은 제곱 + 제곱근 보다 큰 값이 되는 순간
        int[] result = new int[lineCount];
        for (int i = 0; i < lineCount; i++) {
            int range = lines[i][1] - lines[i][0];
            if (range < 4) {
                result[i] = range;
                continue;
            }

            double squareRoot = Math.sqrt(range);

            result[i] = (int)(squareRoot) * 2 - 1;
            if (squareRoot - (int) squareRoot > 0) {
                result[i]++;
            }
            if (squareRoot - (int) squareRoot > 0.5d) {
                result[i]++;
            }
        }

        // 결과 출력
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < lineCount; i++) {
            writer.write(String.valueOf(result[i]));
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }
}
