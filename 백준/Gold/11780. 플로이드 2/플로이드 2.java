import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static final int DISTANCE_MAX_VALUE = Integer.MAX_VALUE / 2;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int vertexCount = Integer.parseInt(reader.readLine());
        int edgeCount = Integer.parseInt(reader.readLine());

        int[][] distances = new int[vertexCount + 1][vertexCount + 1];
        for (int r = 1; r < distances.length; r++) {
            Arrays.fill(distances[r], 1, distances[r].length, DISTANCE_MAX_VALUE);
        }

        int[][] next = new int[vertexCount + 1][vertexCount + 1];

        for (int i = 0; i < edgeCount; i++) {
            int[] line = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int from = line[0];
            int to = line[1];
            int weight = line[2];

            distances[from][to] = Math.min(distances[from][to], weight);
            next[from][to] = to;
        }

        for (int r = 1; r < distances.length; r++) {
            distances[r][r] = 0;
        }

        reader.close();

        floydWarshall(vertexCount, distances, next);

        printDistances(distances);

        for (int from = 1; from <= vertexCount; from++) {
            for (int to = 1; to <= vertexCount; to++) {
                printPath(next, from, to);
            }
        }
    }

    private static void floydWarshall(int vertexCount, int[][] distances, int[][] next) {
        for (int mid = 1; mid <= vertexCount; mid++) {
            for (int start = 1; start <= vertexCount; start++) {
                for (int end = 1; end <= vertexCount; end++) {
                    if (distances[start][end] > distances[start][mid] + distances[mid][end]) {
                        distances[start][end] = distances[start][mid] + distances[mid][end];
                        next[start][end] = next[start][mid];
                    }
                }
            }
        }

        for (int start = 1; start <= vertexCount; start++) {
            for (int end = 1; end <= vertexCount; end++) {
                if (next[start][end] == 0) {
                    distances[start][end] = 0;
                }
            }
        }
    }

    private static void printDistances(int[][] distances) {
        StringBuilder sb = new StringBuilder();
        for (int from = 1; from < distances.length; from++) {
            for (int to = 1; to < distances[from].length; to++) {
                sb.append(distances[from][to]).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }

    private static void printPath(int[][] paths, int from, int to) {
        if (paths[from][to] == 0) {
            System.out.println("0");
            return;
        }

        List<Integer> path = new ArrayList<>();
        int current = from;

        while (current != to) {
            path.add(current);
            current = paths[current][to];
        }
        path.add(to);

        String pathString = path.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println(path.size() + " " + pathString);
    }
}
