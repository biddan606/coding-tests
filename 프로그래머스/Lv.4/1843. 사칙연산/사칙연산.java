import java.util.*;

class Solution {
    public int solution(String[] arr) {
        List<Integer> numbers = new ArrayList<>();
        List<String> operators = new ArrayList<>();
        for (String s : arr) {
            switch (s) {
                case "+":
                case "-":
                    operators.add(s);
                    break;
                default:
                    numbers.add(Integer.parseInt(s));
            }
        }

        int[][] memory = new int[numbers.size()][numbers.size()];
        for (int[] row : memory) {
            Arrays.fill(row, -1);
        }

        for (int start = 0; start < memory.length; start++) {
            memory[start][start] = numbers.get(start);

            for (int end = start + 1; end < memory.length; end++) {
                memory[start][end] = memory[start][end - 1] + convertNumber(operators.get(end - 1), numbers.get(end));
            }
        }

        return calculateMax(0, memory, operators, 0, 1000 * -101);
    }

    private int convertNumber(String operator, int number) {
        if (operator.equals("-")) {
            return  -number;
        }
        return number;
    }

    private int calculateMax(int start, int[][] memory, List<String> operators, int current, int result) {
        if (start == memory.length) {
            return Math.max(current, result);
        }

        for (int end = start; end < memory.length; end++) {
            String operator = "+";
            if (start != 0) {
                operator = operators.get(start - 1);
            }
            
            int addingNumber = convertNumber(operator, memory[start][end]);
            result = Math.max(result, calculateMax(end + 1, memory, operators, current + addingNumber, result));
        }

        return result;
    }
}
