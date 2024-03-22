import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int result = dfs(words, new boolean[words.length], begin, target, 0);
        
        if (result == Integer.MAX_VALUE) {
            result = 0;
        }
        return result;
    }
    
    /*
    1. words를 순회한다.
    2. currentWord와 nextWord의 글자 차이를 반환 받는다.
    3. 차이가 1개라면 방문 처리하고 재귀한다.
    4. 최소 깊이라면 최소 깊이를 갱신한다.
    */
    private int dfs(String[] words, boolean[] visitedWords, String current, String target, int depth) {
        if (Objects.equals(current, target)) {
            return depth;
        }
        
        int minDepth = Integer.MAX_VALUE;
        
        for (int i = 0; i < words.length; i++) {
            if (visitedWords[i]) {
                continue;
            }
            String next = words[i];
            
            int difference = 0;
            for (int p = 0; p < current.length(); p++) {
                char baseCh = current.charAt(p);
                char compCh = next.charAt(p);
                if (baseCh != compCh) {
                    difference++;
                }
            }
            if (difference != 1) {
                continue;
            }
            
            visitedWords[i] = true;
            minDepth = Math.min(minDepth, dfs(words, visitedWords, next, target, depth + 1));
            visitedWords[i] = false;
        }
        
        return minDepth;
    }
}