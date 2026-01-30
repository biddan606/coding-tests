import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main {

    private static final int MAX_VALUE = 9;

    public static void main(String args[]) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nth = Integer.parseInt(reader.readLine());

        reader.close();

        List<Long> combinations = new ArrayList<>();

        for (int n = 1; n <= 0b11_1111_1111; n++) {
            long current = 0;
            for (int digit = MAX_VALUE; digit >= 0; digit--) {
                if ((n & (1 << digit)) != 0) {
                    current = current * 10 + digit;
                }
            }

            combinations.add(current);
        }

        Long result = combinations.stream()
                .sorted()
                .skip(nth)
                .findFirst()
                .orElse(-1L);

        System.out.println(result);
    }
}
