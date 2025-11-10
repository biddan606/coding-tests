import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Rmq rmq = new Rmq(arr);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            int result = rmq.queryRange(start, end);

            bw.write(String.valueOf(result));
            bw.newLine();
        }

        br.close();
        bw.flush();
        bw.close();
    }

    private static class Rmq {
        final int size;
        final int[] tree;

        public Rmq(int[] arr) {
            this.size = arr.length;

            this.tree = new int[size * 4];
            if (size > 0) {
                buildTree(arr, 1, 1, size);
            }
        }

        private int buildTree(int[] arr, int node, int startInclusive, int endInclusive) {
            if (startInclusive == endInclusive) {
                tree[node] = arr[startInclusive - 1];
                return tree[node];
            }

            int mid =  startInclusive + (endInclusive - startInclusive) / 2;

            int left = buildTree(arr, node * 2, startInclusive, mid);
            int right = buildTree(arr, node * 2 + 1, mid + 1, endInclusive);

            tree[node] = Math.min(left, right);
            return tree[node];
        }

        public int query(int node, int left, int right, int startInclusive, int endInclusive) {
            if (endInclusive< left || right < startInclusive) {
                return Integer.MAX_VALUE;
            }

            if (startInclusive <= left && right <= endInclusive) {
                return tree[node];
            }

            int mid =  left + (right - left) / 2;

            int leftResult =  query(node * 2, left, mid, startInclusive, endInclusive);
            int rightResult =  query(node * 2 + 1, mid + 1, right, startInclusive, endInclusive);

            return Math.min(leftResult, rightResult);
        }

        public int queryRange(int startInclusive, int endInclusive) {
            return this.query(1, 1, size, startInclusive, endInclusive);
        }
    }
}
