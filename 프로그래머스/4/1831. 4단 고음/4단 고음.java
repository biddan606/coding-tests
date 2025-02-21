class Solution {
    /*
    문제 이해
    `*`, `+`로 이루어진 문자열을 만들 수 있다
    문자열은 패턴이 있고, 숫자로 변환이 가능하다
    n이 될 수 있는 문자열은 총 몇개인가?
    
    올바른 문자열
    - `*`이 오면 `+` 2개가 누적된다
    - `+`는 누적되었을 떄만 사용할 수 있다
    - n이 되었을 때 `+` 누적 값이 0이어야 한다
    
    접근
    `**++++` = 13, `*++*++` = 17
    `*`을 먼저 사용하는 경우가 더 작은 수가 된다
    3^20 > 2147483647보다 크므로 많은 depth를 탐색하지 않을 것 같다
    백트래킹 사용
    
    구현 스텝
    1. 백트래킹을 이용해 3단 고음 문자열을 만든다
    2-1. n과 같고 올바른 문자열이라면 count를 증가시킨다
    2-2. n과 같지만 올바른 문자열이 아니라면 무시한다
    2-3. n보다 커지면 탐색을 멈춘다
    
    문자열이라고 했지만 `+`개수만 중요하므로 `+`개수만 카운팅한다
    
    ** 시간 초과
    패턴을 발견해야할 거 같다
    2147483647 -> 최대 57 Depth를 가짐
    재귀므로 2^26 시간복잡도를 가짐
    depth를 줄여야 함
    
    정답 보고 힌트 얻음(*, + 카운팅)
    저 카운팅으로 인해 2^26까지 줄어드는지는 모르겠음
    
    구현 스텝
    1. 최대 *개수와 +개수를 구함
        - *개수가 log인 이유: *은 x3인데 *로만 넘을 수 있는 값이 된다면 고음 규칙으로는 항상 그 값을 넘음
            고로 *로만 넘을 수 있는 값보다 작아야 함
    2. 백트래킹 재귀
    3. asterisk, plus 개수를 두어 depth를 한정시킴
    */
    
    private int matchedCount;
    
    public int solution(int n) {
        // 로그 밑 변환 공식
        int asteriskCount = (int) (Math.log10(n) / Math.log10(3));
        int plusCount = asteriskCount * 2;
        
        backtrack(n, asteriskCount, plusCount);
        return matchedCount;
    }
    
    private void backtrack(int target, int asteriskCount, int plusCount) {
        if (target == 1 && asteriskCount * 2 == plusCount) {
            matchedCount++;
            return;
        }
        
        if (target > 1 && plusCount > 0) {
            backtrack(target - 1, asteriskCount, plusCount - 1);
        }
        if (target % 3 == 0 && asteriskCount > 0 && asteriskCount * 2 - 2 >= plusCount) {
            backtrack(target / 3, asteriskCount - 1, plusCount);
        }
    }
}

// * 1개일때 0
// *++ * 2개일 때 2
// *++ *++ * 3개일 때 4
// *++ *++ *++ * 4개일 때 6
