class Solution {
    /*
    1. skill을 사용하여 건물을 공격하거나 회복시킨다.
    2. 파괴되지 않은 건물 개수를 반환한다.(파괴되지 않은 건물은 체력이 1이상)

    스킬은 최대 250_000 입니다. r1 - r2, c1 - c2의 범위도 최대 1_000 입니다.
    단순 채우기의 형식으로 했을 때는 250_000 * 1_000 * 1_000 의 반복을 하게 됩니다.
    사각형의 범위를 양 4점을 표시하고 마지막 단계에서 한꺼번에 해당 값을 반영합니다.

    못 풀어서 정답 봄
    생각 흐름:
    - 스킬당 스킬 값을 채우려면 너무 많은 연산이 필요하다.
    - 점을 이용해 스킬 값을 할당해보자
    - 현재 문제는 2차원이지만, 작은 문제부터 시작해보자
    - 1차원이라고 본다면 시작과 끝을 찍어두고 배열을 순회하여 누적합을 만들 수 있다. 이걸 2차원 배열로 바꿔보자
    - 2차원 배열에서 누적합을 만들기 위해서는 시작점과 끝나는 점을 행 종료 다음 지점과 열 종료 다음 지점에 둘 수 있다.
    - 직사가형으로 누적이 되기 위해서 (x - 1, y - 1), (x, y - 1), (x - 1, y) 의 값을 이용해야 한다.
    - (x, y - 1), (x - 1, y) 값을 합치면 대각이 두배가 되므로 (x - 1, y - 1) 를 빼주어 대각 값을 상쇄시켜준다.
    - 행 종료 다음 지점과 열 종료 다음 지점에 누적을 제거하기 위해 반대가 되는 값을 넣었는데, 두 지점의 누적 값을 합치는 지점에서 둘 다 빼게 된다.
        이를 방지하기 위해 만나는 지점에 원래 누적 값을 추가로 넣는다.

    풀이:
    1. (r1, c1), (r1 + 1, c1 + 1) 스킬 값, (r1 + 1, c1), (r1, c1 + 1) -스킬 값을 넣는다.
    2. 배열을 순회하며 누적합으로 변환한다. (x - 1, y - 1) - (x - 1, y) - (x, y - 1)
    3. 보드와 누적합이 1이상인 값들의 개수를 세어 반환합니다.
    */
    
    private static final int REPAIR_NUMBER = 2;
    
    public int solution(int[][] board, int[][] skills) {
        int[][] changes = new int[board.length + 1][board[0].length + 1];
        for (int[] skill : skills) {
            int type = skill[0];
            int r1 = skill[1];
            int c1 = skill[2];
            int r2 = skill[3];
            int c2 = skill[4];
            
            int degree = skill[5];
            if (type == REPAIR_NUMBER) {
                degree = -degree;
            }
            
            changes[r1][c1] += degree;
            changes[r2 + 1][c2 + 1] += degree;
            changes[r1][c2 + 1] -= degree;
            changes[r2 + 1][c1] -= degree;
        }
        
        for (int r = 0; r < changes.length; r++) {
            for (int c = 0; c < changes[r].length; c++) {
                int diagonal = 0;
                if (r - 1 >= 0 && c - 1 >= 0) {
                    diagonal = changes[r - 1][c - 1];
                }
                
                int up = 0;
                if (r - 1 >= 0) {
                    up = changes[r - 1][c];
                }
                
                int left = 0;
                if (c - 1 >= 0) {
                    left = changes[r][c - 1];
                }
                
                changes[r][c] += left + up - diagonal;
            }
        }
        
        int result = 0;
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] - changes[r][c] >= 1) {
                    result++;
                }
            }
        }
        
        return result;
    }
}