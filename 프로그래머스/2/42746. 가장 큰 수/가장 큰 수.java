import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {

    public String solution(int[] numbers) {
//        if (Arrays.stream(numbers).allMatch(i -> i == 0)) {
//            return "0";
//        }

        return Arrays.stream(numbers)
                .mapToObj(String::valueOf)
                .sorted((s1, s2) -> (s2 + s1).compareTo(s1 + s2))
                .collect(Collectors.joining())
                .replaceAll("^0+", "0");
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] numbers = {
                0, 0, 0
        };

        String result = solution.solution(numbers);

        System.out.println(result);
    }
}
