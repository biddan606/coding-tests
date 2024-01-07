import java.util.*;

class Solution {

    public String solution(String myString) {
        StringBuilder builder = new StringBuilder();
        Set<Character> usedCharacters = new HashSet<>();
        
        for (char c : myString.toCharArray()) {
            if (usedCharacters.contains(c)) {
                continue;
            }

            builder.append(c);
            usedCharacters.add(c);
        }

        return builder.toString();
    }
}
