import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] firstLine = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int numberOfCables = firstLine[0];
        int numberOfNeeded = firstLine[1];

        int[] cables = new int[numberOfCables];
        for (int i = 0; i < cables.length; i++) {
            cables[i] = Integer.parseInt(reader.readLine());
        }
        reader.close();

        long left = MIN_LENGTH;
        long right = MAX_LENGTH + 1L;
        while (left < right - 1) {
            long mid = (left + right) / 2;

            long numberOfCut = cut(cables, mid);
            if (numberOfCut < numberOfNeeded) {
                right = mid;
            } else {
                left = mid;
            }
        }

        System.out.println(left);
    }

    private static long cut(int[] cables, long length) {
        long count = 0;
        for (int cable : cables) {
            count += cable / length;
        }

        return count;
    }
}
