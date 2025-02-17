class Solution {
    /*
    문제
    r1과 r2 사이의 x, y 모두 정수인 좌표들을 개수를 반환해야 한다
    r1 < r2
    
    접근
    - 반환 타입은 long이다
    - 원은 영점으로부터 일정 거리에 있는 점들로 이루어져 있다
    - 일정 거리 이상(r1) 일정 거리 이하(r2)의 점들을 반환하면 되지 않을까?
    
    직관
    1. r2 지름의 정사각형을 만든다
    2. 정사각형 안에 있는 (x, y) 좌표를 체크한다
    3. (x, y) 좌표의 길이가 r1 반지름 이상이고, r2 반지름 이하라면 
        카운트를 증가시킨다
        
    최적화
    - r2 지름의 정사각형과 r1 - 1 지름의 정사각형에서 비교한다
    - 1사분면만 비교하고 * 4
    
    시간 초과 예제
    r1 = 1, r2 = 1_000_000이면 1_000_000^2을 순회함
    
    접근2
    - 1번은 순회할 수 있다
    - 거리 공식이 sqrt(x * x + y + y)니까
        이걸 이용해서 특정 x 좌표의 y 정수를 센다
    
    직관2
    1. x 좌표를 순회한다 (x는 1부터 순회한다, 1사분면)
    2. y의 최대값 - y의 최소값 + 1을 추가한다
    3. 결과를 반환한다
    */
    public long solution(long r1, long r2) {
        long result = 0;
        long maxSquare = r2 * r2;
        long minSquare = r1 * r1;
        
        for (long x = 1; x <= r2; x++) {
            int maxY = (int) Math.sqrt(maxSquare - x * x);
            int minY = (int) Math.ceil(Math.sqrt(minSquare - x * x));
            
            result += maxY - minY + 1;
        }
        return result * 4;
    }
}
