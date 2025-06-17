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
    */
    public int calculate(String s) {
        // number 분리
        int[] numbers = Arrays.stream(s.split("[+\\-*/]"))
                            .map(String::trim)
                            .mapToInt(Integer::parseInt)
                            .toArray();

        // operator 분리
        Character[] operators = Arrays.stream(s.split("[\\d]"))
                            .map(String::trim)
                            .filter(str -> !str.isEmpty())
                            .map(str -> str.charAt(0))
                            .toArray(Character[]::new);

        Deque<Integer> numberStack = new ArrayDeque<>();
        Deque<Character> operatorStack = new ArrayDeque<>();

        numberStack.push(numbers[0]);

        for (int i = 1; i < numbers.length; i++) {
            int currentNumber = numbers[i];
            char currentOperator = operators[i - 1];

            while (!operatorStack.isEmpty() 
                && isGrePriority(operatorStack.peek(), currentOperator)) {
                
                calculate(numberStack, operatorStack.pop());
            }

            numberStack.push(currentNumber);
            operatorStack.push(currentOperator);
        }

        while (!operatorStack.isEmpty()) {
            calculate(numberStack, operatorStack.pop());
        }

        return numberStack.peek();
    }

    private static boolean isGrePriority(char operator1, char operator2) {
        return !((operator1 == '+' || operator1 == '-')
            && (operator2 == '*' || operator2 == '/'));
    }

    private static void calculate(Deque<Integer> numberStack, char operator) {
        int rightNumber = numberStack.pop();
        int leftNumber = numberStack.pop();

        int result = calulate(leftNumber, rightNumber, operator);

        numberStack.push(result);
    }

    private static int calulate(int leftNumber, int rightNumber, char operator) {
        switch (operator) {
            case '+': 
                return leftNumber + rightNumber;
            case '-':
                return leftNumber - rightNumber;
            case '*':
                return leftNumber * rightNumber;
            case '/':
                return leftNumber / rightNumber;
            default:
                throw new IllegalArgumentException("잘못된 연산자");
        }
    }
}
