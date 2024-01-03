import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Solution {

    public String solution(String str) {
        List<String> sortedStrings = str.chars()
                .boxed()
                .sorted(Comparator.reverseOrder())
                .map(Character::toString)
                .collect(Collectors.toList());

        return String.join("", sortedStrings);
    }
}
