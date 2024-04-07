import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main {

    /*
    1. 입력을 받는다.
        - 첫 줄에 집에 있는 물건 종류, 들 수 있는 가방의 최대 무게
        - 두번째 줄부터 물건의 무게, 만족도, 개수
    2. 최대 무게에서 최대의 만족도를 출력한다.


     */
    public static void main(String[] args) throws IOException {
        // 입력을 받는다.
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLine = reader.readLine().split(" ");
        int itemCount = Integer.parseInt(firstLine[0]);
        int maxWeight = Integer.parseInt(firstLine[1]);

        Item[] items = new Item[itemCount];

        for (int i = 0; i < items.length; i++) {
            String[] tokens = reader.readLine().split(" ");
            int weight = Integer.parseInt(tokens[0]);
            int satisfaction = Integer.parseInt(tokens[1]);
            int count = Integer.parseInt(tokens[2]);

            items[i] = new Item(weight, satisfaction, count);
        }
        reader.close();

        // 최대 무게에서 최대의 만족도가 나오도록 계산한다.
        int[] maxSatisfactions = new int[maxWeight + 1];

        for (Item item : items) {
            List<Integer> counts = getCounts(item.count);

            for (Integer count : counts) {
                int currentWeight = item.weight * count;
                int currentSatisfaction = item.satisfaction * count;

                for (int s = maxSatisfactions.length - 1; s >= currentWeight; s--) {
                    maxSatisfactions[s] = Math.max(
                            maxSatisfactions[s],
                            maxSatisfactions[s - currentWeight] + currentSatisfaction
                    );
                }
            }
        }

        // 최대 만족도를 출력한다.
        System.out.println(maxSatisfactions[maxWeight]);
    }

    private static List<Integer> getCounts(int count) {
        List<Integer> itemCounts = new ArrayList<>();
        int twoSquare = 1;
        int remainder = count;

        while (remainder > 0) {
            int current = Math.min(twoSquare, remainder);

            itemCounts.add(current);

            remainder -= twoSquare;
            twoSquare *= 2;
        }

        return itemCounts;
    }

    private static class Item {
        final int weight;
        final int satisfaction;
        final int count;

        public Item(int weight, int satisfaction, int count) {
            this.weight = weight;
            this.satisfaction = satisfaction;
            this.count = count;
        }
    }
}
