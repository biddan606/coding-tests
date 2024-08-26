import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int numberCount = Integer.parseInt(reader.readLine());

        List<Integer> positives = new ArrayList<>();
        List<Integer> negatives = new ArrayList<>();
        boolean zeroPresence = false;

        for (int i = 0; i < numberCount; i++) {
            int n = Integer.parseInt(reader.readLine());
            if (n > 0) {
                positives.add(n);
            } else if (n < 0) {
                negatives.add(n);
            } else {
                zeroPresence = true;
            }
        }
        reader.close();

        positives.sort(Comparator.reverseOrder());
        negatives.sort(Comparator.naturalOrder());

        int result = 0;

        int positivesIndex = 0;
        while (positivesIndex + 1 < positives.size() && positives.get(positivesIndex + 1) > 1) {
            result += positives.get(positivesIndex) * positives.get(positivesIndex + 1);
            positivesIndex += 2;
        }

        while (positivesIndex < positives.size()) {
            result += positives.get(positivesIndex);
            positivesIndex++;
        }

        int negativesIndex = 0;
        while (negativesIndex + 1 < negatives.size()) {
            result += negatives.get(negativesIndex) * negatives.get(negativesIndex + 1);
            negativesIndex += 2;
        }

        if (!zeroPresence && negativesIndex < negatives.size()) {
            result += negatives.get(negativesIndex);
        }

        System.out.println(result);
    }
}
