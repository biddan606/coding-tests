import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cityCount = Integer.parseInt(br.readLine());
        int[][] cityDistances = new int[cityCount][cityCount];
        for (int i = 0; i < cityCount; i++) {
            Arrays.fill(cityDistances[i], cityCount * 100_000);
            cityDistances[i][i] = 0;
        }

        int edgeCount = Integer.parseInt(br.readLine());
        Map<Integer, List<Edge>> edges = new HashMap<>();
        for (int i = 0; i < edgeCount; i++) {
            int[] token = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int from = token[0] - 1;
            int to = token[1] - 1;
            int weight = token[2];

            edges.computeIfAbsent(from, k -> new ArrayList<>())
                    .add(new Edge(to, weight));
        }

        int[] target = Arrays.stream(br.readLine().split(" "))
                .mapToInt(s -> Integer.parseInt(s) - 1)
                .toArray();
        br.close();

        int start = target[0];
        int end = target[1];

        PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.distance));
        pq.offer(new Element(start, 0));

        boolean[] visited = new boolean[cityCount];

        int result = Integer.MAX_VALUE;

        while (!pq.isEmpty()) {
            Element element = pq.poll();
            if (element.current == end) {
                result = element.distance;
                break;
            }

            if (visited[element.current]) {
                continue;
            }
            visited[element.current] = true;

            edges.getOrDefault(element.current, Collections.emptyList())
                    .forEach(e -> pq.offer(new Element(e.to, e.weight + element.distance)));
        }

        System.out.println(result);
    }
    private static class Edge {
        final int to;
        final int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    private static class Element {
        final int current;
        final int distance;

        public Element(int current, int distance) {
            this.current = current;
            this.distance = distance;
        }
    }
}
