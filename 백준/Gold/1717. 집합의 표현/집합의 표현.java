import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] firstLine = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int numberCount = firstLine[0];
        int operationCount = firstLine[1];

        int[] numbers = new int[numberCount + 1];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i;
        }

        for (int i = 0; i < operationCount; i++) {
            int[] operationData = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int operationType = operationData[0];
            int number1 = operationData[1];
            int number2 = operationData[2];

            switch (operationType) {
                case 0:
                    merge(numbers, number1, number2);
                    break;
                case 1:
                    printMatchResult(numbers, number1, number2);
                    break;
            }
        }
        br.close();
    }

    private static void merge(int[] numbers, int number1, int number2) {
        int parent1 = find(numbers, number1);
        int parent2 = find(numbers, number2);

        if (parent1 != parent2) {
            numbers[parent1] = parent2;
        }
    }

    private static int find(int[] numbers, int number) {
        if (numbers[number] != number) {
            numbers[number] = find(numbers, numbers[number]);
        }
        return numbers[number];
    }

    private static void printMatchResult(int[] numbers, int number1, int number2) {
        String matchResult = "NO";
        if (find(numbers, number1) == find(numbers, number2)) {
            matchResult = "YES";
        }

        System.out.println(matchResult);
    }
}
