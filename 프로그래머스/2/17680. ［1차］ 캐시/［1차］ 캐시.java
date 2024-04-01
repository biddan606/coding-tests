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
            // accessOrder == true이므로 갱신 시에 기존값이 존재하더라도 마지막으로 보낸다.
            super(size, 0.75f, true);
            this.size = size;
        }

        // size보다 더 많은 값들을 가지고 있다면, 가장 오래된 엔트리를 삭제한다.
        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > size;
        }
    }
}
