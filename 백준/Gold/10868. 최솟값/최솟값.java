import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int arraySize = Integer.parseInt(stringTokenizer.nextToken());
        int queryCount = Integer.parseInt(stringTokenizer.nextToken());

        int[] numbers = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        RangeMinimumQuery rangeMinimumQuery = new RangeMinimumQuery(numbers);

        for (int i = 0; i < queryCount; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            int queryStartIndex = Integer.parseInt(stringTokenizer.nextToken());
            int queryEndIndex = Integer.parseInt(stringTokenizer.nextToken());

            int minResult = rangeMinimumQuery.findMinimumInRange(queryStartIndex, queryEndIndex);

            bufferedWriter.write(String.valueOf(minResult));
            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private static class RangeMinimumQuery {
        final int elementCount;
        final int[] segmentTree;

        public RangeMinimumQuery(int[] sourceArray) {
            this.elementCount = sourceArray.length;

            this.segmentTree = new int[elementCount * 4];
            if (elementCount > 0) {
                buildSegmentTree(sourceArray, 1, 1, elementCount);
            }
        }

        private int buildSegmentTree(int[] sourceArray, int nodeIndex, int rangeStart, int rangeEnd) {
            if (rangeStart == rangeEnd) {
                segmentTree[nodeIndex] = sourceArray[rangeStart - 1];
                return segmentTree[nodeIndex];
            }

            int midPoint =  rangeStart + (rangeEnd - rangeStart) / 2;

            int leftChildMin = buildSegmentTree(sourceArray, nodeIndex * 2, rangeStart, midPoint);
            int rightChildMin = buildSegmentTree(sourceArray, nodeIndex * 2 + 1, midPoint + 1, rangeEnd);

            segmentTree[nodeIndex] = Math.min(leftChildMin, rightChildMin);
            return segmentTree[nodeIndex];
        }

        private int findMinimum(int nodeIndex, int searchRangeStart, int searchRangeEnd, int queryRangeStart, int queryRangeEnd) {
            if (queryRangeEnd < searchRangeStart || searchRangeEnd < queryRangeStart) {
                return Integer.MAX_VALUE;
            }

            if (queryRangeStart <= searchRangeStart && searchRangeEnd <= queryRangeEnd) {
                return segmentTree[nodeIndex];
            }

            int midPoint =  searchRangeStart + (searchRangeEnd - searchRangeStart) / 2;

            int leftResult =  findMinimum(nodeIndex * 2, searchRangeStart, midPoint, queryRangeStart, queryRangeEnd);
            int rightResult =  findMinimum(nodeIndex * 2 + 1, midPoint + 1, searchRangeEnd, queryRangeStart, queryRangeEnd);

            return Math.min(leftResult, rightResult);
        }

        public int findMinimumInRange(int queryRangeStart, int queryRangeEnd) {
            return this.findMinimum(1, 1, elementCount, queryRangeStart, queryRangeEnd);
        }
    }
}