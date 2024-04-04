import java.util.*;

class Solution {
    private static final int MIN_BASE_SCORE = 1;
    private static final int MAX_BASE_SCORE = 20;
    private static final int MIN_MULTIPLIER = 1;
    private static final int MAX_MULTIPLIER = 3;
    private static final int BULL_SCORE = 50;
    
    public int[] solution(int target) {
        // 추가 점수 목록을 만든다.
        Map<Integer, List<Score>> extraScores = new HashMap<>();
        
        for (int base = MIN_BASE_SCORE; base <= MAX_BASE_SCORE; base++) {
            for (int multiplier = MIN_MULTIPLIER; multiplier <= MAX_MULTIPLIER; multiplier++) {
                int singleCount = 0;
                if (multiplier == 1) {
                    singleCount++;
                }
                Score score = new Score(1, singleCount, 0);
                
                extraScores.computeIfAbsent(base * multiplier, k -> new ArrayList<>())
                    .add(score);
            }
        }
        
        // 불 경우의 수를 추가한다.
        Score bullScore = new Score(1, 0, 1);
        extraScores.computeIfAbsent(BULL_SCORE, k -> new ArrayList<>())
                    .add(bullScore);
        
        // target까지 dp를 진행한다.
        Score[] optimalScores = new Score[target + 1];
        optimalScores[0] = new Score(0, 0, 0);
                
        for (int n = 1; n < optimalScores.length; n++) {
            List<Score> possibleScores = new ArrayList<>();
            
            for (Map.Entry<Integer, List<Score>> entry : extraScores.entrySet()) {
                int requiredScore = n - entry.getKey();
                if (requiredScore < 0) {
                    continue;
                }
                
                for (Score extraScore : entry.getValue()) {
                    Score prevScore = optimalScores[requiredScore];
                    
                    Score possibleScore = new Score(
                        prevScore.totalCount + extraScore.totalCount,
                        prevScore.singleCount + extraScore.singleCount,
                        prevScore.bullCount + extraScore.bullCount
                    );
                    
                    possibleScores.add(possibleScore);
                }
            }
            
            possibleScores.sort((a, b) -> {
                if (a.totalCount == b.totalCount) {
                    return (b.singleCount + b.bullCount) - (a.singleCount + a.bullCount);
                }
                
                return a.totalCount - b.totalCount;
            });
            
            optimalScores[n] = possibleScores.get(0);
        }
        
        int[] result = {
            optimalScores[target].totalCount, 
            optimalScores[target].singleCount + optimalScores[target].bullCount
            };
        return result; 
    }
    
    private static class Score {
        final int totalCount;
        final int singleCount;
        final int bullCount;
        
        public Score(int totalCount, int singleCount, int bullCount) {
            this.totalCount = totalCount;
            this.singleCount = singleCount;
            this.bullCount = bullCount;
        }
    }
}