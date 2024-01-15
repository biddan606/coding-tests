import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        Map<String, Integer> termMap = new HashMap<>();
        for (String term : terms) {
            String[] splited = term.split(" ");
            termMap.put(splited[0], Integer.parseInt(splited[1]));
        }
        
        SolutionDate todayDate = getDate(today);
        
        List<Integer> result = new ArrayList<>();   
        for (int i = 0; i < privacies.length; i++) {
            String[] splited = privacies[i].split(" ");
            
            SolutionDate privacyDate = getDate(splited[0]);
            
            SolutionDate expirationDate = plusMonth(privacyDate, termMap.get(splited[1]));
            
            if (isPast(expirationDate, todayDate)) {
                result.add(i + 1);
            }
        }
        
        return result.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
    
    private SolutionDate getDate(String date) {
        String[] splited = date.split("\\.");
        int year = Integer.parseInt(splited[0]);
        int month = Integer.parseInt(splited[1]);
        int day = Integer.parseInt(splited[2]);
        
        return new SolutionDate(year, month, day);
    }
    
    private SolutionDate plusMonth(SolutionDate date, int month) {
        int nextYear = date.year + (date.month + month - 1) / 12;
        int nextMonth = (date.month + month - 1) % 12 + 1;
            
        return new SolutionDate(
            nextYear,
            nextMonth,
            date.day
        );
    }
    
    private boolean isPast(SolutionDate target, SolutionDate source) {
        if (target.year < source.year) {
            return true;
        } else if (target.year == source.year) {
            if (target.month < source.month) {
                return true;
            } else if (target.month == source.month) {
                return target.day <= source.day;
            }
        }
        return false;
    }
    
    private static class SolutionDate {
        final int year;
        final int month;
        final int day;
        
        public SolutionDate(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }
    }
}