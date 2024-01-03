import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Solution {

    public String solution(String str) {
        List<String> sortedStrings = Arrays.stream(str.split(""))
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        return String.join("", sortedStrings);
    }
}
