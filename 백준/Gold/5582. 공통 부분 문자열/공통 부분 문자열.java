import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] source = br.readLine().toCharArray();
        char[] subString = br.readLine().toCharArray();
        br.close();

        int[][] lengths = new int[source.length][subString.length];
        for (int row = 0; row < source.length; row++) {
            char sourceChar = source[row];

            for (int col = 0; col < subString.length; col++) {
                char subStringChar = subString[col];
                if (sourceChar != subStringChar) {
                    continue;
                }

                if (row == 0 || col == 0) {
                    lengths[row][col] = 1;
                } else {
                    lengths[row][col] += lengths[row - 1][col - 1] + 1;
                }
            }
        }


        int maxLengthOfSubString = 0;
        for (int row = 0; row < source.length; row++) {
            for (int col = 0; col < subString.length; col++) {
                maxLengthOfSubString = Math.max(maxLengthOfSubString, lengths[row][col]);
            }
        }

        System.out.println(maxLengthOfSubString);
    }
}
