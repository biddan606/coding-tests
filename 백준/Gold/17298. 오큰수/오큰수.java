import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

public class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // 입력을 받는다.
        int numberCount = Integer.parseInt(reader.readLine());
        String[] tokens = reader.readLine().split(" ");

        int[] numbers = new int[numberCount];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(tokens[i]);
        }
        reader.close();

        // 오큰수를 구한다.
        int[] result = new int[numbers.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = numbers.length - 1; i >= 0; i--) {
            int currentNumber = numbers[i];
            while (!stack.isEmpty() && stack.peek() <= currentNumber) {
                stack.pop();
            }

            result[i] = -1;
            if (!stack.isEmpty()) {
                result[i] = stack.peek();
            }

            stack.push(currentNumber);
        }

        // 결과를 출력한다.
        String output = Arrays.stream(result)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));
        System.out.println(output);
    }
}
