import java.util.Arrays;

class Solution {

    private static final int[][] DIRECTS = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };

    public int[] solution(String[][] places) {
        // 결과 초기화
        int[] answer = new int[places.length];
        Arrays.fill(answer, 1);

        // 검증
        for (int i = 0; i < places.length; i++) {
            char[][] placeMap = Arrays.stream(places[i])
                    .map(String::toCharArray)
                    .toArray(char[][]::new);

            for (int row = 0; row < placeMap.length; row++) {
                for (int col = 0; col < placeMap[row].length; col++) {
                    if (!isValidPoint(placeMap, row, col)) {
                        answer[i] = 0;
                        break;
                    }
                }

                if (answer[i] == 0) {
                    break;
                }
            }
        }

        return answer;
    }

    private boolean isValidPoint(char[][] map, int row, int col) {
        char pointState = map[row][col];
        if (pointState != 'P') {
            return true;
        }

        for (int directIndex = 0; directIndex < DIRECTS.length; directIndex++) {
            int nextRow = row + DIRECTS[directIndex][0];
            int nextCol = col + DIRECTS[directIndex][1];
            if (!isValidRange(map, nextRow, nextCol)) {
                continue;
            }

            if (!isValidManhattanDistance(map, nextRow, nextCol, row, col)) {
                return false;
            }
        }

        return true;
    }

    private boolean isValidManhattanDistance(char[][] map, int row, int col, int prevRow, int prevCol) {
        if (map[row][col] == 'P') {
            return false;
        } else if (map[row][col] == 'X') {
            return true;
        }

        for (int directIndex = 0; directIndex < DIRECTS.length; directIndex++) {
            int nextRow = row + DIRECTS[directIndex][0];
            int nextCol = col + DIRECTS[directIndex][1];
            if ((prevRow == nextRow && prevCol == nextCol) || !isValidRange(map, nextRow, nextCol)) {
                continue;
            }

            if (map[nextRow][nextCol] == 'P') {
                return false;
            }
        }

        return true;
    }

    private boolean isValidRange(char[][] map, int row, int col) {
        if (row < 0 || map.length <= row) {
            return false;
        }
        return col >= 0 && map[row].length > col;
    }


    public static void main(String[] args) {
        String[][] places = new String[][]{
                {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
        };

        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(places)));
    }
}
