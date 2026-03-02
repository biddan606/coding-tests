import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] sequence;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sequence = new int[M];
        visited = new boolean[N];

        dfs(0);

        // 모든 출력을 모아서 한 번에 출력
        System.out.print(sb);
    }

    private static void dfs(int depth) {
        if (depth != M) {
            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    sequence[depth] = i + 1;
                    dfs(depth + 1);

                    visited[i] = false;
                }
            }
        } else {
            for (int val : sequence) {
                sb.append(val).append(' ');
            }
            sb.append('\n');
            return;
        }
    }
}