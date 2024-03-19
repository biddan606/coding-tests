import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static final int DISTANCE_MAX_VALUE = Integer.MAX_VALUE / 2;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // 지역의 개수, 수색 범위, 길의 개수를 입력 받는다.
        int[] initialSettings = readLineAsIntArray(reader);
        int areaCount = initialSettings[0];
        int searchRange = initialSettings[1];
        int pathCount = initialSettings[2];

        // 버텍스들의 아이템 개수를 입력 받는다.(index는 0부터 시작)
        int[] itemCountsPerArea = readLineAsIntArray(reader);

        int[][] distances = new int[areaCount][areaCount];
        for (int[] row : distances) {
            Arrays.fill(row, DISTANCE_MAX_VALUE);
        }

        for (int i = 0; i < pathCount; i++) {
            int[] pathInfo = readLineAsIntArray(reader);
            // 버텍스 index가 0부터 시작하여 입력 받은 버텍스 id에서 -1을 해준다.
            int from = pathInfo[0] - 1;
            int to = pathInfo[1] - 1;
            int weight = pathInfo[2];

            // 무방향 그래프
            distances[from][to] = Math.min(distances[from][to], weight);
            distances[to][from] = Math.min(distances[to][from], weight);
        }
        reader.close();

        // 자기 자신으로의 거리는 0으로 초기화합니다.
        for (int i = 1; i < areaCount; i++) {
            distances[i][i] = 0;
        }

        floydWarshall(distances, areaCount);

        int maxItemsFound = 0;
        for (int from = 0; from < itemCountsPerArea.length; from++) {
            int currentItemsFound = 0;
            for (int to = 0; to < itemCountsPerArea.length; to++) {
                if (distances[from][to] > searchRange) {
                    continue;
                }

                currentItemsFound += itemCountsPerArea[to];
            }

            maxItemsFound = Math.max(maxItemsFound, currentItemsFound);
        }

        System.out.println(maxItemsFound);
    }

    private static int[] readLineAsIntArray(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static void floydWarshall(int[][] distances, int vertexCount) {
        for (int mid = 0; mid < vertexCount; mid++) {
            for (int start = 0; start < vertexCount; start++) {
                for (int end = 0; end < vertexCount; end++) {
                    distances[start][end] =
                            Math.min(distances[start][end], distances[start][mid] + distances[mid][end]);
                }
            }
        }
    }
}
