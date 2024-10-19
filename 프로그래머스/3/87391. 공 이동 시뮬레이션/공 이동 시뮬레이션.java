class Solution {

    public long solution(int rows, int cols, int destinationRow, int destinationCol, int[][] queries) {
        /*
        endPoint 지점부터 queries를 역순으로 순회한다.
        direction 역방향의 끝지점이라면, direction 방향으로 범위를 확장한다.
        점에서 시작하여, 점 또는 사각형을 가지게 된다.
        반환값은 사각형의 넓이이다.
        */
        int minRow = destinationRow;
        int maxRow = destinationRow;
        int minCol = destinationCol;
        int maxCol = destinationCol;

        for (int i = queries.length - 1; i >= 0; i--) {
            int direction = queries[i][0];
            int distance = queries[i][1];

            if (direction == 0) { // direction: 0, col를 증가시킨다.
                maxCol = Math.min(cols - 1, maxCol + distance);
                if (minCol != 0) {
                    minCol = minCol + distance;
                }
            } else if (direction == 1) { // direction: 1, col를 감소시킨다.
                minCol = Math.max(0, minCol - distance);
                if (maxCol != cols - 1) {
                    maxCol = maxCol - distance;
                }
            } else if (direction == 2) { // direction: 2, row를 증가시킨다.
                maxRow = Math.min(rows - 1, maxRow + distance);
                if (minRow != 0) {
                    minRow = minRow + distance;
                }
            } else if (direction == 3) { // direction: 3, row를 감소시킨다.
                minRow = Math.max(0, minRow - distance);
                if (maxRow != rows - 1) {
                    maxRow = maxRow - distance;
                }
            }

            if (minRow > rows - 1 || maxRow < 0 || minCol > cols - 1 || maxCol < 0) {
                return 0;
            }
        }

        return (long) (maxRow - minRow + 1) * (maxCol - minCol + 1);
    }

    public static void main(String[] args) {
        int rows = 2;
        int cols = 2;
        int destinationRow = 0;
        int destinationCol = 0;
        int[][] queries = {{1, 1}};

        long result = new Solution().solution(rows, cols, destinationRow, destinationCol, queries);
        System.out.println(result);
    }
}
