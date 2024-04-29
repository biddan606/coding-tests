import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static final int MIN_LENGTH = 0;
    private static final int MAX_LENGTH = 1_000_000_000;

    public static void main(String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] firstLine = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int length = firstLine[0];
        int required = firstLine[1];

        int[] numbers = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        reader.close();

        int left = MIN_LENGTH;
        int right = MAX_LENGTH + 1;
        while (left < right - 1) {
            int mid = (left + right) / 2;

            long sum = cut(numbers, mid);
            if (sum < required) {
                right = mid;
            } else {
                left = mid;
            }
        }

        System.out.println(left);
    }

    private static long cut(int[] numbers, int length) {
        long sum = 0;
        for (int n : numbers) {
            sum += Math.max(0, n - length);
        }

        return sum;
    }
}
