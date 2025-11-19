import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String str = reader.readLine();
        reader.close();

        int stringCount = 0;

        for (int i = str.length() - 1; i >= 0; i--) {
            char current =  str.charAt(i);

            if (i > 0 && current == '=') {
                char front = str.charAt(i - 1);

                if (front == 'c' || front == 's') {
                    i--;
                } else if (front == 'z') {
                    i--;
                    if (i > 0 && str.charAt(i - 1) == 'd') {
                        i--;
                    }
                }
            } else if (i > 0 && current == '-') {
                char front = str.charAt(i - 1);

                if (front == 'c' || front == 'd') {
                    i--;
                }
            } else if (i > 0 && current == 'j') {
                char front =  str.charAt(i - 1);

                if (front == 'n' || front == 'l') {
                    i--;
                }
            }

            stringCount++;
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        writer.write(String.valueOf(stringCount));
        writer.flush();
        writer.close();
    }
}
