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

        int[] maxValues = new int[maxWeight + 1];
        for (int w = knapsacks[0].weight; w < maxValues.length; w++) {
            maxValues[w] = knapsacks[0].value;
        }

        for (int i = 1; i < knapsacks.length; i++) {
            Knapsack currentKnapsack = knapsacks[i];

            // 0 -> maxValues.length - 1 순으로 비교할 경우, 현재 배낭이 누적될 수 있음
            for (int w = maxValues.length - 1; w >= currentKnapsack.weight; w--) {
                maxValues[w] = Math.max(maxValues[w], maxValues[w - currentKnapsack.weight] + currentKnapsack.value);
            }
        }

        System.out.println(maxValues[maxWeight]);
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
