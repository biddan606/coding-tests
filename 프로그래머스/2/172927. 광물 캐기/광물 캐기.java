import java.util.*;

class Solution {
    private static final int DIAMONE = 0;
    private static final int IRON = 1;
    private static final int STONE = 2;
    private static final int MINERAL_EXTRACTION_COUNT = 5;
    
    public int solution(int[] picks, String[] minerals) {
        if (isAllZero(picks)) {
            return 0;
        }
        
        int minFatigue = Integer.MAX_VALUE;
        
        for (int pick = 0; pick < picks.length; pick++) {
            if (picks[pick] == 0) {
                continue;
            }
            picks[pick]--;
            
            minFatigue = Math.min(minFatigue, getMinFatigue(picks, minerals, pick, 0));
            
            picks[pick]++;
        }
        
        return minFatigue;
    }
    
    private int getMinFatigue(int[] picks, String[] minerals, int pick, int mineralsStartIndex) {
        int fatigue = mine(pick, minerals, mineralsStartIndex, MINERAL_EXTRACTION_COUNT);
        if (isAllZero(picks)) {
            return fatigue;
        }
        
        int minFatigue = Integer.MAX_VALUE;
        
        for (int nextPick = 0; nextPick < picks.length; nextPick++) {
            if (picks[nextPick] == 0) {
                continue;
            }
            picks[nextPick]--;
            
            minFatigue = Math.min(minFatigue, fatigue + getMinFatigue(picks, minerals, nextPick, mineralsStartIndex + MINERAL_EXTRACTION_COUNT));
            
            picks[nextPick]++;
        }
        
        return minFatigue;
    }
    
    private int mine(int pick, String[] minerals, int startIndex, int extractionCount) {
        int fatique = 0;
        
        for (int i = startIndex; i < Math.min(minerals.length, startIndex + extractionCount); i++) {            
            fatique += mine(pick, minerals[i]);
        }
        
        return fatique;
    }
    
    private int mine(int pick, String mineral) {
        if (pick == STONE && Objects.equals(mineral, "diamond")) {
            return 25;
        } else if (pick == IRON && Objects.equals(mineral, "diamond")) {
            return 5;
        } else if (pick == STONE && Objects.equals(mineral, "iron")) {
            return 5;
        }
        return 1;
    }
    
    private boolean isAllZero(int[] array) {
        for (int n : array) {
            if (n != 0) {
                return false;
            }
        }
        
        return true;
    }
}