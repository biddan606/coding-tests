import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {

    public int solution(int[] numbers) {
        Set<Integer> set = Arrays.stream(numbers)
                .boxed()
                .collect(Collectors.toSet());

        return 45 - set.stream()
                .mapToInt(i -> i)
                .sum();
    }
}
