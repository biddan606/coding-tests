import java.util.*;

class Solution {
    public String solution(int[] numbers, String hand) {
        // 휴대폰 번호 위치를 매핑한다.
        Map<Integer, int[]> numberMap = new HashMap<>();
        numberMap.put(1, new int[]{0, 0});
        numberMap.put(2, new int[]{0, 1});
        numberMap.put(3, new int[]{0, 2});
        numberMap.put(4, new int[]{1, 0});
        numberMap.put(5, new int[]{1, 1});
        numberMap.put(6, new int[]{1, 2});
        numberMap.put(7, new int[]{2, 0});
        numberMap.put(8, new int[]{2, 1});
        numberMap.put(9, new int[]{2, 2});
        numberMap.put(0, new int[]{3, 1});
        
        // 왼손, 오른손 위치를 초기화한다.
        int[] leftHandLocation = new int[]{3, 0};
        int[] rightHandLocation = new int[]{3, 2};
        
        // 버튼을 누른다.
        StringBuilder result = new StringBuilder();
        for (int number : numbers) {
            int[] numberLocation = numberMap.get(number);
            
            // 누를 손가락을 얻는다.
            double leftHandWeight = 
                Math.abs(leftHandLocation[0] - numberLocation[0]) + Math.abs(leftHandLocation[1] - numberLocation[1]);
            double rightHandWeight = 
                Math.abs(rightHandLocation[0] - numberLocation[0]) + Math.abs(rightHandLocation[1] - numberLocation[1]);
            switch(hand) {
                    case "left" -> leftHandWeight -= 0.5;
                    case "right" -> rightHandWeight -= 0.5;
            }
            
            char selectedHand = 'L';
            if (number == 1 || number == 4 || number == 7) {
                selectedHand = 'L';
            } else if (number == 3 || number == 6 || number == 9) {
                selectedHand = 'R';
            } else if (leftHandWeight > rightHandWeight) { // 2, 5, 8, 0
                selectedHand = 'R';
            }
            result.append(selectedHand);
            
            // 누른 손가락은 위치를 변경한다.
            switch(selectedHand) {
                    case 'L' -> leftHandLocation = numberLocation;
                    case 'R' -> rightHandLocation = numberLocation;
            }
        }
        
        // 누른 손 순서를 반환한다.
        return result.toString();
    }
}