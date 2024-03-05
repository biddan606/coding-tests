import java.util.*;
import java.lang.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        // 각 번호별로 입출차 시간을 얻는다.
        Map<String, List<String>> timesByCarNumber = new HashMap<>();
        for (String line : records) {
            String[] tokens = line.split(" ");
            String time = tokens[0];
            String carNumber = tokens[1];
            
            timesByCarNumber.computeIfAbsent(carNumber, k -> new ArrayList<>())
                .add(time);
        }
        
        // 각 번호별 요금을 계산한다.
        int defaultTime = fees[0];
        int defaultFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];
        
        Map<String, Integer> feeByCarNumber = new HashMap<>();
        for (Map.Entry<String, List<String>> e : timesByCarNumber.entrySet()) {
            String carNumber = e.getKey();
            List<String> times = e.getValue();
            
            int totalTime = 0;
            for (int i = 0; i < times.size(); i += 2) {
                String startTime = times.get(i);
                
                String endTime = "23:59";
                if (i + 1 < times.size()) {
                    endTime = times.get(i + 1);
                }
                
                totalTime += calculateTimeDifference(startTime, endTime);
            }
            
            feeByCarNumber.put(carNumber, calculateFee(defaultTime, defaultFee, unitTime, unitFee, totalTime));
        }
        
        System.out.println(feeByCarNumber);
        
        
        // 번호 오름차순으로 정렬한 요금을 반환한다.
        return feeByCarNumber.entrySet().stream()
            .sorted((e1, e2) -> Objects.compare(e1.getKey(), e2.getKey(), String::compareTo))
            .mapToInt(e -> e.getValue())
            .toArray();
    }
    
    private int calculateTimeDifference(String startTime, String endTime) {
        int[] splitedStartTime = Arrays.stream(startTime.split(":"))
            .mapToInt(Integer::parseInt)
            .toArray();
        int start = splitedStartTime[0] * 60 + splitedStartTime[1];
        
        int[] splitedEndTime = Arrays.stream(endTime.split(":"))
            .mapToInt(Integer::parseInt)
            .toArray();
        int end = splitedEndTime[0] * 60 + splitedEndTime[1];
        
        return end - start;
    }
    
    private int calculateFee(int defaultTime, int defaultFee, int unitTime, int unitFee, int time) {
        if (time <= defaultTime) {
            return defaultFee;
        }
        
        int additionalTime = time - defaultTime;
        int additionalFee = ((int) Math.ceil(additionalTime / (double) unitTime)) * unitFee;
        
        return defaultFee + additionalFee;
    }
}