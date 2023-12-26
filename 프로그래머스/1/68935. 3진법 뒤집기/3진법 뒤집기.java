import java.util.*;

class Solution {
    public int solution(int n) {
        String base3 = Long.toString(n, 3);

        String reversedBase3 = new StringBuilder(base3)
                .reverse().toString();

        return (int) Long.parseLong(reversedBase3, 3);
    }
}
