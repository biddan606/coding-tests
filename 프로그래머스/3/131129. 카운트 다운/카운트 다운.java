import java.util.*;

class Solution {
    public int[] solution(int target) {
        Element[] dp = new Element[target + 1];
        dp[0] = new Element(0, 0, 0);
        
        for (int i = 1; i <= target; i++) {
            List<Element> current = new ArrayList<>();
            
            for (int n = 1; n <= 20; n++) {
                for (int coefficient = 1; coefficient <= 3; coefficient++) {
                    int dif = n * coefficient;
                    if (i - dif < 0) {
                        continue;
                    }
                    
                    Element prevElement = dp[i - dif];
                    int nextTotalCount = prevElement.totalCount + 1;
                    int nextSingleCount = prevElement.singleCount;
                    if (coefficient == 1) {
                        nextSingleCount++;
                    }
                    int nextBullCount = prevElement.bullCount;
                    
                    Element newElement = 
                        new Element(nextTotalCount, nextSingleCount, nextBullCount);
                    current.add(newElement);
                }
            }
            
            if (i >= 50) {
                Element prevElement = dp[i - 50];
                int nextTotalCount = prevElement.totalCount + 1;
                int nextSingleCount = prevElement.singleCount;
                int nextBullCount = prevElement.bullCount + 1;
                
                Element newElement = new Element(nextTotalCount, nextSingleCount, nextBullCount);
                current.add(newElement);
            }
            
            current.sort((a, b) -> {
                if (a.totalCount == b.totalCount) {
                    return (b.singleCount + b.bullCount) - (a.singleCount + a.bullCount);
                }
                
                return a.totalCount - b.totalCount;
            });
            
            dp[i] = current.get(0);
        }
        
        int[] result = {dp[target].totalCount, dp[target].singleCount + dp[target].bullCount};
        return result;
    }
    
    private static class Element {
        final int totalCount;
        final int singleCount;
        final int bullCount;
        
        public Element(int totalCount, int singleCount, int bullCount) {
            this.totalCount = totalCount;
            this.singleCount = singleCount;
            this.bullCount = bullCount;
        }
    }
}