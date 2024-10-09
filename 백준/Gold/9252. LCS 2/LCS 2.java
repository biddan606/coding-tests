import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] source = br.readLine().toCharArray();
        char[] subString = br.readLine().toCharArray();
        br.close();

        String[][] subSequences = new String[source.length + 1][subString.length + 1];
        for (int row = 0; row < subSequences.length; row++) {
            subSequences[row][0] = "";
        }
        Arrays.fill(subSequences[0], "");

        String maxLengthSubSequence = "";
        for (int row = 1; row < subSequences.length; row++) {
            char sourceChar = source[row - 1];

            for (int col = 1; col < subSequences[row].length; col++) {
                char subChar = subString[col - 1];
                if (sourceChar == subChar
                        && subSequences[row - 1][col - 1].length() + 1 > subSequences[row - 1][col].length()
                        && subSequences[row - 1][col - 1].length() + 1 > subSequences[row][col - 1].length() ) {
                    subSequences[row][col] = subSequences[row - 1][col - 1] + subChar;
                } else if (subSequences[row - 1][col].length() > subSequences[row][col - 1].length()){
                    subSequences[row][col] = subSequences[row - 1][col];
                } else {
                    subSequences[row][col] = subSequences[row][col - 1];
                }

                if (subSequences[row][col].length() > maxLengthSubSequence.length()) {
                    maxLengthSubSequence = subSequences[row][col];
                }
            }
        }

        System.out.println(maxLengthSubSequence.length());
        System.out.println(maxLengthSubSequence);
    }
}
