import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLine = reader.readLine().split(" ");

        int knapsackCount = Integer.parseInt(firstLine[0]);
        int maxWeight = Integer.parseInt(firstLine[1]);

        Knapsack[] knapsacks = new Knapsack[knapsackCount];
        for (int i = 0; i < knapsacks.length; i++) {
            String[] tokens = reader.readLine().split(" ");
            int weight = Integer.parseInt(tokens[0]);
            int value = Integer.parseInt(tokens[1]);

            knapsacks[i] = new Knapsack(weight, value);
        }
        reader.close();

        int[][] dp = new int[knapsackCount][maxWeight + 1];
        for (int w = knapsacks[0].weight; w < dp[0].length; w++) {
            dp[0][w] = knapsacks[0].value;
        }

        for (int i = 1; i < dp.length; i++) {
            Knapsack currentKnapsack = knapsacks[i];

            for (int w = 0; w < dp[i].length; w++) {
                if (currentKnapsack.weight > w) {
                    dp[i][w] = dp[i - 1][w];
                    continue;
                }

                dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - currentKnapsack.weight] + currentKnapsack.value);
            }
        }
        
        System.out.println(dp[knapsackCount - 1][maxWeight]);
    }

    private static class Knapsack {

        final int weight;
        final int value;

        public Knapsack(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}
