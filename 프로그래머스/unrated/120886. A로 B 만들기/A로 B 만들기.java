import java.util.HashMap;
import java.util.Map;

class Solution {

    public int solution(String before, String after) {
        Map<Character, Integer> beforeMap = toMap(before);
        Map<Character, Integer> afterMap = toMap(after);

        if (beforeMap.equals(afterMap)) {
            return 1;
        }
        return 0;
    }

    private Map<Character, Integer> toMap(String str) {
        Map<Character, Integer> map = new HashMap<>();

        for (Character c : str.toCharArray()) {
            Integer prevValue = map.getOrDefault(c, 0);

            map.put(c, prevValue + 1);
        }

        return map;
    }
}
