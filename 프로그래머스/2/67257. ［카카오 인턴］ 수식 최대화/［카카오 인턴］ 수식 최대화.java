import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;

class Solution {

    private static final String[][] PRECEDENCES_GROUP = {
            "+-*".split(""),
            "+*-".split(""),
            "*+-".split(""),
            "*-+".split(""),
            "-*+".split(""),
            "-+*".split("")
    };

    public long solution(String expression) {
        // 문자열을 파싱한다.
        StringTokenizer tokenizer = new StringTokenizer(expression, "+-*", true);
        List<String> tokens = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            tokens.add(tokenizer.nextToken());
        }

        // 계산을 통해 절대값이 가장 큰 값을 얻는다.
        long max = 0;
        for (String[] precedences : PRECEDENCES_GROUP) {
            max = Math.max(Math.abs(calculate(tokens, precedences)), max);
        }

        return max;
    }

    private long calculate(List<String> tokens, String[] precedences) {
        LinkedList<String> linkedList = new LinkedList<>(tokens);

        for (String precedence : precedences) {

            for (int i = 1; i < linkedList.size() - 1; i++) {
                String operator = linkedList.get(i);
                if (!operator.equals(precedence)) {
                    continue;
                }

                long lhs = Long.parseLong(linkedList.get(i - 1));
                long rhs = Long.parseLong(linkedList.get(i + 1));
                long result = calculate(lhs, rhs, operator);

                linkedList.remove(i - 1);
                linkedList.remove(i - 1);
                linkedList.set(i - 1, String.valueOf(result));
                i -= 2;
            }
        }

        return Long.parseLong(linkedList.get(0));
    }

    private long calculate(long lhs, long rhs, String operator) {
        switch (operator) {
            case "+":
                return lhs + rhs;
            case "-":
                return lhs - rhs;
            case "*":
                return lhs * rhs;
            default:
                throw new IllegalArgumentException("계산할 수 없는 연산자입니다.");
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.solution("100-200*300-500+20"));
    }
}
