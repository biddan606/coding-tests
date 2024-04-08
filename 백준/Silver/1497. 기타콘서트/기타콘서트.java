import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLine = reader.readLine().split(" ");
        int guitarCount = Integer.parseInt(firstLine[0]);

        long[] availableSongsBit = new long[guitarCount];
        for (int g = 0; g < availableSongsBit.length; g++) {
            String[] tokens = reader.readLine().split(" ");
            String songsYesOrNo = tokens[1];

            for (int s = 0; s < songsYesOrNo.length(); s++) {
                char yesOrNo = songsYesOrNo.charAt(s);
                if (yesOrNo == 'N') {
                    continue;
                }

                availableSongsBit[g] |= 1L << s;
            }
        }
        reader.close();

        int maxAvailableSongs = 0;
        int minGuitars = -1;

        for (int guitarsBit = 0; guitarsBit < (1 << guitarCount); guitarsBit++) {
            long sumOfAvailableSongsBit = 0;

            for (int g = 0; g < guitarCount; g++) {
                if (((1 << g) & guitarsBit) == 0) {
                    continue;
                }

                sumOfAvailableSongsBit |= availableSongsBit[g];
            }

            int availableSongs = countBits(sumOfAvailableSongsBit);
            int selectedGuitars = countBits(guitarsBit);

            if (availableSongs > maxAvailableSongs) {
                maxAvailableSongs = availableSongs;
                minGuitars = selectedGuitars;
            } else if (availableSongs == maxAvailableSongs && minGuitars > selectedGuitars) {
                maxAvailableSongs = availableSongs;
                minGuitars = selectedGuitars;
            }
        }

        System.out.println(minGuitars);
    }

    private static int countBits(long bits) {
        int count = 0;

        while (bits != 0) {
            if (((bits & 1) != 0)) {
                count++;
            }
            bits >>= 1;
        }

        return count;
    }
}
