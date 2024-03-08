import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // vertex index는 1부터 시작하므로 크기를 1 증가시킨다.
        int verticesSize = Integer.parseInt(reader.readLine()) + 1;
        boolean[] visited = new boolean[verticesSize];
        Distance[] distancesFromStart = new Distance[verticesSize];

        int edgeCount = Integer.parseInt(reader.readLine());
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < edgeCount; i++) {
            int[] line = readLineAsIntArray(reader);
            int fromId = line[0];
            int toId = line[1];
            int weight = line[2];

            graph.putIfAbsent(fromId, new ArrayList<>());
            graph.get(fromId).add(new Edge(toId, weight));
        }

        int[] lastLine = readLineAsIntArray(reader);
        int startId = lastLine[0];
        int endId = lastLine[1];

        reader.close();

        dijkstra(distancesFromStart, visited, graph, startId, endId);

        printResult(distancesFromStart, endId);
    }

    private static int[] readLineAsIntArray(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static void dijkstra(Distance[] distancesFromStart, boolean[] visited, Map<Integer, List<Edge>> graph,
            int startId, int endId) {
        PriorityQueue<QueueElement> pq = new PriorityQueue<>(Comparator.comparingInt(q -> q.weight));
        pq.offer(new QueueElement(startId, startId, 0));

        while (!pq.isEmpty()) {
            QueueElement element = pq.poll();
            int fromId = element.fromId;
            int toId = element.toId;
            int weight = element.weight;

            if (visited[toId]) {
                continue;
            }
            visited[toId] = true;
            distancesFromStart[toId] = new Distance(fromId, weight);

            if (toId == endId) {
                return;
            }

            for (Edge e : graph.getOrDefault(toId, Collections.emptyList())) {
                int nextId = e.toId;
                pq.offer(new QueueElement(toId, nextId, weight + e.weight));
            }
        }
    }

    private static void printResult(Distance[] distancesFromStart, int endId) {
        System.out.println(distancesFromStart[endId].weight);

        List<Integer> result = getPath(distancesFromStart, endId);

        System.out.println(result.size());
        for (int pathId : result) {
            System.out.print(pathId + " ");
        }
        System.out.println();
    }

    private static List<Integer> getPath(Distance[] distancesFromStart, int id) {
        if (distancesFromStart[id].prevId == id) {
            List<Integer> result = new ArrayList<>();
            result.add(id);

            return result;
        }

        List<Integer> result = getPath(distancesFromStart, distancesFromStart[id].prevId);
        result.add(id);

        return result;
    }

    private static class Distance {

        final int prevId;
        final int weight;

        public Distance(int prevId, int weight) {
            this.prevId = prevId;
            this.weight = weight;
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

    private static class QueueElement {

        final int fromId;
        final int toId;
        final int weight;

        public QueueElement(int fromId, int toId, int weight) {
            this.fromId = fromId;
            this.toId = toId;
            this.weight = weight;
        }
    }
}
