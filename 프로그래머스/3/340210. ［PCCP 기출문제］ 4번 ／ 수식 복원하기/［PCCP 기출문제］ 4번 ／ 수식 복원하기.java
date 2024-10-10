import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    public String[] solution(String[] expressions) {
        // 문자열을 파싱해야 한다.(숫자, 연산자)
        List<String[]> splittedExpressions = new ArrayList<>();
        for (String expression : expressions) {
            splittedExpressions.add(expression.split(" "));
        }

        // 결과가 X가 아닌 표현식들의 가능한 진법을 알 수 있어야 한다.
        // 가능한 진법들의 교집합을 구해야 한다.
        List<Integer> bases = new ArrayList();
        for (int n = 2; n <= 9; n++) {
            boolean breaked = false;
            for (String[] expression : splittedExpressions) {
                for (char c : expression[0].toCharArray()) {
                    if (c - '0' >= n) {
                        breaked = true;
                    }
                }
                for (char c : expression[2].toCharArray()) {
                    if (c - '0' >= n) {
                        breaked = true;
                    }
                }
                if (expression[4].equals("X")) {
                    continue;
                }
                for (char c : expression[4].toCharArray()) {
                    if (c - '0' >= n) {
                        breaked = true;
                    }
                }

                if (breaked) {
                    break;
                }
            }

            if (breaked) {
                continue;
            }
            bases.add(n);
        }

        for (String[] currentExpression : splittedExpressions) {
            if (currentExpression[4].equals("X")) {
                continue;
            }

            List<Integer> impossibleBases = new ArrayList<>();
            for (Integer base : bases) {
                int n1 = toDecimal(currentExpression[0], base);
                int n2 = toDecimal(currentExpression[2], base);
                int expected = toDecimal(currentExpression[4], base);

                int result = calculateResult(n1, n2, currentExpression[1]);
                if (result != expected) {
                    impossibleBases.add(base);
                }
            }

            bases.removeAll(impossibleBases);
        }

        // 가능한 진법으로 결과가 X인 표현식들의 결과를 구해야 한다.
        // 가능한 모든 집합의 값이 같을 경우, 결과를 값으로 표현하고 아닐 경우 `?`로 표현한다.
        List<String> resultExpressions = new ArrayList<>();
        for (String[] xMarkExpression : splittedExpressions) {
            if (!xMarkExpression[4].equals("X")) {
                continue;
            }

            String expected = null;
            boolean breaked = false;
            for (Integer base : bases) {
                int n1 = toDecimal(xMarkExpression[0], base);
                int n2 = toDecimal(xMarkExpression[2], base);

                String result = toBase(calculateResult(n1, n2, xMarkExpression[1]), base);
                if (expected == null) {
                    expected = result;
                } else if (!expected.equals(result)) {
                    resultExpressions.add(
                            xMarkExpression[0] + " "
                                    + xMarkExpression[1] + " "
                                    + xMarkExpression[2] + " = ?"
                    );
                    breaked = true;
                    break;
                }
            }

            if (expected == null || !breaked) {
                resultExpressions.add(
                        xMarkExpression[0] + " "
                                + xMarkExpression[1] + " "
                                + xMarkExpression[2] + " = "
                                + expected
                );
            }
        }

        return resultExpressions.toArray(String[]::new);
    }

    private int toDecimal(String str, int base) {
        int result = 0;
        for (char c : str.toCharArray()) {
            result = result * base + (c - '0');
        }
        return result;
    }

    private int calculateResult(int n1, int n2, String operation) {
        if (operation.equals("+")) {
            return n1 + n2;
        }
        return n1 - n2;
    }

    private String toBase(int number, Integer base) {
        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            sb.append(number % base);
            number /= base;
        }

        if (sb.length() == 0) {
            return "0";
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        String[] expressions = {"1 + 1 = 2", "1 + 3 = 4", "1 + 5 = X", "1 + 2 = X"};
        String[] solution = new Solution().solution(expressions);

        System.out.println(Arrays.toString(solution));
    }
}