import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        in.nextToken();
        int T = (int) in.nval;

        while (T-- > 0) {
            in.nextToken();
            int n = (int) in.nval;

            int[] v0 = new int[n], v1 = new int[n];
            for (int i = 0; i < n; i++) { in.nextToken(); v0[i] = (int) in.nval; }
            for (int i = 0; i < n; i++) { in.nextToken(); v1[i] = (int) in.nval; }

            int[] d0 = new int[n], d1 = new int[n];

            // col 0
            d0[0] = v0[0];
            d1[0] = v1[0];

            if (n >= 2) {
                // col 1
                d0[1] = d1[0] + v0[1];
                d1[1] = d0[0] + v1[1];

                // col 2 ~
                for (int c = 2; c < n; c++) {
                    d0[c] = Math.max(Math.max(d0[c - 2], d1[c - 2]), d1[c - 1]) + v0[c];
                    d1[c] = Math.max(Math.max(d0[c - 2], d1[c - 2]), d0[c - 1]) + v1[c];
                }
            }

            int ans = Math.max(d0[n - 1], d1[n - 1]);
            if (n >= 2) {
                ans = Math.max(ans, Math.max(d0[n - 2], d1[n - 2]));
            }

            out.write(Integer.toString(ans));
            out.newLine();
        }

        out.flush();
        out.close();
    }
}