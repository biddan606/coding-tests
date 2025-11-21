import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int vertexCount = Integer.parseInt(tokenizer.nextToken());
        int edgeCount = Integer.parseInt(tokenizer.nextToken());
        int startVertex = Integer.parseInt(tokenizer.nextToken());

        Map<Integer, List<Integer>> edges = new HashMap<>();
        for (int v = 1; v <= vertexCount; v++) {
            edges.put(v, new ArrayList<>());
        }

        for (int i = 0; i < edgeCount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int vertex1 = Integer.parseInt(tokenizer.nextToken());
            int vertex2 = Integer.parseInt(tokenizer.nextToken());

            edges.get(vertex1).add(vertex2);
            edges.get(vertex2).add(vertex1);
        }
        reader.close();

        for (List<Integer> e : edges.values()) {
            e.sort(Integer::compareTo);
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        dfs(edges, startVertex, new boolean[vertexCount + 1], writer);
        writer.newLine();

        bfs(edges, vertexCount, startVertex, writer);
        writer.newLine();

        writer.flush();
        writer.close();
    }

    private static void dfs(Map<Integer, List<Integer>> edges, int startVertex, boolean[] visited,
            BufferedWriter writer)
            throws IOException {
        visited[startVertex] = true;
        writer.write(startVertex + " ");

        edges.get(startVertex).forEach(v -> {
            if (visited[v]) {
                return;
            }

            try {
                dfs(edges, v, visited, writer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void bfs(Map<Integer, List<Integer>> edges, int vertexCount, int startVertex, BufferedWriter writer)
            throws IOException {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(startVertex);

        boolean[] visited = new boolean[vertexCount + 1];

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            if (visited[vertex]) {
                continue;
            }

            visited[vertex] = true;
            writer.write(vertex + " ");

            edges.get(vertex).forEach(v -> {
                if (visited[v]) {
                    return;
                }

                queue.offer(v);
            });
        }
    }
}
