import java.util.*;
import java.util.stream.*;

class Solution {
    public boolean solution(String[] phoneBook) {
        Set<String> phoneNumbers = new HashSet<>(Arrays.asList(phoneBook));
        
        return !phoneNumbers.stream()
            .anyMatch(phoneNumber ->
                      IntStream.range(1, phoneNumber.length())
                      .anyMatch(i -> phoneNumbers.contains(phoneNumber.substring(0, i)))
                     );
    }
}