import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] dwarfCandidates = new int[9];
        for (int i = 0; i < 9; i++) {
            dwarfCandidates[i] = Integer.parseInt(reader.readLine());
        }
        reader.close();

        int totalSum = Arrays.stream(dwarfCandidates).sum();

        for (int firstOut = 0; firstOut < dwarfCandidates.length; firstOut++) {
            for (int secondOut = firstOut + 1; secondOut < dwarfCandidates.length; secondOut++) {
                if (totalSum - dwarfCandidates[firstOut] - dwarfCandidates[secondOut] == 100) {
                    dwarfCandidates[firstOut] = 0;
                    dwarfCandidates[secondOut] = 0;
                    break;
                }
            }

            if (dwarfCandidates[firstOut] == 0) {
                break;
            }
        }

        Arrays.sort(dwarfCandidates);
        Arrays.stream(dwarfCandidates)
                .skip(2)
                .forEach(System.out::println);
    }
}
