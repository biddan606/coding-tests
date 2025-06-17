class Solution {
    /*
    # 문제 이해
    - 수식(3+2*2) 결과를 반환해야 한다
    - 수식은 (*, /)이 앞에서부터 수행되고
        그 이후에 (+, -)가 앞에서부터 수행된다
    - 나눗셈(/) 결과가 소수일 경우, 내림된다

    # 풀이 접근
    수식의 최대 길이는 3 * 10^5이다 
    operator, value가 10^5개 들어올 수 있다는 것이다
    고로, 1개의 연산당 선형 시간이 걸리면 안된다

    Stack을 이용하면 1개의 연산시 상수 시간으로 해결할 수 있다

    ## 요구사항
    1. (+, -) 연산 전에 (*, /) 연산을 수행해야 한다
    2. 앞 (+, -)은 뒤 (+, -)보다 먼저 수행되어야 한다

    1. stack에 opreator를 쌓는다.
    2-1. 앞 operator보다 뒤 operator의 우선순위가 먼저라면 추가한다
    2-2. 앞 operator보다 뒤 operator의 우선순위가 낮거나 같다면 앞의 연산 수행
    3. 남은 연산 수행

    # 구현 스텝
    1. operator와 number를 분리한다
    2. number 0-index를 넣은 상태에서
        operator, number 순회하며 (2-1, 2-2) 수행
    3. 남은 연산 수행
    4. number peek 반환

    ---

    공간 복잡도를 O(1)로 풀 수 있다

    "22 - 3 * 5"라고 할 때,
    '-'가 오는 경우 22는 계산되어도 된다 -> 22는 그 다음 계산부터 무관하므로
    22가 계산될 수 있게 "0 + 22 - 3 * 5"라고 표현하면 된다

    '*'가 오면 미뤄야 한다
    "22 * 3 - 5"라고 할 때,
    '-' 지점에 도달해서 '*'를 계산해도 '-'와 무관하지 않기 때문이다
    '-'와 계산되는 leftNumber가 만들어진다

    이 성질을 이용해서 공간 복잡도 O(1)로 풀 수 있다

    맨 앞에 "0 + " 가 있다 생각하여
    operator = '+'
    lastNumber = 0 로 시작한다

    currentNumber로 숫자를 생성하며
    '+', '-'가 오면 lastNumber를 결과에 추가하고

    '*', '/'가 오면 lastNumber를 갱신한 뒤, 미룬다

    */
    public int calculate(String s) {
        int lastNumber = 0;
        char operator = '+';
        int currentNumber = 0;
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                currentNumber = currentNumber * 10 + c - '0';
            } 
            
            if (isOperator(c) || i == s.length() - 1) {
                switch (operator) {
                    case '+':
                    result += lastNumber;
                    lastNumber = currentNumber;
                    break;
                
                case '-':
                    result += lastNumber;
                    lastNumber = -currentNumber;
                    break;

                case '*':
                    lastNumber *= currentNumber;
                    break;

                case '/':
                    lastNumber /= currentNumber;
                    break;

                default:
                    break;
                }

                operator = c;
                currentNumber = 0;
            }
        }

        result += lastNumber;

        return result;
    }

    private static boolean isOperator(char c) {
        return c == '+' 
            || c == '-'
            || c == '*'
            || c == '/';
    }
}
