import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int[] rocksWithDistance = new int[rocks.length + 2];
        System.arraycopy(rocks, 0, rocksWithDistance, 1, rocks.length);
        rocksWithDistance[rocksWithDistance.length - 1] = distance;
        Arrays.sort(rocksWithDistance);
        
        // 바위를 제거했을 때의 최소값을 구한다.
        // 최소값 중 가장 큰 값을 구한다.
        int startDistanceBetween = 1;
        int endDistanceBetween = distance + 1;
        
        while (endDistanceBetween - startDistanceBetween > 1) {
            int distanceBetween = (startDistanceBetween + endDistanceBetween) / 2;
            
            int removedRocksCount = removeRocks(rocksWithDistance, distanceBetween);
            
            if (removedRocksCount <= n) {
                startDistanceBetween = distanceBetween;
            } else {
                endDistanceBetween = distanceBetween;
            }
        }
        
        return startDistanceBetween;
    }
    
    private int removeRocks(int[] rocksWithDistance, int distanceBetween) {
        int removedCount = 0;
        
        int index = 0;
        while (index < rocksWithDistance.length - 1) {
            int nextIndex = index + 1;
            
            while (nextIndex < rocksWithDistance.length && 
                   rocksWithDistance[nextIndex] - rocksWithDistance[index] < distanceBetween) {
                nextIndex++;
                removedCount++;
            }
            
            index = nextIndex;
        }
        
        return removedCount;
    }
}

/*
rocks: [2, 14, 11, 21, 17]
distance: 25
제거: [21, 17]
남은 바위: [2, 14, 11]
각 바위 사이의 거리: [2, 9, 3, 11]

25 - 14 = 11
14 - 11 = 3
11 - 2 = 9
2 - 0 = 2
*/