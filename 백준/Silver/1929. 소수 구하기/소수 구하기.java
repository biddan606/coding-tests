import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        reader.close();

        int start = Integer.parseInt(input[0]);
        int end = Integer.parseInt(input[1]);

        boolean[] isPrime = new boolean[end + 1];
        Arrays.fill(isPrime, 2, isPrime.length, true);

        for (int i = 0; i * i < isPrime.length; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < isPrime.length; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = start; i <= end; i++) {
            if (isPrime[i]) {
                result.append(i).append('\n');
            }
        }

        System.out.println(result.toString());
    }
}
