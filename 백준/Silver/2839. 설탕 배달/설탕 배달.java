import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
# 문제
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int target = Integer.parseInt(reader.readLine());
        reader.close();

        int fiveCount = target / 5;
        while (fiveCount >= 0) {
            if ((target - fiveCount * 5) % 3 == 0) {
                break;
            }

            fiveCount--;
        }

        if (fiveCount < 0) {
            System.out.println(-1);
        } else {
            System.out.println(fiveCount + (target - fiveCount * 5) / 3);
        }
    }
}
