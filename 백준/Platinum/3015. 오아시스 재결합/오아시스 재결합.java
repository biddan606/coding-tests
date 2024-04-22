import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Main {

    public static void main(String[] args) throws IOException {
        // 입력 받는다.
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int numberOfPeople = Integer.parseInt(reader.readLine());

        int[] heights = new int[numberOfPeople];
        for (int i = 0; i < numberOfPeople; i++) {
            heights[i] = Integer.parseInt(reader.readLine());
        }
        int[] weights = new int[numberOfPeople];
        Arrays.fill(weights, 1);

        long totalPairs = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = heights.length - 1; i >= 0; i--) {
            int currentPairs = 0;
            int currentHeight = heights[i];

            while (!stack.isEmpty() && heights[stack.peek()] <= currentHeight) {
                currentPairs += weights[stack.peek()];

                if (heights[stack.peek()] == currentHeight) {
                    weights[i] += weights[stack.peek()];
                }

                stack.pop();
            }

            if (!stack.isEmpty()) {
                currentPairs++;
            }

            totalPairs += currentPairs;
            stack.push(i);
        }

        // 결과를 출력한다.
        System.out.println(totalPairs);
    }
}
