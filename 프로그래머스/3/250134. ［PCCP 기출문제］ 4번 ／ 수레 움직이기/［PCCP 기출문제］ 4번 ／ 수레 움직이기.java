import java.util.*;

class Solution {

    public static int N, M, Ans = Integer.MAX_VALUE;
    public static int[][] Map, Red, Blue;
    public static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    public static int rx, ry, bx, by;

    // DFS 함수로 두 수레의 이동을 처리
    public static void Dfs(int count, int x1, int y1, int x2, int y2) {

        // 두 수레가 각각 도착지에 도착한 경우 최소 턴 갱신
        if (Map[x1][y1] == 3 && Map[x2][y2] == 4) {
            Ans = Math.min(Ans, count);
            return;
        }

        // 빨간 수레만 도착지에 있을 경우
        if (Map[x1][y1] == 3) {
            for (int i = 0; i < 4; i++) {
                int nx = x2 + dx[i];
                int ny = y2 + dy[i];
                if (nx < 1 || ny < 1 || nx > N || ny > M) continue;
                if (Blue[nx][ny] == 1 || Map[nx][ny] == 3 || Map[nx][ny] == 5) continue;
                Blue[nx][ny] = 1;
                Dfs(count + 1, x1, y1, nx, ny);
                Blue[nx][ny] = 0;
            }
        }
        // 파란 수레만 도착지에 있을 경우
        else if (Map[x2][y2] == 4) {
            for (int i = 0; i < 4; i++) {
                int nx = x1 + dx[i];
                int ny = y1 + dy[i];
                if (nx < 1 || ny < 1 || nx > N || ny > M) continue;
                if (Red[nx][ny] == 1 || Map[nx][ny] == 4 || Map[nx][ny] == 5) continue;
                Red[nx][ny] = 1;
                Dfs(count + 1, nx, ny, x2, y2);
                Red[nx][ny] = 0;
            }
        }
        // 두 수레가 모두 이동해야 하는 경우
        else {
            for (int i = 0; i < 4; i++) {
                int nx = x1 + dx[i];
                int ny = y1 + dy[i];
                if (nx < 1 || ny < 1 || nx > N || ny > M || Red[nx][ny] == 1 || Map[nx][ny] == 5) continue;

                for (int j = 0; j < 4; j++) {
                    int nnx = x2 + dx[j];
                    int nny = y2 + dy[j];
                    if (nnx < 1 || nny < 1 || nnx > N || nny > M || Blue[nnx][nny] == 1 || Map[nnx][nny] == 5) continue;
                    if (nx == nnx && ny == nny) continue; // 같은 칸에 있을 수 없음
                    if (nx == x2 && ny == y2 && nnx == x1 && nny == y1) continue; // 서로 자리를 바꾸는 경우 방지

                    Red[nx][ny] = 1;
                    Blue[nnx][nny] = 1;
                    Dfs(count + 1, nx, ny, nnx, nny);
                    Red[nx][ny] = 0;
                    Blue[nnx][nny] = 0;
                }
            }
        }
    }

    // 메인 함수로 미로 탐색을 시작
    public int solution(int[][] maze) {
        N = maze.length;
        M = maze[0].length;
        Map = new int[N + 1][M + 1];
        Red = new int[N + 1][M + 1];
        Blue = new int[N + 1][M + 1];

        // 미로 초기화 및 수레의 시작 위치 설정
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                Map[i][j] = maze[i - 1][j - 1];
                if (Map[i][j] == 1) {
                    Red[i][j] = 1;
                    rx = i;
                    ry = j;
                }
                if (Map[i][j] == 2) {
                    Blue[i][j] = 1;
                    bx = i;
                    by = j;
                }
            }
        }

        // DFS 탐색 시작
        Dfs(0, rx, ry, bx, by);

        // 결과 반환 (최솟값이 갱신되지 않았다면 도달할 수 없으므로 0 반환)
        return Ans == Integer.MAX_VALUE ? 0 : Ans;
    }
}