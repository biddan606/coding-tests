import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int lines = Integer.parseInt(st.nextToken());

        int[] maxMemorization = new int[3];
        Arrays.fill(maxMemorization, 0);

        int[] minMemorization = new int[3];
        Arrays.fill(maxMemorization, 0);
        
        for (int i = 0; i < lines; i++) {
            int[] current = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int[] nextMaxMemorization = new int[3];
            nextMaxMemorization[0] = Math.max(maxMemorization[0], maxMemorization[1]) + current[0];
            nextMaxMemorization[1] = Math.max(Math.max(maxMemorization[0], maxMemorization[1]), maxMemorization[2]) + current[1];
            nextMaxMemorization[2] = Math.max(maxMemorization[1], maxMemorization[2]) + current[2];

            maxMemorization =  nextMaxMemorization;

            int[] nextMinMemorization = new int[3];
            nextMinMemorization[0] = Math.min(minMemorization[0], minMemorization[1]) + current[0];
            nextMinMemorization[1] = Math.min(Math.min(minMemorization[0], minMemorization[1]), minMemorization[2]) + current[1];
            nextMinMemorization[2] = Math.min(minMemorization[1], minMemorization[2]) + current[2];

            minMemorization =  nextMinMemorization;
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            max = Math.max(max, maxMemorization[i]);
            min = Math.min(min, minMemorization[i]);
        }

        System.out.printf("%d %d%n", max, min);
    }
}
