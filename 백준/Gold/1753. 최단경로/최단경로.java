import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {

    private static final int UNVISITED = -1;

    public static void main(String[] args) throws IOException {
        /*
        입력을 받는다.
        첫째 줄은 정점의 개수와 간선의 개수를 입력받는다.
        둘째 줄은 시작 버텍스 번호를 입력받는다.
        세번째 줄부터 2개의 버텍스 번호와 가중치를 입력받는다.
         */
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] firstLine = readLineAsIntArray(reader);

        // index는 1부터 시작한다.
        int verticesSize = firstLine[0] + 1;
        int[] distancesFromStartId = new int[verticesSize];
        Arrays.fill(distancesFromStartId, UNVISITED);

        int edgeCount = firstLine[1];
        int startId = Integer.parseInt(reader.readLine());

        Map<Integer, List<Edge>> edges = new HashMap<>();
        for (int i = 1; i < verticesSize; i++) {
            edges.putIfAbsent(i, new ArrayList<>());
        }

        for (int i = 0; i < edgeCount; i++) {
            int[] line = readLineAsIntArray(reader);
            int fromId = line[0];
            int toId = line[1];
            int weight = line[2];

            edges.get(fromId).add(new Edge(toId, weight));
        }

        dijkstra(distancesFromStartId, edges, startId);

        for (int i = 1; i < distancesFromStartId.length; i++) {
            if (distancesFromStartId[i] == UNVISITED) {
                System.out.println("INF");
                continue;
            }

            System.out.println(distancesFromStartId[i]);
        }
    }

    private static int[] readLineAsIntArray(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static void dijkstra(int[] distancesFromStartId, Map<Integer, List<Edge>> edges, int startId) {
        // index 0: Id, 1: startId부터 currentId까지의 거리
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(array -> array[1]));
        pq.offer(new int[]{startId, 0});

        while (!pq.isEmpty()) {
            int[] element = pq.poll();
            int currentId = element[0];
            int currentDistance = element[1];

            int originalDistance = distancesFromStartId[currentId];
            if (originalDistance != UNVISITED && originalDistance <= currentDistance) {
                continue;
            }
            distancesFromStartId[currentId] = currentDistance;

            for (Edge e : edges.get(currentId)) {
                pq.offer(new int[]{e.toId, distancesFromStartId[currentId] + e.weight});
            }
        }
    }

    private static class Edge {

        final int toId;
        final int weight;

        public Edge(int toId, int weight) {
            this.toId = toId;
            this.weight = weight;
        }
    }
}
