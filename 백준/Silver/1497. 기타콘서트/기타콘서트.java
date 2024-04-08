import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLine = reader.readLine().split(" ");
        int guitarCount = Integer.parseInt(firstLine[0]);

        long[] songsAvailabilityBit = new long[guitarCount];
        for (int g = 0; g < songsAvailabilityBit.length; g++) {
            String[] tokens = reader.readLine().split(" ");
            String songsYesOrNo = tokens[1];

            for (int s = 0; s < songsYesOrNo.length(); s++) {
                char yesOrNo = songsYesOrNo.charAt(s);
                if (yesOrNo == 'Y') {
                    songsAvailabilityBit[g] |= 1L << s;
                }

            }
        }
        reader.close();

        int maxSongs = 0;
        int minGuitars = -1;

        for (int selectedGuitarsBit = 0; selectedGuitarsBit < (1 << guitarCount); selectedGuitarsBit++) {
            long combinedSongsBit = 0;

            for (int g = 0; g < guitarCount; g++) {
                if (((1 << g) & selectedGuitarsBit) == 0) {
                    continue;
                }

                combinedSongsBit |= songsAvailabilityBit[g];
            }

            int availableSongs = Long.bitCount(combinedSongsBit);
            int selectedGuitars = Integer.bitCount(selectedGuitarsBit);

            if (availableSongs > maxSongs ||
                    (availableSongs == maxSongs && minGuitars > selectedGuitars)) {
                maxSongs = availableSongs;
                minGuitars = selectedGuitars;
            }
        }

        System.out.println(minGuitars);
    }
}
