import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 재귀 함수 간에 공유할 변수를 static으로 선언하여 파라미터 복잡도 제거
    static int N, M;
    static int[] sequence; // ArrayList 대신 고정 배열 사용
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder(); // I/O 연산을 최소화하기 위한 버퍼

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
        // Base Case: M개를 모두 골랐을 때
        if (depth == M) {
            for (int val : sequence) {
                sb.append(val).append(' ');
            }
            sb.append('\n');
            return;
        }

        // Recursive Case
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                sequence[depth] = i + 1; // ArrayList.add/remove 대신 인덱스 접근 사용
                
                dfs(depth + 1);
                
                visited[i] = false; // 백트래킹: 상태 복구
            }
        }
    }
}