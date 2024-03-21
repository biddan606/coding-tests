import java.util.*;

class Solution {
    
    private static final int TARGET_LENGTH = 11;
    private static final int TARGET_MAX_VALUE = 10;
    
    public int[] solution(int n, int[] apeachInfo) {
        int[] ryanInfo = new int[TARGET_LENGTH];
        Result result = new Result();
        
        dfs(ryanInfo, apeachInfo,  0, n, result);
        
        if (Objects.isNull(result.info)) {
            return new int[]{-1};
        }
        return result.info;
    }
    
    private void dfs(int[] ryanInfo, int[] apeachInfo,  int index, int n, Result result) {
        if (index == TARGET_LENGTH - 1) {
            ryanInfo[index] = n;
            
            updateResultIfBetter(ryanInfo, apeachInfo, result);
            
            ryanInfo[index] = 0;
            return;
        }
        
        dfs(ryanInfo, apeachInfo, index + 1, n, result);
        
        if (n > apeachInfo[index]) {
            ryanInfo[index] = apeachInfo[index] + 1;
            dfs(ryanInfo, apeachInfo, index + 1, n - ryanInfo[index], result);
            ryanInfo[index] = 0;
        }
    }
    
    private void updateResultIfBetter(int[] ryanInfo, int[] apeachInfo, Result result) {
        int scoreDifference = calculateScoreDifference(ryanInfo, apeachInfo);
        if (scoreDifference <= 0) {
            return;
        }

        if (scoreDifference > result.scoreDifference) {
            result.scoreDifference = scoreDifference;
            result.info = Arrays.copyOf(ryanInfo, ryanInfo.length);
        } else if (scoreDifference == result.scoreDifference && compare(ryanInfo, result.info) > 0) {
            result.info = Arrays.copyOf(ryanInfo, ryanInfo.length);
        }
    }
    
    private int calculateScoreDifference(int[] ryanInfo, int[] apeachInfo) {
        int scoreDiffernece = 0;
        
        for (int i = 0; i < TARGET_LENGTH; i++) {
            int currentScore = TARGET_MAX_VALUE - i;
            
            if (ryanInfo[i] == 0 && apeachInfo[i] == 0) {
                continue;
            }
            
            if (ryanInfo[i] > apeachInfo[i]) {
                scoreDiffernece += currentScore;
            } else {
                scoreDiffernece -= currentScore;
            }
        }
        
        return scoreDiffernece;
    }
    
    private int compare(int[] info1, int[] info2) {
        for (int i = TARGET_LENGTH - 1; i >= 0; i--) {
            if (info1[i] == info2[i]) {
                continue;
            }
            
            return info1[i] - info2[i];
        }
        
        return 0;
    }
    
    private static class Result {
        int scoreDifference = 0;
        int[] info;
    }
}