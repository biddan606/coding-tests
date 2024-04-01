import java.util.*;

class Solution {
    
    private final LinkedList<String> cache = new LinkedList<>();
    
    public int solution(int cacheSize, String[] cities) {
        int runtime = 0;
        
        for (String city : cities) {
            String cityLower = city.toLowerCase();
            
            if (!cache.contains(cityLower)) {
                runtime += 5;
            } else {
                runtime += 1;
            }
            
            update(cityLower, cacheSize);
        }
        
        return runtime;
    }
    
    private void update(String newCity, int cacheSize) {
        if (cacheSize == 0) {
            return;
        }
        
        if (cache.contains(newCity)) {
            cache.remove(newCity);
        }
        while (cacheSize <= cache.size()) {
            cache.removeFirst();    
        }
        
        cache.add(newCity);
    }
}