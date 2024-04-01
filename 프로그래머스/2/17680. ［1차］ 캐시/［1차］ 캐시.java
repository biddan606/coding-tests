import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int runtime = 0;
        Lru<String, String> lruCache = new Lru<>(cacheSize);
        
        for (String city : cities) {
            String cityLower = city.toLowerCase();
            
            if (lruCache.containsKey(cityLower)) {
                ++runtime;
            } else {
                runtime += 5;
            }
            
            lruCache.put(cityLower, cityLower);
        }
        
        return runtime;
    }
    
    private static class Lru<K, V> extends LinkedHashMap<K, V> {
        private final int size;

        protected Lru(int size) {
            super(size, 0.75f, true);
            this.size = size;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > size;
        }
    }
}
