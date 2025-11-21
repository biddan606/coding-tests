import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, V;
    static ArrayList<Integer>[] graph; // Map보다 배열 기반 리스트가 인덱스 접근이 빨라 효율적입니다.
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 정점 개수
        M = Integer.parseInt(st.nextToken()); // 간선 개수
        V = Integer.parseInt(st.nextToken()); // 시작 정점

        // 1. 그래프 초기화 (1번 정점부터 N번 정점까지 사용)
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 2. 간선 연결 (양방향)
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        // 3. 방문 순서를 위해 정렬 (작은 번호의 정점부터 방문)
        for (int i = 1; i <= N; i++) {
            Collections.sort(graph[i]);
        }

        // DFS 실행
        visited = new boolean[N + 1];
        dfs(V);
        sb.append('\n');

        // BFS 실행
        visited = new boolean[N + 1];
        bfs(V);
        
        System.out.println(sb);
    }

    private static void dfs(int current) {
        visited[current] = true;
        sb.append(current).append(" ");

        for (int next : graph[current]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }

    private static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true; // 큐에 넣을 때 방문 처리 (중복 삽입 방지)

        while (!queue.isEmpty()) {
            int current = queue.poll();
            sb.append(current).append(" ");

            for (int next : graph[current]) {
                if (!visited[next]) {
                    visited[next] = true; // 미리 방문 처리하여 큐 중복 방지
                    queue.offer(next);
                }
            }
        }
    }
}