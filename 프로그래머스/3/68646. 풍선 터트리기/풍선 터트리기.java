import java.util.*;

class Solution {
    public int solution(int[] a) {
        List<Integer> descendingValues = getDescending(a);
        List<Integer> ascendingValues = getAscending(a);
        
        Set<Integer> result = new HashSet<>(descendingValues);
        result.addAll(ascendingValues);
        
        return result.size();
    }
    
    private List<Integer> getDescending(int[] array) {
        int min = Integer.MAX_VALUE;
        List<Integer> result = new ArrayList<>();
        
        for (int n : array) {
            if (min > n) {
                min = n;
                result.add(n);
            }
        }
        
        return result;
    }
    
    private List<Integer> getAscending(int[] array) {
        int min = Integer.MAX_VALUE;
        List<Integer> result = new ArrayList<>();
        
        for (int i = array.length - 1; i >= 0; i--) {
            int n = array[i];
            
            if (min > n) {
                min = n;
                result.add(n);
            }
        }
        
        return result;
    }
}