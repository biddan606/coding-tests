import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String args[]) throws IOException {
        // 입력을 받는다.
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());

        // N번 1~N까지의 숫자를 순회한다.
        List<String> outputs = new ArrayList<>();
        dfs(new ArrayList<>(), new boolean[N + 1], N, outputs);

        String resultOutput = String.join("\n", outputs);
        System.out.println(resultOutput);
    }

    private static void dfs(List<Integer> list, boolean[] visited, int limit, List<String> outputs) {
        // 배열의 길이와 limit가 같아지면, 배열을 출력하고 돌아간다.
        if (list.size() == limit) {
            // 리스트를 출력한다.
            outputs.add(createOutput(list));
            return;
        }

        for (int i = 1; i <= limit; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            list.add(i);

            dfs(list, visited, limit, outputs);

            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }

    private static String createOutput(List<Integer> list) {
        return list.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
    }
}
