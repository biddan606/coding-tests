import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static int[] numbers;
    private static int result = 0;

    public static void main(String[] args) throws IOException {
        // 입력
        // 1. 숫자 개수
        // 2. 숫자들
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());
        numbers = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        reader.close();

        dfs(new int[size], 0, new boolean[size]);

        // 결과 출력
        System.out.println(result);
    }

    private static void dfs(int[] array, int depth, boolean[] visited) {
        if (depth == numbers.length) {
            int currentResult = 0;
            for (int i = 0; i + 1 < array.length; i++) {
                currentResult += Math.abs(array[i + 1] - array[i]);
            }

            result = Math.max(currentResult, result);
            return;
        }

        for (int i = 0; i < numbers.length; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            array[depth] = numbers[i];

            dfs(array, depth + 1,  visited);

            visited[i] = false;
        }
    }
}
