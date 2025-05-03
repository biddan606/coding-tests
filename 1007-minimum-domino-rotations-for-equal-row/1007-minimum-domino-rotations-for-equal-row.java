class Solution {
    /*
    # 문제 이해
    도미노 2개의 타일, 위 타일 아래 타일로 이루어져 있다
    타일을 회전할 수 있다(위아래 -> 아래위)
    
    위 타일이 모두 같은 수이거나 아래 타일이 모두 같은 수이게 해야 한다
    최소한의 회전수를 반환한다

    1개의 타일의 위아래는 동일한 수일 수 있다

    # 풀이 접근
    최대 도미노의 개수는 2 * 10^4이다 n^2 안됨
    숫자는 1~6까지만 존재한다

    모든 타일을 top or bottom이 같은 숫자여야 한다
    -> 모든 타일에 존재하는 숫자

    해당 숫자가 유니크할까?
    -> 2개의 수로만 이루어져 있다면, 2개가 존재할 수 있다
    -> 하지만 이런 경우, 어떤 쪽의 최소를 구해도 동일하다. 결국 둘 다 일치하기 떄문에

    1. 모든 타일에 존재하는 수를 찾는다
    2. 해당 수를 같은 공간으로 맞추려면 필요한 최소 개수를 구한다
        - top, bottom 둘 중 1곳이 될 수 있음

    # 구현 스텝
    모든 타일에 존재하는 수를 찾는다
        1. 도미노에 등장한 수를 카운트할 map을 생성한다
        2. 모든 도미노를 순회한다
        3. 도미노의 top, bottom 값을 map에 추가한다
            - top == bottom일 경우, 1개의 수만 추가
    해당 수를 같은 공간으로 맞추려면 필요한 최소 개수를 구한다
        4. 도미노 수만큼 등장한 수를 추출한다
        5. 해당 수가 top에 등장한 횟수를 카운트한다
        6. top위치횟수, bottom위치횟수를 모두 구함
            - 겹칠 수 있어서 1개만 구할 수 없음
        7. min(size - top위치횟수, size - bottom위치횟수)을 반환한다
    
    ---
    
    # 다른 풀이
    tops[0], bottoms[0]이 targetNumber가 된다
    -> targetNumber는 모든 도미노에 1개는 존재해야 하므로

    tops[0], bottoms[0]로 counting과 함께 positionCount를 구하면서
    결과가 존재한다면 반환해도 된다
    -> 결과가 존재한다는 의미는 해당 수가 targetNumber라는 것
    */
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int size = tops.length;
        int[] countsByNumber = new int[7];

        for (int i = 0; i < size; i++) {
            int topNumber = tops[i];
            int bottomNumber = bottoms[i];

            countsByNumber[topNumber]++;
            if (topNumber != bottomNumber) {
                countsByNumber[bottomNumber]++;
            }
        }

        int targetNumber = -1;
        for (int n = 1; n <= 6; n++) {
            if (countsByNumber[n] == size) {
                targetNumber = n;
                break;
            }
        }
        
        if (targetNumber == -1) {
            return -1;
        }

        int topPositionCount = 0;
        int bottomPositionCount = 0;
        for (int i = 0; i < size; i++) {
            if (tops[i] == targetNumber) {
                topPositionCount++;                
            }
            if (bottoms[i] == targetNumber) {
                bottomPositionCount++;
            }
        }

        return Math.min(size - topPositionCount, size - bottomPositionCount);
    }
}
