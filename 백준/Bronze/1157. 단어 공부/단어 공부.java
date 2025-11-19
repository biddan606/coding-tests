import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String str = reader.readLine();
        str = str.toUpperCase();
        reader.close();

        int[] alphabets = new  int[26];

        for (char c : str.toCharArray()) {
            alphabets[c - 'A']++;
        }

        char result = ' ';
        int max = 0;

        for (int i = 0; i < 26; i++) {
            if (max < alphabets[i]) {
                max = alphabets[i];
                result = (char) ('A' + i);
            } else if (max == alphabets[i]) {
                if (result != ' ') {
                    result = '?';
                }
            }
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        writer.write(result);
        writer.flush();
        writer.close();
    }
}
