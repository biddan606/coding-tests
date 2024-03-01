import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        SortedSet<Integer> reserveSet = new TreeSet<>();
        for (int number : reserve) {
            reserveSet.add(number);
        }
        
        SortedSet<Integer> lostSet = new TreeSet<>();
        for (int number : lost) {
            if (reserveSet.contains(number)) {
                reserveSet.remove(number);
                continue;
            }
            
            lostSet.add(number);
        }
        
        int count = 0;
        for (int number : lostSet) {
            int prev = number - 1;
            if (reserveSet.contains(prev)) {
                reserveSet.remove(prev);
                continue;
            }
            
            int next = number + 1;
            if (reserveSet.contains(next)) {
                reserveSet.remove(next);
                continue;
            }
            
            count++;
        }
        
        return n - count;
    }
}