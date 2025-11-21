import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N; // 퀸의 개수 (보드 크기)
    static int count = 0; // 경우의 수

    // O(1) 검증을 위한 방문 체크 배열들
    static boolean[] colCheck;      // 열(Column) 점유 여부
    static boolean[] diagCheck1;    // ↗ 대각선 (row + col 이 같음)
    static boolean[] diagCheck2;    // ↘ 대각선 (row - col 이 같음)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 배열 초기화
        colCheck = new boolean[N];
        diagCheck1 = new boolean[2 * N];
        diagCheck2 = new boolean[2 * N];

        dfs(0);

        System.out.println(count);
    }

    private static void dfs(int row) {
        // Base Case: 모든 행에 퀸을 다 놓았을 때
        if (row == N) {
            count++;
            return;
        }

        for (int col = 0; col < N; col++) {
            // 가지치기 (Pruning): 놓을 수 없는 위치면 스킵
            if (isAttacked(row, col)) {
                continue;
            }

            // 상태 표시 (Marking)
            mark(row, col, true);

            // 재귀 호출 (다음 행으로 이동)
            dfs(row + 1);

            // 상태 복구 (Backtracking)
            mark(row, col, false);
        }
    }

    // 현재 위치가 공격받는지 O(1)에 확인
    private static boolean isAttacked(int row, int col) {
        // diagCheck2의 인덱스는 음수가 될 수 있으므로 + N을 더해 보정
        return colCheck[col] || diagCheck1[row + col] || diagCheck2[row - col + N];
    }

    // 상태 기록 및 복구
    private static void mark(int row, int col, boolean status) {
        colCheck[col] = status;
        diagCheck1[row + col] = status;
        diagCheck2[row - col + N] = status;
    }
}