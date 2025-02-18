import java.util.*;

class Solution {
    /*
    문제
    m번의 시도 안에 오름차순으로 정렬된 5개(범위: 1~n)의 비밀코드를 맞춰야 한다
    
    접근
    - n의 개수가 적음
    - 비밀 코드는 5개
    - n의 최대값이 30이고, 비밀코드는 5개이므로 30*29*28*27*26로 풀 수 있을거 같음
    
    직관
    1. q를 set의 형태로 변환한다 <- 체크하기 쉽게
    2. 오름차순으로 조합을 만든다
    3. 조합을 검증한다
    */
    
    private int result;
    private Set<Integer>[] qSet;
    private int[] qAnswer;
    private int max;
    
    public int solution(int n, int[][] q, int[] ans) {
        qSet = new HashSet[q.length];
        qAnswer = new int[ans.length];
        max = n;
        
        for (int i = 0; i < q.length; i++) {
            qSet[i] = new HashSet<>();
            
            for (int e : q[i]) {
                qSet[i].add(e);
            }
            
            qAnswer[i] = ans[i];
        }
        
        int[] combination = new int[5];
        
        for (int startNumber = 1; startNumber <= n; startNumber++) {
            combination[0] = startNumber;
            
            dfs(combination, 1, startNumber + 1);
        }
        
        return result;
    }
    
    private void dfs(int[] combination, int index, int startNumber) {
        if (index == combination.length) {
            if (verifyCombination(combination)) {
                result++;
            }
            return;
        }
        
        int nextIndex = index + 1;
        
        for (int selectedNumber = startNumber; selectedNumber <= max; selectedNumber++) {
            combination[index] = selectedNumber;
            
            dfs(combination, nextIndex, selectedNumber + 1);
        }
    }
    
    private boolean verifyCombination(int[] combination) {
        for (int i = 0; i < qSet.length; i++) {
            int corrected = 0;
            
            for (int e : combination) {
                if (qSet[i].contains(e)) {
                    corrected++;
                }
            }
            
            if (corrected != qAnswer[i]) {
                return false;
            }
        }
        
        return true;
    }
}
