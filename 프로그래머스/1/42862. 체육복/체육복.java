import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        Set<Integer> reserveSet = IntStream.of(reserve)
            .boxed()
            .collect(Collectors.toSet());
        
        Set<Integer> lostSet = IntStream.of(lost)
            .boxed()
            .collect(Collectors.toSet());
        
        SortedSet<Integer> actualReserveSet = new TreeSet<>(reserveSet);
        actualReserveSet.removeAll(lostSet);
        
        SortedSet<Integer> actualLostSet = new TreeSet<>(lostSet);
        actualLostSet.removeAll(reserveSet);
        
        Set<Integer> borrowedSet = new HashSet<>();
        actualLostSet.forEach(l -> {
            int prev = l - 1;
            if (actualReserveSet.contains(prev)) {
                actualReserveSet.remove(prev);
                borrowedSet.add(l);
                return;
            }
            
            int next = l + 1;
            if (actualReserveSet.contains(next)) {
                actualReserveSet.remove(next);
                borrowedSet.add(l);
                return;
            }
        });
        
        return n - actualLostSet.size() + borrowedSet.size();
    }
}