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
                result.add(applyOperator(c, subExpression));
            }

            index++;
        }
        
        return result;
    }

    private boolean applyOperator(char operator, List<Boolean> results) {
        return switch (operator) {
            case '!' -> !results.get(0);
            case '&' -> results.stream().allMatch(Boolean::booleanValue);
            case '|' -> results.stream().anyMatch(Boolean::booleanValue);
            default -> throw new IllegalArgumentException("잘못된 연산자입니다: %c".formatted(operator));
        };
    }
}
