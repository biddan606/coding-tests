import java.util.*;

class Solution {
    public int[] solution(String s) {
        String[] nested = s.substring(2, s.length() - 2).split("\\},\\{");
        
        List<int[]> parsedNumbers = new ArrayList<>();
        
        for (String strNumbers : nested) {
            int[] numbers = Arrays.stream(strNumbers.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
            
            parsedNumbers.add(numbers);
        }
        
        parsedNumbers.sort(Comparator.comparingInt(a -> a.length));
        
        List<Integer> tuple = new ArrayList<>();
        Map<Integer, Integer> overallNumberFrequency = new HashMap<>();
        
        for (int[] numbers : parsedNumbers) {
            Map<Integer, Integer> currentNumberFrequency = new HashMap<>();
            
            for (int number : numbers) {
                currentNumberFrequency.put(number, currentNumberFrequency.getOrDefault(number, 0) + 1);
                
                int currentFrequency = currentNumberFrequency.get(number);
                int overallFrequency = overallNumberFrequency.getOrDefault(number, 0);
                
                if (currentFrequency > overallFrequency) {
                    tuple.add(number);
                    overallNumberFrequency.put(number, overallNumberFrequency.getOrDefault(number, 0) + 1);
                }
            }
        }
        
        return tuple.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}
