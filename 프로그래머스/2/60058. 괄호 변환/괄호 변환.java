import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {

    /**
     * 1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
     * 2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다.
     * 3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
     *   3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.
     * 4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다.
     *   4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
     *   4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
     *   4-3. ')'를 다시 붙입니다.
     *   4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
     *   4-5. 생성된 문자열을 반환합니다.
     *
     * @param p
     * @return
     */
    public String solution(String p) {
        return createCorrectParentheses(p);
    }

    private String createCorrectParentheses(String str) {
        if (isCorrectParentheses(str)) {
            return str;
        }

        // u는 최소 2개의 원소를 가지고 있다
        String[] uv = splitBalancedParentheses(str);

        String u = uv[0];
        String v = uv[1];

        String correctedV = createCorrectParentheses(v);

        if (isCorrectParentheses(u)) {
            return u + correctedV;
        } else {
            return "(" + correctedV + ")" + reverse(u.substring(1, u.length() - 1));
        }
    }

    private boolean isCorrectParentheses(String str) {
        int openParentheses = 0;

        for (char c : str.toCharArray()) {
            if (c == '(') {
                openParentheses++;
            } else if (c == ')'){
                if (openParentheses <= 0) {
                    return false;
                }
                openParentheses--;
            }
        }

        return true;
    }

    private String[] splitBalancedParentheses(String str) {
        int balance = 0;
        int index = 0;

        for (char c : str.toCharArray()) {
            if (c == '(') {
                balance++;
            } else if (c == ')') {
                balance--;
            }
            index++;

            if (balance == 0) {
                break;
            }
        }

        return new String[]{
                str.substring(0, index),
                str.substring(index)
        };
    }

    private String reverse(String str) {
        return str.chars()
                .mapToObj(c -> c == '(' ? ")" : "(")
                .collect(Collectors.joining());
    }
}
