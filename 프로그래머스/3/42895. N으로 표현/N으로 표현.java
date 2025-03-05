import java.util.*;

class Solution {
    /*
    # 문제 이해
    N이라는 숫자만을 이용해서 number를 만들어야 한다
    N을 가장 적게 사용하여 number를 만들고 사용 횟수를 반환해야 한다
    - 최소값이 8보다 크다면 -1 반환
    
    # 접근
    - N은 한자리 수이다
    - depth가 8까지밖에 안되므로 어떤 방법을 사용해도 될 듯
    - 각 depth별로 만들 수 있는 수 저장하여 이용
        depth = 1은 항상 자기 자신
        depth = 2는 두수를 겹치거나 depth1 조합
        ...
    
    12 = 5 + 5 (5 / 5) + (5 / 5)
        depth1조합 + depth3개조합 + depth2조합
    12 = 55 / 5 + 5 / 5
        depth3조합 + depth1조합 + depth2조합
    
    # 구현 스텝
    1. n 개수별로 만들 수 있는 조합을 추가한다
        - 작은 개수부터 큰 개수 순으로
    2. 작은 개수부터 number와 매칭되는 게 있는지 찾아본다
    3. 매칭이 있다면 해당 개수, 없다면 -1 반환한다
    
    - n 개수는 Set으로 저장하는 게 나을 듯 겹치는 것이 없으므로
    
    # 최적화
    - 통과하나 사실상 시간 초과(시간을 넉넉하게 주어서 맞은 듯, 3초 걸림)
    - dfs로 푸니 많은 시간이 걸림
        -> 아마도 많은 값들이 중첩되어 있어서 생각이상의 반복이 나오는 듯
    - 총 몇번의 연산이 필요할지 계산할 수 없음
    
    # 현재 코드
    1개조합 + 1개조합 + 1개조합 + 1개조합 -> 4개조합에 추가함
    
    #최적화 코드
    1개조합 + 1개조합 + 1개조합 + 1개조합를 다르게 바라볼 수 있음
    결국 이것들은 묶일테고 2개로만 나누어짐 
    (1개조합 + 1개조합) + (1개조합 + 1개조합)
    (1개조합 + 1개조합) + 1개조합) + (1개조합)
    
    이를 이용해서 for문 2개로 최적화
    
    # 최적화 구현 스텝
    1. 1. n 개수별로 만들 수 있는 조합을 담을 것을 생성한다(초기값 추가 n=5 c=1 -> 5, n=5 c=2 -> 55)
    2. 이중 for문을 돌리며 작은 조합부터 큰 조합들을 추가함
    3. 작은 조합부터 매칭 확인, 매칭이 된다면 해당 count 반환, 없다면 -1 반환
    */
    private static final int MAX_COUNT = 8;
    
    public int solution(int N, int number) {
        Set<Integer>[] valuesByCount = new Set[MAX_COUNT + 1];
        int initialValue = N;
            
        for (int c = 1; c <= MAX_COUNT; c++) {
            valuesByCount[c] = new HashSet<>();
            valuesByCount[c].add(initialValue);
            
            initialValue = initialValue * 10 + N;
        }
        
        for (int targetCount = 2; targetCount <= MAX_COUNT; targetCount++) {
            for (int firstCount = 1; firstCount < targetCount; firstCount++) {
                int secondCount = targetCount - firstCount;
                
                for (int firstValue : valuesByCount[firstCount]) {
                    for (int secondValue : valuesByCount[secondCount]) {
                        valuesByCount[targetCount].add(firstValue + secondValue);
                        valuesByCount[targetCount].add(firstValue - secondValue);
                        valuesByCount[targetCount].add(firstValue * secondValue);
                        if (secondValue != 0) {
                            valuesByCount[targetCount].add(firstValue / secondValue);
                        }
                    }
                }
            }
        }
        
        for (int c = 1; c <= MAX_COUNT; c++) {
            if (valuesByCount[c].contains(number)) {
                return c;
            }
        }
        return -1;
    }
}
