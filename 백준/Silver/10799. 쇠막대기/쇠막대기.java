import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String layout = reader.readLine();
        reader.close();

        int pieces = 0;
        int bars = 0;

        for (int i = 0; i < layout.length(); i++) {
            char c = layout.charAt(i);
            if (c == '(') {
                if (isLaser(layout, i)) {
                    pieces += bars;
                    i++;
                } else {
                    bars++;
                }
            } else {
                bars--;
                pieces++;
            }
        }

        System.out.println(pieces);
    }

    private static boolean isLaser(String layout, int index) {
        boolean hasNextChar = layout.length() > index + 1;
        if (!hasNextChar) {
            return false;
        }

        boolean isCurrentOpeningBracket = layout.charAt(index) == '(';
        boolean isNextClosingBracket = layout.charAt(index + 1) == ')';
        return isCurrentOpeningBracket && isNextClosingBracket;
    }
}
