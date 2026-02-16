import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
# 문제
0/1로 표현된 2차원 배열이 있다.
이 배열을 압축할 수 있다.
압축하는 방법은 4분면으로 나눈 뒤 각 분면을 표현하는 방식이다.
압축 표현을 출력해야 한다. "(0(0011)(0(0111)01)1)"

# 접근
Size는 64까지 가질 수 있고, 깊이는 6까지 내려갈 수 있다.
-> 최적화를 처리하지 않아도 된다.

1개까지 내려가서 자기의 숫자를 반환한다음,
4분면이 모두 일치하면 일치하는 숫자로 반환한다.
일치하지 않는다면, "()" 안에 각 숫자들을 배치해서 반환한다.

# 풀이
1. 입력: size와 2차원배열 입력
2. dfs: 압축 결과를 반환
    1. 4분면을 쪼개서 재귀
    2. size가 1이 될 때까지 계속 재귀
        - startRow, startCol
    3. size=1이 되면 현재 문자열 반환
    4. 4분면 비교, 모두 일치하면 1분면 문자열 반환
        - 일치하지 않으면 각 분면의 값을 합친 뒤, "()"로 감싸서 반환
3. 출력: 압축 결과 출력
 */
public class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(reader.readLine());
        char[][] grid = new char[size][];
        for (int row = 0; row < size; row++) {
            grid[row] = reader.readLine().toCharArray();
        }
        reader.close();

        String compressed = compress(grid, 0, 0, size);
        System.out.println(compressed);
    }

    private static String compress(char[][] grid, int startRow, int startCol, int size) {
        // size=1이 되면 현재 문자열 반환
        if (size == 1) {
            return String.valueOf(grid[startRow][startCol]);
        }

        // 4분면을 쪼개서 재귀
        String[] boundaries = new String[4];
        int halfSize = size / 2;
        boundaries[0] = compress(grid, startRow, startCol, halfSize);
        boundaries[1] = compress(grid, startRow, startCol + halfSize, halfSize);
        boundaries[2] = compress(grid, startRow + halfSize, startCol, halfSize);
        boundaries[3] = compress(grid, startRow + halfSize, startCol + halfSize, halfSize);

        // 같다면 1분면 반환
        if (boundaries[0].length() == 1 &&
                boundaries[0].equals(boundaries[1]) &&
                boundaries[0].equals(boundaries[2]) &&
                boundaries[0].equals(boundaries[3])) {
            return boundaries[0];
        }

        // 같지 않다면 각 분면을 합친 뒤, "()"로 감싸서 반환
        StringBuilder mismatchReuslt = new StringBuilder("(");
        for (int boundaryIndex = 0; boundaryIndex < boundaries.length; boundaryIndex++) {
            mismatchReuslt.append(boundaries[boundaryIndex]);
        }
        mismatchReuslt.append(")");
        return mismatchReuslt.toString();
    }
}
