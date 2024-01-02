import java.util.HashSet;
import java.util.Set;

class Solution {

    public int solution(String numbers) {
        // 각 자릿수를 얻는다.
        int[] digits = numbers.chars()
                .map(c -> c - '0')
                .toArray();

        // DFS를 돌며 소수를 얻는다.
        Set<Integer> primeNumbers = new HashSet<>();
        setPrimeNumbers(digits, new boolean[digits.length], 0, primeNumbers);

        return primeNumbers.size();
    }

    private void setPrimeNumbers(int[] digits, boolean[] usedDigits, int number, Set<Integer> primeNumbers) {
        if (isPrimeNumber(number)) {
            primeNumbers.add(number);
        }

        for (int i = 0; i < digits.length; i++) {
            if (usedDigits[i]) {
                continue;
            }

            usedDigits[i] = true;

            setPrimeNumbers(digits, usedDigits, number * 10 + digits[i], primeNumbers);

            usedDigits[i] = false;
        }
    }

    private boolean isPrimeNumber(int number) {
        if (number < 2) {
            return false;
        }

        int sqrt = (int) Math.sqrt(number);

        for (int divider = 2; divider <= sqrt; divider++) {
            if (number % divider == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        String numbers = "011";
        System.out.println(solution.solution(numbers));
    }
}
