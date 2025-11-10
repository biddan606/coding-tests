import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int numberCount = Integer.parseInt(st.nextToken());
        int totalChangeCount = Integer.parseInt(st.nextToken());
        int totalSumOperationCount = Integer.parseInt(st.nextToken());

        long[] numbers = new long[numberCount];

        for (int i = 0; i < numberCount; i++) {
            numbers[i] = Long.parseLong(br.readLine());
        }

        SegmentTree segmentTree = new SegmentTree(numbers);

        int currentChangeCount = 0;
        int currentSumOperationCount = 0;

        while (currentChangeCount < totalChangeCount || currentSumOperationCount < totalSumOperationCount) {
            String[] command = br.readLine().split(" ");

            int operation = Integer.parseInt(command[0]);
            int number1 = Integer.parseInt(command[1]);
            long number2 = Long.parseLong(command[2]);

            if (operation == 1) {
                segmentTree.update(number1, number2);

                currentChangeCount++;
            } else {
                long result = segmentTree.query(number1, (int) number2);
                System.out.println(result);

                currentSumOperationCount++;
            }
        }
    }

    private static class SegmentTree {
        private long[] tree;
        private int size;

        public SegmentTree(long[] arr) {
            this.size = arr.length;

            this.tree = new long[size * 4];
            if (size > 0) {
                buildTree(arr, 1, 1, size);
            }
        }

        private Long buildTree(long[] arr, int node, int startInclusive, int endInclusive) {
            if (startInclusive == endInclusive) {
                tree[node] = arr[startInclusive - 1];
                return tree[node];
            }

            int mid = startInclusive + (endInclusive - startInclusive) / 2;

            long leftSum = buildTree(arr, node * 2,  startInclusive, mid);
            long rightSum = buildTree(arr, node * 2 + 1, mid + 1, endInclusive);

            tree[node] = leftSum + rightSum;
            return tree[node];
        }

        public void update(int number, long value) {
            updateNode(1, 1, size, number, value);
        }

        private void updateNode(int node, int startInclusive, int endInclusive, int number, long value) {
            if (number < startInclusive || endInclusive < number) {
                return;
            }

            if (startInclusive == endInclusive) {
                tree[node] = value;
                return;
            }

            int mid =  startInclusive + (endInclusive - startInclusive) / 2;

            updateNode(node * 2, startInclusive, mid, number, value);
            updateNode(node * 2 + 1, mid + 1, endInclusive, number, value);

            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }

        public long query(int startInclusive, int endInclusive) {
            return queryRange(1, 1, size, startInclusive, endInclusive);
        }

        private long queryRange(int node, int left, int right, int startInclusive, int endInclusive) {
            if (endInclusive < left || right < startInclusive) {
                return 0;
            }

            if (startInclusive <= left && right <= endInclusive) {
                return tree[node];
            }

            int mid =  left + (right - left) / 2;

            long leftSum = queryRange(node * 2, left, mid, startInclusive, endInclusive);
            long rightSum = queryRange(node * 2 + 1, mid + 1, right, startInclusive, endInclusive);
            return  leftSum + rightSum;
        }
    }
}
