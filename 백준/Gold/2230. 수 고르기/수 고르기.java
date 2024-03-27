import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] firstLine = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int numberCount = firstLine[0];
        int differenceLimit = firstLine[1];

        int[] numbers = new int[numberCount];
        for (int i = 0; i < numberCount; i++) {
            numbers[i] = Integer.parseInt(reader.readLine());
        }

        Arrays.sort(numbers);

        int minDifference = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        while (left < numbers.length && right < numbers.length) {
            int difference = numbers[right] - numbers[left];
            if (difference < differenceLimit) {
                right++;
            } else {
                minDifference = Math.min(minDifference, difference);
                left++;
            }
        }

        System.out.println(minDifference);
    }
}
