import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int numberCount = Integer.parseInt(reader.readLine());
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < numberCount; i++) {
            int n = Integer.parseInt(reader.readLine());
            numbers.add(n);
        }
        reader.close();

        numbers.sort(Collections.reverseOrder());

        // 내림차순으로 1보다 큰 양수들을 곱한다.
        int result = 0;

        int leftIndex = 0;
        while (leftIndex + 1 < numbers.size() && numbers.get(leftIndex + 1) > 1) {
            result += numbers.get(leftIndex) * numbers.get(leftIndex + 1);
            leftIndex += 2;
        }

        // 1 또는 남은 양수들을 더한다.
        while (leftIndex < numbers.size() && numbers.get(leftIndex) > 0) {
            result += numbers.get(leftIndex);
            leftIndex++;
        }

        // 절대값이 큰 음수들을 곱한다.
        int rightIndex = numbers.size() - 1;
        while (rightIndex - 1 >= leftIndex && numbers.get(rightIndex - 1) < 0) {
            result += numbers.get(rightIndex) * numbers.get(rightIndex - 1);
            rightIndex -= 2;
        }

        // 음수가 남았고, 0도 남았다면 서로 곱하여 0을 반환한다.
        if (leftIndex < numbers.size() && numbers.get(leftIndex) != 0 &&
                rightIndex >= 0 && numbers.get(rightIndex) < 0) {
            result += numbers.get(rightIndex);
        }

        System.out.println(result);
    }
}
