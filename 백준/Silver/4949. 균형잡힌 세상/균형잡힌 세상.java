import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String line = reader.readLine();
            if (line.length() == 1 && line.charAt(0) == '.') {
                break;
            }

            boolean result = isBalance(line);

            if (result) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }

        reader.close();
    }

    private static boolean isBalance(String str) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : str.toCharArray()) {
            switch (c) {
                case '(':
                case '[':
                    stack.push(c);
                    break;
                case ')':
                case ']':
                    if (!match(stack, c)) {
                        return false;
                    }
                    break;
                default:
                    break;
            }
        }

        return stack.isEmpty();
    }

    private static boolean match(Deque<Character> stack, char c) {
        if (stack.isEmpty()) {
            return false;
        }

        char matching = stack.pop();

        if ((c == ')' && matching == '(')
                || (c == ']' && matching == '[')) {
            return true;
        }
        return false;
    }
}
