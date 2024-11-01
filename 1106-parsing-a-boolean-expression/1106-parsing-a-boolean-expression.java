class Solution {
    int index;

    public boolean parseBoolExpr(String expression) {
        // '(' 을 만나면 재귀를 탄다.
        // ')'을 만나면 재귀를 나온다.
        // 반환값은 List이다.
        index = 0;
        return calculateExpression(expression).get(0);
    }

    private List<Boolean> calculateExpression(String expression) {
        List<Boolean> result = new ArrayList<>();

        while (index < expression.length() && expression.charAt(index) != ')') {
            char c = expression.charAt(index);

            if (c == 't') {
                result.add(true);
            } else if (c == 'f') {
                result.add(false);
            } else if (c == '!' || c == '&' || c == '|') {
                index += 2;
                List<Boolean> subExpression = calculateExpression(expression);
                result.add(calculateSubExpression(c, subExpression));
            }

            index++;
        }
        
        return result;
    }

    private static boolean calculateSubExpression(char operator, List<Boolean> subExpression) {
        if (operator == '!') {
            return !subExpression.get(0);
        } else if (operator == '&') {
            for (boolean e : subExpression) {
                if (!e) {
                    return false;
                }
            }
            return true;
        } else if (operator == '|') {
            for (boolean e : subExpression) {
                if (e) {
                    return true;
                }
            }
            return false;
        }

        throw new IllegalArgumentException("잘못된 연산자입니다: %c".formatted(operator));
    }
}
