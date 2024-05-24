import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] firstLine = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int houseCount = firstLine[0];
        int routerCount = firstLine[1];

        int[] houseLocation = new int[houseCount];
        for (int i = 0; i < houseLocation.length; i++) {
            houseLocation[i] = Integer.parseInt(reader.readLine());
        }

        Arrays.sort(houseLocation);

        int low = 1;
        int high = houseLocation[houseLocation.length - 1] - houseLocation[0] + 1;
        while (low < high) {
            int mid = (low + high) / 2;

            if (install(houseLocation, mid) >= routerCount) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        System.out.println(low - 1);
    }

    private static int install(int[] location, int installationDistance) {
        int count = 1;
        int lastInstallationLocation = location[0];

        for (int i = 1; i < location.length; i++) {
            if (location[i] - lastInstallationLocation >= installationDistance) {
                lastInstallationLocation = location[i];
                count++;
            }
        }

        return count;
    }
}
