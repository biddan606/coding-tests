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

        dfs(new ArrayList<>(), new boolean[size]);

        // 결과 출력
        System.out.println(result);
    }

    private static void dfs(List<Integer> list, boolean[] visited) {
        if (list.size() == numbers.length) {
            int currentResult = 0;
            for (int i = 0; i + 1 < list.size(); i++) {
                currentResult += Math.abs(list.get(i) - list.get(i + 1));
            }

            result = Math.max(currentResult, result);
            return;
        }

        for (int i = 0; i < numbers.length; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            list.add(numbers[i]);

            dfs(list, visited);

            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }
}
