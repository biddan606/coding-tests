import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cityCount = Integer.parseInt(br.readLine());
        int[][] cityDistances = new int[cityCount][cityCount];
        for (int i = 0; i < cityCount; i++) {
            Arrays.fill(cityDistances[i], cityCount * 100_000);
            cityDistances[i][i] = 0;
        }

        int edgeCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < edgeCount; i++) {
            int[] token = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int from = token[0] - 1;
            int to = token[1] - 1;
            int weight = token[2];

            cityDistances[from][to] = Math.min(weight, cityDistances[from][to]);
        }

        int[] target = Arrays.stream(br.readLine().split(" "))
                .mapToInt(s -> Integer.parseInt(s) - 1)
                .toArray();
        br.close();

        for (int j = 0; j < cityCount; j++) {
            for (int i = 0; i < cityCount; i++) {
                for (int k = 0; k < cityCount; k++) {
                    cityDistances[i][k] = Math.min(cityDistances[i][k],
                            cityDistances[i][j] + cityDistances[j][k]);
                }
            }
        }

        System.out.println(cityDistances[target[0]][target[1]]);
    }
}
