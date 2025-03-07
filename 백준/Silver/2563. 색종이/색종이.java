import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {
    /*
    # 문제 이해
    정사각형(크기 100) 도화지 안에 정사각형(크기 10) 검은 색종이를 놓는다
    검은 영역의 넓이를 반환해야 한다

    # 접근
    - 검은 색종이의 수는 100이다 단순 구현으로 풀 수 있다
        시간 복잡도는 고려하지 않아도 된다(N^3까지 가능)
    - 색칠된 영역을 계속 칠해주면 될거 같다

    # 구현 스텝(입력, 출력 설명 X)
    1. 도화지 영역을 생성한다
    2. 검은 색종이로 도화지를 덮는다
        검은 색종이만큼 도화지 영역 값을 true처리
    3. true 처리된 영역의 개수를 구한다
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int blackPaperCount = Integer.parseInt(reader.readLine());
        int[][] blackPapers = new int[blackPaperCount][2];
        for (int i = 0; i < blackPaperCount; i++) {
            blackPapers[i] = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        reader.close();

       boolean[][] whitePaper = new boolean[101][101];
        for (int[] blackPaper : blackPapers) {
            int startRow = blackPaper[0];
            int startCol = blackPaper[1];

            for (int r = startRow; r < startRow + 10 ; r++) {
                for (int c = startCol; c < startCol + 10 ; c++) {
                    whitePaper[r][c] = true;
                }
            }
        }

        int coloredSize = 0;
        for (boolean[] row : whitePaper) {
            for (boolean colored : row) {
                if (colored) coloredSize++;
            }
        }

        System.out.println(coloredSize);
    }
}
