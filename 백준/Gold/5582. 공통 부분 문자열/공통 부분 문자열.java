import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] source = br.readLine().toCharArray();
        char[] subString = br.readLine().toCharArray();
        br.close();

        int[][] lengths = new int[source.length + 1][subString.length + 1];
        for (int row = 0; row < source.length; row++) {
            char sourceChar = source[row];

            for (int col = 0; col < subString.length; col++) {
                char subStringChar = subString[col];

                if (sourceChar == subStringChar) {
                    lengths[row + 1][col + 1] = lengths[row][col] + 1;
                }
            }
        }


        int maxLengthOfSubString = 0;
        for (int row = 1; row < lengths.length; row++) {
            for (int col = 1; col < lengths[row].length; col++) {
                maxLengthOfSubString = Math.max(maxLengthOfSubString, lengths[row][col]);
            }
        }

        System.out.println(maxLengthOfSubString);
    }
}
