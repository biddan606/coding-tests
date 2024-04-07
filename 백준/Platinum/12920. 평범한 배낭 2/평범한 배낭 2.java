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

    일반적인 DP로 계산하면, 시간 복잡도 = O(최대 아이템 개수 * 최대 무게 * 최대 물건의 개수) = O(100 * 10_000 * 10_000) 로
    1억을 초과한다.(특정 값을 계산을 줄여줘야 한다)
    물건의 개수를 리스트로 수정하여 계산하면 logK로 줄일 수 있다.
    물건의 개수가 k개일 때, 특정 리스트로 수정할 수 있다.
    1 -> {1},
    2 -> {1, 1},
    3 -> {1, 2},
    4 -> {1, 2, 1}
    ... 7 -> {1, 2, 4}
    8 -> {1, 2, 4, 1}
    ... 10_000 -> {1, 2, 4, 8, 16, 32, ...}
    위와 같이 수정할 경우, [0]로 최대 만족도를 검사하게 되면 개수 0, 1일 때를 확인할 수 있다.
    [1]은 2, 3일 때를 확인할 수 있다. 앞에서 [0]이 0이라면 2, [1]이라면 3
    [2]는 4, 5, 6, 7을 확인할 수 있다. [0] + [1]이 0이라면 4, [0] + [1]이 1이라면 5...
    위와 같이 모든 개수를 검사할 수 있고, 2의 승수로 증가하니 logK의 개수만 검사하면 된다.
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
            twoSquare <<= 1;
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
