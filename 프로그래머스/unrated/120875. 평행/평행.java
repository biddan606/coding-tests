import java.util.*;

class Solution {
    public int solution(int[][] dots) {
        Set<Double> slopes = new HashSet<>();
        
        for (int i1 = 0; i1 < dots.length; i1++) {
            for (int i2 = i1 + 1; i2 < dots.length; i2++) {
                for (int j1 = 0; j1 < dots.length; j1++) {
                    if (j1 == i1 || j1 == i2) {
                        continue;
                    }
                    
                    for (int j2 = j1 + 1; j2 < dots.length; j2++) {
                        if (j2 == i1 || j2 == i2) {
                            continue;
                        }
                        
                        int[] dotI1 = dots[i1];
                        int[] dotI2 = dots[i2];
                        double slope1 = (double) (dotI1[1] - dotI2[1]) / (dotI1[0] - dotI2[0]);
                        
                        int[] dotJ1 = dots[j1];
                        int[] dotJ2 = dots[j2];
                        double slope2 = (double) (dotJ1[1] - dotJ2[1]) / (dotJ1[0] - dotJ2[0]);
                        
                        if (slope1 == slope2) {
                            return 1;
                        }
                    }
                }
            }
        }
        
        return 0;
    }
}