import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        // 인덱스 값과 이름 매핑한다.
        Map<String, Integer> friendIndexMap = new HashMap<>();
        for (int i = 0; i < friends.length; i++) {
            friendIndexMap.put(friends[i], i);
        }
        
        // 선물 준 횟수를 기록한다.
        int[][] giftCounts = new int[friends.length][friends.length];
        for (String gift : gifts) {
            String[] names = gift.split(" ");
            String fromName = names[0];
            String toName = names[1];
            
            int fromIndex = friendIndexMap.get(fromName);
            int toIndex = friendIndexMap.get(toName);
            
            giftCounts[fromIndex][toIndex]++;
        }
        
        // 각 선물 지수를 구한다.
        int[] giftValues = new int[friends.length];
        for (int row = 0; row < giftCounts.length; row++) {
            for (int col = 0; col < giftCounts[row].length; col++) {
                giftValues[row] += giftCounts[row][col];
                giftValues[col] -= giftCounts[row][col];
            }
        }
           
        /*
        다음 달 가장 많이 선물을 받는 개수를 구한다.
        서로 선물을 줬다면, 서로 선물을 준 개수 중에 큰 쪽이 받는다.
        서로 선물 개수가 같거나 주지 않았다면, 선물 지수가 높은 사람이 받는다.
        */
        int maxNextGiftValue = 0;
        for (int row = 0; row < giftCounts.length; row++) {
            int nextGiftValue = 0;
            
            for (int col = 0; col < giftCounts[row].length; col++) {
                if (giftCounts[row][col] != giftCounts[col][row]) {
                    if (giftCounts[row][col] > giftCounts[col][row]) {
                        nextGiftValue++;
                    }
                    continue;
                }
                
                if (giftValues[row] > giftValues[col]) {
                    nextGiftValue++;
                }
            }
            
            maxNextGiftValue = Math.max(nextGiftValue, maxNextGiftValue);
        }
        
        return maxNextGiftValue;
    }
}
/*
friends
"muzi", "ryan", "frodo", "neo"

muzi: 0
ryan: 1
frodo: 2
neo: 3

gifts
"muzi frodo", 
"muzi frodo", 
"ryan muzi", 
"ryan muzi", 
"ryan muzi", 
"frodo muzi", 
"frodo ryan", 
"neo muzi"

0 0 2 0
3 0 0 0
1 1 0 0
1 0 0 0

row[자신의 인덱스] - col[자신의 인덱스] = 선물 지수
*/