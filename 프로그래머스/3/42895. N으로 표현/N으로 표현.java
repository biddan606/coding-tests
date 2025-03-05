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
        depth1조합 + depth1조합 + depth2조합 + depth2조합
    12 = 55 / 5 + 5 / 5
        depth3조합 + depth1조합 + depth2조합
    
    # 구현 스텝
    1. n 개수별로 만들 수 있는 조합을 추가한다
        - 작은 개수부터 큰 개수 순으로
    2. 작은 개수부터 number와 매칭되는 게 있는지 찾아본다
    3. 매칭이 있다면 해당 개수, 없다면 -1 반환한다
    
    - n 개수는 Set으로 저장하는 게 나을 듯 겹치는 것이 없으므로
    */
    private static final int MAX_COUNT = 8;
    
    public int solution(int N, int number) {
        Set<Integer>[] valuesByCount = new Set[MAX_COUNT + 1];
        for (int c = 1; c <= MAX_COUNT; c++) {
            valuesByCount[c] = new HashSet<>();
            valuesByCount[c].add(Integer.parseInt(String.valueOf(N).repeat(c)));
        }
        
        for (int c = 2; c <= MAX_COUNT; c++) {
            dfs(valuesByCount, c, c, 0);
        }
        
        for (int c = 1; c <= MAX_COUNT; c++) {
            if (valuesByCount[c].contains(number)) {
                return c;
            }
        }
        return -1;
    }
    
    private static void dfs(Set<Integer>[] valuesByCount, int remaingCount, int targetCount, int currentValue) {
        if (remaingCount == 0) {
            valuesByCount[targetCount].add(currentValue);
            return;
        }
        
        for (int c = 1; c <= remaingCount && c < targetCount; c++) {
            for (int value : valuesByCount[c]) {
                dfs(valuesByCount, remaingCount - c, targetCount, currentValue + value);
                dfs(valuesByCount, remaingCount - c, targetCount, currentValue - value);
                dfs(valuesByCount, remaingCount - c, targetCount, currentValue * value);
                if (value != 0) {
                    dfs(valuesByCount, remaingCount - c, targetCount, currentValue / value);                    
                }
            }
        }
    }
}
