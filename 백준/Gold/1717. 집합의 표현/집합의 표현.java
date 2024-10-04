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

        int[] parents = new int[numberCount + 1];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < operationCount; i++) {
            int[] operationData = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int operationType = operationData[0];
            int number1 = operationData[1];
            int number2 = operationData[2];

            switch (operationType) {
                case 0:
                    merge(parents, number1, number2);
                    break;
                case 1:
                    String matchResult = getMatchResult(parents, number1, number2);
                    sb.append(matchResult)
                            .append("\n");
                    break;
            }
        }
        br.close();

        System.out.println(sb);
    }

    private static void merge(int[] parents, int number1, int number2) {
        int parent1 = find(parents, number1);
        int parent2 = find(parents, number2);

        if (parent1 != parent2) {
            parents[parent2] = parent1;
        }
    }

    private static int find(int[] parents, int number) {
        if (parents[number] != number) {
            parents[number] = find(parents, parents[number]);
        }
        return parents[number];
    }

    private static String getMatchResult(int[] parents, int number1, int number2) {
        if (find(parents, number1) == find(parents, number2)) {
            return  "YES";
        }
        return "NO";
    }
}
