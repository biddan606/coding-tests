import java.util.*;

class Solution {
    /*
    딱 한번만 작은 풍선을 터트리는 행위를 할 수 있다.
    이 조건을 만족하기 위해서는, 양쪽 중에 한쪽 방향에만 나보다 작은 수가 존재해야 한다.
    결국 V 자 형태의 값들이 추출될 것이다.
    n == a.length 일 때, 
    0 -> n 까지 내림차순 값들과 n -> 0까지 내림차순 값들을 합치면 V 자 형태의 값들을 추출할 수 있다.
    제일 작은 값은 중복되므로 제거해주어야 한다.
    */
    public int solution(int[] a) {
        List<Integer> descendingValues = getDescending(a);
        List<Integer> ascendingValues = getAscending(a);
        
        return descendingValues.size() + ascendingValues.size() - 1;
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