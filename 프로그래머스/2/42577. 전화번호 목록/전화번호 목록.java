import java.util.*;

class Solution {
    public boolean solution(String[] phoneBook) {
        Set<String> phoneNumbers = new HashSet<>();
        
        for (String phoneNumber : phoneBook) {
            phoneNumbers.add(phoneNumber);
        }
        
        for (String phoneNumber : phoneBook) {
            for (int i = 1; i < phoneNumber.length(); i++) {
                String prefix = phoneNumber.substring(0, i);
                
                if (phoneNumbers.contains(prefix)) {
                    return false;
                }
            }
        }
        
        return true;
    }
}