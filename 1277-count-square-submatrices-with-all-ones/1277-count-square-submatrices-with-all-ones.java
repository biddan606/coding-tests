class Solution {
    /*
    # 문제 이해
    matrix에서 1로 이루어진 정사각형 개수를 반환한다.
    -> 1개만 있더라도 정사각형이다.

    # 제약
    rows, cols <= 300이다.
    matrix의 최대개수는 90_000이 될 수 있다.

    시간 복잡도는 O(NlogN)까지 허용 가능하다.

    공간 복잡도는 고려할 필요 없다. 
    -> 고려할 정도로 많은 메모리를 사용할 거 같지 않다.

    # 구현 방법 고민
    1 -> 4 -> 9로 더 큰 정사각형이 될 수 있다.
    
    1. 첫 지점인 1을 찾는다.
    2. 1지점에서 아래, 오른쪽, 오른 대각선을 추가한다.
        - 추가할 수 있는 경우는 각 지점이 1인 경우이다.
        - visited 지점은 추가하지 않아도 된다.
        - 왼쪽 위에서부터 탐색할 것이고 탐색된 지점은 visited 처리할 것이기 때문에, 중복은 없다.
        - 1개라도 추가할 수 없다면, 더 커질 수 없는 정사각형이므로, 모두 visited 처리를 풀어야 한다.
    3. 최대 정사각형의 정사각형 개수를 구한다.
        - 1 -> 1, 4 -> 1 + 4, 9 -> 1 + 4 + 9, 16 -> 1 + 4 + 9 + 16
    
    # 구현 방법의 문제점

    [
        [1,0,1,0,1],
        [1,0,0,1,1],
        [0,1,0,1,1],
        [1,0,0,1,1]
    ]

    위 예제를 통과하지 못한다. 
    row=2,col=3일 때 방문해서 2 길이의 정사각형을 발견해야 한다.
    하지만 현재 코드는 이미 방문한 포인트라서 방문하지 않는다.

    이런 문제가 생긴 이유는 구현 방식을 생각하고, 반례를 생각하지 않아서이다.

    # 정답 구현 방법

    dp를 활용해서 풀 수 있다.
    원래 구현 방법에서는 아래, 오른쪽, 아래오른 대각을 다음 포인트로 지정했다.
    dp에서는 위,왼쪽,위왼쪽 대각에서 최소값+1로 갱신하면 된다.

    ## 정사각형의 개수는 어떻게 구할까?
    갱신된 값을 그대로 추가하면 된다.

    [1, 1, 1]
    [1, 1, 1]
    [1, 1, 1]

    와 같은 length=3 정사각형이 있다면,

    [1, 1, 1]
    [1, 2, 2]
    [1, 2, 3]

    dp 값이 만들어진다.
    그러므로 각 갱신값을 그대로 추가해주면 된다.
    */
    public int countSquares(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] dp = new int[rows][cols];
        int totalSquares = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (matrix[r][c] == 0) {
                    continue;
                }

                if (r == 0 || c == 0) {
                    dp[r][c] = 1;
                } else {
                    dp[r][c] = min(dp[r - 1][c], dp[r - 1][c - 1], dp[r][c - 1]) + 1;
                }

                totalSquares += dp[r][c];
            }
        }

        return totalSquares;
    }

    private int min(int v1, int v2, int v3) {
        int result = Integer.MAX_VALUE;

        result = Math.min(result, v1);
        result = Math.min(result, v2);
        result = Math.min(result, v3);
        
        return result;
    }
}
