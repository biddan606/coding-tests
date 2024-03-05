import java.util.*;
import java.lang.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        // 각 번호별로 입출차 시간을 얻는다.
        Map<String, Car> cars = new HashMap<>();
        for (String record : records) {
            String[] tokens = record.split(" ");
            int time = parseTime(tokens[0]);
            String carNumber = tokens[1];
            
            cars.putIfAbsent(carNumber, new Car(carNumber));
            Car car = cars.get(carNumber);
            
            boolean isIn = "IN".equals(tokens[2]);
            if (isIn) {
                car.in(time);
            } else {
                car.out(time);
            }
        }

        // 출차가 안된 차들에 "23:59"로 시간을 계산한다.
        cars.values().stream()
            .filter(Car::isParked)
            .forEach(car -> car.out(parseTime("23:59")));

        // 각 번호별 요금을 계산한다.
        Fee fee = new Fee(fees);
        
        return cars.values().stream()
            .sorted(Comparator.comparing(Car::getNumber))
            .mapToInt(car -> fee.cost(car.getParkTime()))
            .toArray();
    }
    
    private int parseTime(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(3));
        
        return hour * 60 + minute;
    }
    
    private static class Car {
        private final String number;
        private int inTime = -1;
        private int parkTime;
        
        public Car(String number) {
            this.number = number;
        }
        
        public void in(int time) {
            if (isParked()) {
                return;
            }
            
            inTime = time;
        }
        
        public void out(int time) {
            if (!isParked()) {
                return;
            }
            
            parkTime += time - inTime;
            inTime = -1;
        }
        
        public boolean isParked() {
            return inTime != -1;
        }
        
        public String getNumber() {
            return number;
        }
        
        public int getParkTime() {
            return parkTime;
        }
    }
    
    private static class Fee {
        private final int baseTime;
        private final int baseFee;
        private final int unitTime;
        private final int unitFee;
        
        public Fee(int[] feeInfo) {
            this.baseTime = feeInfo[0];
            this.baseFee = feeInfo[1];
            this.unitTime = feeInfo[2];
            this.unitFee = feeInfo[3];
        }
        
        public int cost(int time) {
            if (time <= baseTime) {
                return baseFee;
            }
            
            int additionalTime = time - baseTime;
            
            return baseFee + ((int) Math.ceil(additionalTime / (double) unitTime)) * unitFee;
         }
    }
}