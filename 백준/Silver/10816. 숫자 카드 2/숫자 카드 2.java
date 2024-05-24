import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int userCardCount = Integer.parseInt(reader.readLine());
        int[] userCards = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int cardCount = Integer.parseInt(reader.readLine());
        int[] cards = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        reader.close();

        // 사용자 카드의 빈도수 계산
        Map<Integer, Integer> cardFrequency = new HashMap<>();
        for (int userCard : userCards) {
            cardFrequency.put(userCard, cardFrequency.getOrDefault(userCard, 0) + 1);
        }

        StringBuilder output = new StringBuilder();
        for (int card : cards) {
            output.append(cardFrequency.getOrDefault(card, 0) + " ");
        }

        System.out.println(output.toString().trim());
    }
}
