import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static final int DISTANCE_MAX_VALUE = Integer.MAX_VALUE / 2;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int vertexCount = Integer.parseInt(reader.readLine());
        int edgeCount = Integer.parseInt(reader.readLine());

        int[][] distances = new int[vertexCount + 1][vertexCount + 1];
        for (int[] row : distances) {
            Arrays.fill(row, DISTANCE_MAX_VALUE);
        }

        for (int i = 0; i < edgeCount; i++) {
            int[] tokens = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = tokens[0];
            int to = tokens[1];
            int weight = tokens[2];

            distances[from][to] = Math.min(distances[from][to], weight);
        }
        reader.close();

        for (int i = 1; i <= vertexCount; i++) {
            distances[i][i] = 0;
        }

        applyFloydWarshall(distances, vertexCount);

        printDistances(distances, vertexCount);
    }

    private static void applyFloydWarshall(int[][] distances, int vertexCount) {
        for (int mid = 1; mid <= vertexCount; mid++) {
            for (int start = 1; start <= vertexCount; start++) {
                for (int end = 1; end <= vertexCount; end++) {
                    distances[start][end] =
                            Math.min(distances[start][end], distances[start][mid] + distances[mid][end]);
                }
            }
        }
    }

    private static void printDistances(int[][] distances, int vertexCount) {
        StringBuilder sb = new StringBuilder();
        for (int row = 1; row <= vertexCount; row++) {
            for (int col = 1; col <= vertexCount; col++) {
                int currentDistance = distances[row][col];
                if (currentDistance == DISTANCE_MAX_VALUE) {
                    currentDistance = 0;
                }

                sb.append(currentDistance).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }
}
