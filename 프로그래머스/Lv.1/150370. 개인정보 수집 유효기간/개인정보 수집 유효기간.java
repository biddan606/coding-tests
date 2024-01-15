import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        Map<String, Integer> termMap = new HashMap<>();
        for (String term : terms) {
            String[] splited = term.split(" ");
            termMap.put(splited[0], Integer.parseInt(splited[1]));
        }
        
        int todayDate = getDate(today);
        
        List<Integer> result = new ArrayList<>();   
        for (int i = 0; i < privacies.length; i++) {
            String[] splited = privacies[i].split(" ");
            
            int privacyDate = getDate(splited[0]);
            
            int expirationDate = plusMonth(privacyDate, termMap.get(splited[1]));
            
            if (isPast(expirationDate, todayDate)) {
                result.add(i + 1);
            }
        }
        
        return result.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
    
    private int getDate(String date) {
        String[] splited = date.split("\\.");
        int year = Integer.parseInt(splited[0]);
        int month = Integer.parseInt(splited[1]);
        int day = Integer.parseInt(splited[2]);
        
        return (year * 28 * 12) + (month * 28) + day;
    }
    
    private int plusMonth(int date, int month) {
        return date + (month * 28);
    }
    
    private boolean isPast(int target, int source) {
        return target <= source;
    }
}