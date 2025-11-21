import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static int max = Integer.MIN_VALUE;
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int numbersSize = Integer.parseInt(reader.readLine());

        int[] numbers = new int[numbersSize];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < numbersSize; i++) {
            numbers[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int[] operatorCounts = new int[4];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < operatorCounts.length; i++) {
            operatorCounts[i] = Integer.parseInt(tokenizer.nextToken());
        }

        reader.close();

        dfs(numbers, 1, operatorCounts, numbers[0]);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        writer.write(max + "\n" + min);
        writer.flush();
        writer.close();
    }

    private static void dfs(int[] numbers, int index, int[] operatorCounts, int accumulated) {
        if (numbers.length == index) {
            max = Math.max(max, accumulated);
            min = Math.min(min, accumulated);
            return;
        }

        int number = numbers[index];

        for (int i = 0; i < operatorCounts.length; i++) {
            if (operatorCounts[i] == 0) {
                continue;
            }

            int nextAccumulated = 0;

            switch (i) {
                case 0:
                    nextAccumulated = accumulated + number;
                    break;
                case 1:
                    nextAccumulated = accumulated - number;
                    break;
                case 2:
                    nextAccumulated = accumulated * number;
                    break;
                case 3:
                    nextAccumulated = accumulated / number;
                    break;
                default:
                    break;
            }

            operatorCounts[i]--;

            dfs(numbers, index + 1, operatorCounts, nextAccumulated);

            operatorCounts[i]++;
        }
    }
}
