import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

class Solution {

    public int[] solution(int[] numbers) {
        Set<Integer> resultSet = new TreeSet<>();

        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                int number1 = numbers[i];
                int number2 = numbers[j];

                resultSet.add(number1 + number2);
            }
        }

        return resultSet.stream()
                .mapToInt(n -> n)
                .toArray();
    }
}
