import java.util.Arrays;

class Solution {
    public int[] solution(long n) {
        String numberToString = String.valueOf(n);

        String reversed = new StringBuilder(numberToString).reverse().toString();

        return reversed.chars()
                .boxed()
                .mapToInt(asc -> asc - '0')
                .toArray();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] result = solution.solution(12345);

        System.out.println(Arrays.toString(result));
    }
}
