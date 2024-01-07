import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {

    public int solution(int[] numbers) {
        return 45 - Arrays.stream(numbers).sum();
    }
}
