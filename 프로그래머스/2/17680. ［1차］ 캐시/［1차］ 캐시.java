import java.util.*;

class Solution {
    
    private final SortedMap<Integer, String> cacheMap = new TreeMap<>();
    private final Set<String> cacheSet = new HashSet<>();
    
    public int solution(int cacheSize, String[] cities) {
        int runtime = 0;
        int timeStamp = 0;
        
        for (String city : cities) {
            String cityLower = city.toLowerCase();
            
            if (!cacheSet.contains(cityLower)) {
                runtime += 5;
            } else {
                runtime += 1;
            }
            
            update(cityLower, timeStamp, cacheSize);
            
            timeStamp++;
        }
        
        return runtime;
    }
    
    private void update(String newCity, int timeStamp, int cacheSize) {
        if (cacheSize == 0) {
            return;
        }
        
        if (cacheSet.contains(newCity)) {
            cacheMap.values().remove(newCity);
            cacheSet.remove(newCity);
        }
        
        if (cacheSet.size() >= cacheSize) {
            int firstKey = cacheMap.firstKey();
            String oldestCity = cacheMap.remove(firstKey);
            cacheSet.remove(oldestCity);
        }
        
        cacheMap.put(timeStamp, newCity);
        cacheSet.add(newCity);
    }
}