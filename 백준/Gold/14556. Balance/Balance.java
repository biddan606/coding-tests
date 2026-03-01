import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        reader.close();

        long dp = 1;
        int multiplier = 1;
        for (int i = 1; i < N; i++) {
            multiplier += 2;
            dp *= multiplier;
            dp = dp % 1_000_000_009L;
        }

        System.out.println(dp);
    }
}
