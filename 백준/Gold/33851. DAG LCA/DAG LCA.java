import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static final int MAX_DISTANCE = Integer.MAX_VALUE / 2;

    static int vertexCount;
    static int edgeCount;
    static int queryCount;
    static List<List<Integer>> adjacencyList;
    static int[][] distancesByVertex;
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        readInput();
        link();
        query();

        br.close();
    }

    private static void query() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < queryCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            int ans = MAX_DISTANCE;
            for (int w = 1; w <= vertexCount; w++) {
                if (distancesByVertex[w][u] < MAX_DISTANCE && distancesByVertex[w][v] < MAX_DISTANCE) {
                    ans = Math.min(ans, Math.max(distancesByVertex[w][u], distancesByVertex[w][v]));
                }
            }

            sb.append(ans >= MAX_DISTANCE ? -1 : ans).append('\n');
        }
        System.out.print(sb);
    }

    private static void link() {
        for (int start = 1; start <= vertexCount; start++) {
            Queue<Integer> queue = new LinkedList<>();
            queue.add(start);

            while (!queue.isEmpty()) {
                int cur = queue.poll();
                for (int next : adjacencyList.get(cur)) {
                    if (distancesByVertex[start][next] == MAX_DISTANCE) {
                        distancesByVertex[start][next] = distancesByVertex[start][cur] + 1;
                        queue.add(next);
                    }
                }
            }
        }
    }

    private static void readInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        vertexCount = Integer.parseInt(st.nextToken());
        edgeCount = Integer.parseInt(st.nextToken());
        queryCount = Integer.parseInt(st.nextToken());

        adjacencyList = new ArrayList<>();
        for (int i = 0; i <= vertexCount; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        distancesByVertex = new int[vertexCount + 1][vertexCount + 1];
        for (int vertexId = 1; vertexId <= vertexCount; vertexId++) {
            Arrays.fill(distancesByVertex[vertexId], MAX_DISTANCE);
            distancesByVertex[vertexId][vertexId] = 0;
        }

        for (int i = 0; i < edgeCount; i++) {
            st = new StringTokenizer(br.readLine());
            int vertexId1 = Integer.parseInt(st.nextToken());
            int vertexId2 = Integer.parseInt(st.nextToken());

            adjacencyList.get(vertexId1).add(vertexId2);
        }
    }
}