/*
# 문제 이해

- 일주일 동안 출근 희망 시각 + 10분까지 출근해야 함
- 만족한 직원들을 개수 반환

# 고려해야 할 점
schedules의 크기는 최대 1000
timelogs은 각 7이 최대이다
-> 시간 복잡도를 고려하지 않아도 된다

schedules[i] 최소값은 700, 최대값은 1100이다 
-> 시간이 범위를 초과하는 것은 고려하지 않아도 된다
-> 분은 초과할 수 있음(55분 -> 05분)

startday은 토요일, 일요일을 고려할 때 사용
-> 6, 7은 계산 X

# 풀이
1. 출근 희망 시간 -> 제한 시간으로 변경
2. 모든 index가 true인 배열 선언 -> 상품 수령 여부 배열
3. startday가 6, 7을 제외한 것들 중 제한 시간을 넘으면 상품 수령 여부 false 반환
4. 상품 수령 여부 true의 개수 반환
*/
function solution(schedules, timelogs, startday) {
    const elementCount = schedules.length;
    
    const check = [];
    
    for (let i = 0; i < elementCount; i++) {
        check.push(true);
    }
    
    const limitTimes = [];
    
    for (let i = 0; i < elementCount; i++) {
        let time = schedules[i];
        time += 10;
        
        const carry = Math.floor((time % 100) / 60);
        time -= carry === 1 ? 60 : 0;
        
        const limitTime = time + carry * 100;
        
        limitTimes.push(limitTime);
    }
    
    for (let d = 0; d < 7; d++) {
        if (startday === 6 || startday === 7) {
            startday = (startday % 7) + 1;
            continue;
        }
        
        for (let i = 0; i < elementCount; i++) {
            const timeOfDay = timelogs[i][d];
            
            if (timeOfDay > limitTimes[i]) {
                check[i] = false;
            }
        }
        
        startday = (startday % 7) + 1;
    }
    
    return check.filter(t => t === true).length;
}
