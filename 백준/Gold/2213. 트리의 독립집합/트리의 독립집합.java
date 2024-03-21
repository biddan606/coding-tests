import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {


    private static final int NOT_ATTENDING = 0;
    private static final int ATTENDING = 1;

    private static final int NON_EXISTENT_ID = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // 정점의 수를 입력 받는다.
        int nodeCount = Integer.parseInt(reader.readLine());

        // 가중치를 입력 받는다.
        int[] weights = readLineAsIntArray(reader);

        // 노드를 생성한다.
        Node[] nodes = Arrays.stream(weights)
                .mapToObj(Node::new)
                .toArray(Node[]::new);

        // 간선을 입력 받는다.
        for (int i = 0; i < nodeCount - 1; i++) {
            int[] tokens = readLineAsIntArray(reader);
            int fromId = tokens[0] - 1;
            int toId = tokens[1] - 1;

            nodes[fromId].linkedIds.add(toId);
            nodes[toId].linkedIds.add(fromId);
        }
        reader.close();

        // 입력이 1개일 경우
        if (nodeCount == 1) {
            System.out.println(nodes[0].weight);
            System.out.println("1");
            return;
        }

        int[][] dp = new int[nodeCount][2];
        int startId = 0;
        treeDp(nodes, dp, startId, NON_EXISTENT_ID);

        int resultCost = Math.max(dp[0][NOT_ATTENDING], dp[0][ATTENDING]);

        List<Integer> resultPaths = new ArrayList<>();
        boolean included = dp[startId][NOT_ATTENDING] < dp[startId][ATTENDING];

        traceOptimalPath(nodes, dp, startId, NON_EXISTENT_ID, included, resultPaths);
        resultPaths.sort(Integer::compare);

        // 결과를 출력한다.
        System.out.println(resultCost);
        System.out.println(resultPaths.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" ")));
    }

    private static int[] readLineAsIntArray(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static void treeDp(Node[] nodes, int[][] dp, int currentId, int prevId) {
        Node node = nodes[currentId];
        dp[currentId][NOT_ATTENDING] = 0;
        dp[currentId][ATTENDING] = node.weight;

        if (node.isLeaf(prevId)) {
            return;
        }

        for (int nextId : node.linkedIds) {
            if (nextId == prevId) {
                continue;
            }
            treeDp(nodes, dp, nextId, currentId);

            dp[currentId][NOT_ATTENDING] += Math.max(dp[nextId][NOT_ATTENDING], dp[nextId][ATTENDING]);
            dp[currentId][ATTENDING] += dp[nextId][NOT_ATTENDING];
        }
    }

    private static void traceOptimalPath(Node[] nodes, int[][] dp, int currentId, int prevId, boolean included,
            List<Integer> paths) {
        Node node = nodes[currentId];

        if (included) {
            // 입력 단게에서 아이디 값을 -1하여 받아 결과 출력시 +1 해주어야 한다.
            paths.add(currentId + 1);
        }

        if (node.isLeaf(prevId)) {
            return;
        }

        for (int nextId : node.linkedIds) {
            if (nextId == prevId) {
                continue;
            }

            boolean nextIncluded = false;
            if (!included && dp[nextId][NOT_ATTENDING] < dp[nextId][ATTENDING]) {
                nextIncluded = true;
            }

            traceOptimalPath(nodes, dp, nextId, currentId, nextIncluded, paths);
        }
    }

    private static class Node {

        final int weight;
        final List<Integer> linkedIds = new ArrayList<>();

        public Node(int weight) {
            this.weight = weight;
        }

        public boolean isLeaf(int prevId) {
            return linkedIds.size() == 1 && linkedIds.get(0) == prevId;
        }
    }
}
