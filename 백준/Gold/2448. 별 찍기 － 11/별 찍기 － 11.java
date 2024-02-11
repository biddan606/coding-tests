import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        // 입력을 받는다.
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        // 맵 사이즈를 구한다.
        int triangleRowSize = 3;
        int triangleColSize = 5;
        int colCenter = 3;
        for (int i = 3; i < n; i *= 2) {
            triangleRowSize = triangleRowSize * 2;
            triangleColSize = triangleColSize * 2 + 1;
            colCenter *= 2;
        }

        // 맵을 그린다.
        char[][] map = new char[triangleRowSize][triangleColSize];
        for (int row = 0; row < map.length; row++) {
            Arrays.fill(map[row], ' ');
        }

        draw(map, 0, 0, triangleRowSize, triangleColSize, colCenter);

        // 결과를 출력한다.
        for (char[] row : map) {
            System.out.println(row);
        }

        reader.close();
    }

    private static void draw(char[][] map, int startRow, int startCol, int rowSize, int colSize, int colCenter) {
        if (colSize == 5) {
            // 삼각형을 그리고 반환한다.
            map[startRow][startCol + 2] = '*';
            map[startRow + 1][startCol + 1] = '*';
            map[startRow + 1][startCol + 3] = '*';
            Arrays.fill(map[startRow + 2], startCol, startCol + 5, '*');
            return;
        }

        int nextRowSize = rowSize / 2;
        int nextColSize = colSize / 2;
        int nextColCenter = colCenter / 2;
        draw(map, startRow, startCol + nextColCenter, nextRowSize, nextColSize, nextColCenter);
        draw(map, startRow + nextRowSize, startCol, nextRowSize, nextColSize, nextColCenter);
        draw(map, startRow + nextRowSize, startCol + nextColSize + 1, nextRowSize, nextColSize, nextColCenter);
    }
}

/*
23 -> 11 -> 5
*/
