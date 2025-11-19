import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // 가독성을 위해 상수로 정의
    private static final int CANVAS_SIZE = 100;
    private static final int PAPER_SIZE = 10;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        boolean[][] canvas = new boolean[CANVAS_SIZE][CANVAS_SIZE];
        int totalArea = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); // 왼쪽 벽과의 거리
            int y = Integer.parseInt(st.nextToken()); // 아래쪽 벽과의 거리

            // 색종이 크기만큼 영역을 순회
            for (int j = x; j < x + PAPER_SIZE; j++) {
                for (int k = y; k < y + PAPER_SIZE; k++) {
                    // 빈 영역(!canvas[j][k])일 때만 카운트하고 마킹
                    if (!canvas[j][k]) {
                        canvas[j][k] = true;
                        totalArea++;
                    }
                }
            }
        }

        System.out.println(totalArea);
        br.close();
    }
}