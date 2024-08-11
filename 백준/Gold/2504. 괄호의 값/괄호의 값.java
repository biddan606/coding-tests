import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String parentheses = reader.readLine();
        reader.close();

        if (!isValidParentheses(parentheses)) {
            System.out.println(0);
            return;
        }

        int index = 0;
        int totalScore = 0;

        while (index < parentheses.length()) {
            ScoringResult scoringResult = calculateScore(parentheses, index, 1, true);

            index = scoringResult.nextIndex;
            totalScore += scoringResult.score;
        }

        System.out.println(totalScore);
    }

    private static boolean isValidParentheses(String parentheses) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : parentheses.toCharArray()) {
            if (isOpeningParenthesis(c)) {
                stack.push(c);
            } else if (isClosingParenthesis(c)) {
                if (stack.isEmpty() || !isMatchingPair(stack.pop(), c)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private static boolean isOpeningParenthesis(char c) {
        return c == '[' || c == '(';
    }

    private static boolean isClosingParenthesis(char c) {
        return c == ']' || c == ')';
    }

    private static boolean isMatchingPair(char opening, char closing) {
        return (opening == '[' && closing == ']') || (opening == '(' && closing == ')');
    }

    private static ScoringResult calculateScore(String parentheses, int index, int multiplier, boolean shouldAddScore) {
        int currentScore = 0;
        char p = parentheses.charAt(index);

        if (isOpeningParenthesis(p)) {
            shouldAddScore = true;

            if (p == '(') {
                multiplier *= 2;
            } else if (p == '[') {
                multiplier *= 3;
            }
        } else if (isClosingParenthesis(p)) {
            if (shouldAddScore) {
                currentScore += multiplier;
            }
            shouldAddScore = false;

            if (p == ')') {
                multiplier /= 2;
            } else if (p == ']'){
                multiplier /= 3;
            }
        }

        if (multiplier == 1) {
            return new ScoringResult(index + 1, currentScore);
        }

        ScoringResult nestedResult = calculateScore(parentheses, index + 1, multiplier, shouldAddScore);
        return new ScoringResult(nestedResult.nextIndex, nestedResult.score + currentScore);
    }

    private static class ScoringResult {
        final int nextIndex;
        final int score;

        public ScoringResult(int nextIndex, int score) {
            this.nextIndex = nextIndex;
            this.score = score;
        }
    }
}
