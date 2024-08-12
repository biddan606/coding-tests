import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(int totalStages, int[] playerStages) {
        // 각 스테이지를 만든다.
        List<Stage> stages = IntStream.rangeClosed(1, totalStages)
                        .mapToObj(Stage::new)
                        .collect(Collectors.toList());
        
        // 스테이지의 플레이한 플레이어 수를 구한다.
        for (int s : playerStages) {
            for (int i = 0; i < Math.min(s, totalStages); i++) {
                stages.get(i).totalPlayers++;
            }
        }
        
        // 해당 스테이지에 정체중인 플레이어 수를 구한다.
        Arrays.stream(playerStages)
              .filter(s -> s <= totalStages)
              .forEach(s -> stages.get(s - 1).stayingPlayers++);
        
        // 실패율, number 기준으로 정렬한다.
        // 실패율 내림차순, 같을 경우 number 오름차순
        stages.sort(Comparator
                    .comparingDouble(Stage::getFailureRate).reversed()
                    .thenComparingInt(stage -> stage.number));
        
        return stages.stream().mapToInt(stage -> stage.number).toArray();
    }
    
    private class Stage {
        final int number;
        int stayingPlayers;
        int totalPlayers;
        
        public Stage(int number) {
            this.number = number;
        }
        
        public double getFailureRate() {
            if (totalPlayers == 0) {
                return 0d;
            }
            
            return stayingPlayers/ (double) totalPlayers;
        }
    }
}
