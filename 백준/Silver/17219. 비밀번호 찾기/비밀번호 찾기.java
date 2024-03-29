import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] firstLine = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int siteCount = firstLine[0];
        int siteToFindCount = firstLine[1];

        Map<String, String> passwordMap = new HashMap<>();
        for (int i = 0; i < siteCount; i++) {
            String[] tokens = reader.readLine().split(" ");
            String siteName = tokens[0];
            String password = tokens[1];

            passwordMap.put(siteName, password);
        }

        String[] result = new String[siteToFindCount];

        for (int i = 0; i < siteToFindCount; i++) {
            String siteToFind = reader.readLine();

            String foundPassword = passwordMap.get(siteToFind);
            result[i] = foundPassword;
        }
        reader.close();

        System.out.print(String.join("\n", result));
    }
}
