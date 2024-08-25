import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int testcaseCount = Integer.parseInt(reader.readLine());
        int[] result = new int[testcaseCount];
        for (int i = 0; i < testcaseCount; i++) {
            int fileSizeCount = Integer.parseInt(reader.readLine());
            int[] fileSizes = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            result[i] = calculateMinTotalMergeCost(fileSizes);
        }
        reader.close();

        for (int r : result) {
            System.out.println(r);
        }
    }

    private static int calculateMinTotalMergeCost(int[] fileSizes) {
        // 누적합
        int[] cumulativeSum = new int[fileSizes.length + 1];

        for (int i = 0; i < fileSizes.length; i++) {
            cumulativeSum[i + 1] = cumulativeSum[i] + fileSizes[i];
        }

        // 최소 병합 비용 초기화
        int[][] minMergerCost = new int[fileSizes.length][fileSizes.length + 1];
        for (int i = 0; i < fileSizes.length; i++) {
            Arrays.fill(minMergerCost[i], Integer.MAX_VALUE);
            minMergerCost[i][i + 1] = 0;
        }

        // 합칠 파일의 개수
        for (int mergeSize = 2; mergeSize <= fileSizes.length; mergeSize++) {
            // 합칠 파일의 시작 지점
            for (int start = 0; start + mergeSize <= fileSizes.length; start++) {
                int mergedFileSize = cumulativeSum[start + mergeSize] - cumulativeSum[start];

                // 합칠 파일의 분기 지점 [시작][분기] + [분기][시작 + 합칠 파일 개수]
                for (int endExclusive = start + 1; endExclusive < start + mergeSize; endExclusive++) {
                    minMergerCost[start][start + mergeSize] =
                            Math.min(minMergerCost[start][start + mergeSize],
                                    minMergerCost[start][endExclusive] + minMergerCost[endExclusive][start + mergeSize] +
                                            mergedFileSize);
                }
            }
        }

        return minMergerCost[0][fileSizes.length];
    }
}
