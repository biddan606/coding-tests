import java.util.Arrays;

class Solution {

    public String[] solution(String[] strings, int n) {
        return Arrays.stream(strings)
                .sorted((s1, s2) -> {
                    int comparedResult = s1.charAt(n) - s2.charAt(n);
                    if (comparedResult == 0) {
                        return s1.compareTo(s2);
                    }
                    return comparedResult;
                })
                .toArray(String[]::new);
    }
}
