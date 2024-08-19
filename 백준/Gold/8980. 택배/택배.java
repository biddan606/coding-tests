import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLine = reader.readLine().split(" ");
        int villagesSize = Integer.parseInt(firstLine[0]);
        int totalCapacity = Integer.parseInt(firstLine[1]);

        int[][] villages = new int[villagesSize + 1][villagesSize + 1];

        int lineCount = Integer.parseInt(reader.readLine());
        for (int i = 0; i < lineCount; i++) {
            String[] token = reader.readLine().split(" ");
            int fromVillage = Integer.parseInt(token[0]);
            int toVillage = Integer.parseInt(token[1]);
            int capacityToSend = Integer.parseInt(token[2]);

            villages[fromVillage][toVillage] = capacityToSend;
        }
        reader.close();

        int[] savedCapacity = new int[villagesSize + 1];
        int sentCapacity = 0;
        for (int from = 1; from <= villagesSize; from++) {
            sentCapacity += savedCapacity[from];

            for (int to = from + 1; to <= villagesSize; to++) {
                savedCapacity[to] += villages[from][to];
            }

            int remainingCapacity = totalCapacity;
            for (int to = from + 1; to <= villagesSize; to++) {
                savedCapacity[to] = Math.min(savedCapacity[to], remainingCapacity);
                remainingCapacity -= savedCapacity[to];
            }
        }

        System.out.println(sentCapacity);
    }
}
