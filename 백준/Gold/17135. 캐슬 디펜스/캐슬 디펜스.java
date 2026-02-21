import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
# 문제
grid 안에 적들이 배치된다. 마지막 row 뒤 추가 row에 궁수가 배치된다.
궁수는 d 범위 안의 적들을 공격할 수 있다. 범위 계산 방법은 |row1 - row2| + |col1 - col2|이다.
가장 가까운 적을 공격하고, 가까운 적이 여러명이라면 가장 왼쪽에 있는 적을 공격한다.
턴당 궁수는 적을 공격하며 공격한 즉시 죽는다. 단, 같은 턴에 같은 적을 공격할 수도 있다.

궁수의 공격이 끝나면, 적은 아래로 한칸 이동하는데, 성으로 오면 게임에서 제외된다.

궁수가 공격으로 제거할 수 있는 적의 최대 수를 출력해야 한다.

# 접근
행과 열의 개수는 최대 15로 최대 15턴동안 진행될 수 있다.
궁수는 3명이고 15개의 위치에 존재할 수 있으므로 15*14*13 경우의 수가 존재한다.

dfs로 궁수의 위치를 할당하고, 모든 적이 사라질 때까지 턴을 진행하면,
각 위치에 따른 적 제거 수를 알 수 있다.

# 구현
1. 입력: 행,열, 궁수 공격 제한 거리, 각 cell의 값
2. 게임 진행시 사용할 적군의 위치 저장
3. dfs로 궁수 위치 배정
4. 모든 궁수 위치가 배정되었으면, 적이 사라질 때까지 게임 진행
    1. 적군의 위치를 기준으로 궁수가 공격
        - 궁수 사정거리 안에 있어야 함
        - 궁수 사정거리에 들어온 경우만 공격 가능
        - 공격 가능한 적이 여러명이라면, 가장 가까우면서 왼쪽 적 공격
        - 궁수가 같은 적을 공격할 수도 있음
    2. 적군의 위치 아래로 한칸 이동
    3. 모든 적군이 사라질 때까지 반복
    4. 제거된 적 수가 최대값이라면 갱신
5. 출력: {$제거된_최대_적_수}
 */
public class Main {

    static int rowSize;
    static int colSize;
    static int attackLimit;

    static List<int[]> enemyPositions;

    static int maxKilledEnemyCount;

    public static void main(String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // 행,열, 궁수 공격 제한 거리 입력
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        rowSize = Integer.parseInt(tokenizer.nextToken());
        colSize = Integer.parseInt(tokenizer.nextToken());
        attackLimit = Integer.parseInt(tokenizer.nextToken());

        // 각 cell의 값 입력
        String[][] grid = new String[rowSize][];
        for (int r = 0; r < rowSize; r++) {
            grid[r] = reader.readLine().split(" ");
        }
        reader.close();

        // 적군의 위치 저장
        // 왼쪽부터 저장하여, 먼저 선택되면 갱신되지 않도록 함
        enemyPositions = new ArrayList<>();
        for (int c = 0; c < colSize; c++) {
            for (int r = 0; r < rowSize; r++) {
                if (grid[r][c].equals("1")) {
                    enemyPositions.add(new int[]{r, c});
                }
            }
        }

        // dfs로 궁수 위치 배정
        maxKilledEnemyCount = 0;
        dfs(0, new ArrayList<>());

        System.out.println(maxKilledEnemyCount);
    }

    private static void dfs(int startCol, List<int[]> archerPoints) {
        // 3명이 배치되면 게임 진행
        if (archerPoints.size() == 3) {
            playGame(archerPoints);
            return;
        }

        for (int col = startCol; col < colSize; col++) {
            int[] newArcherPoint = {rowSize, col};
            archerPoints.add(newArcherPoint);

            dfs(col + 1, archerPoints);

            archerPoints.remove(archerPoints.size() - 1);
        }
    }

    private static void playGame(List<int[]> archerPoints) {
        // 사용할 적군 위치 초기화
        Queue<int[]> aliveEnemies = new ArrayDeque<>();
        for (int[] e : enemyPositions) {
            aliveEnemies.offer(new int[]{e[0], e[1]});
        }

        // 모든 적군이 사라질 때까지 게임 진행
        int killedEnemyCount = 0;
        while (!aliveEnemies.isEmpty()) {
            Set<int[]> attackEnemies = new HashSet<>();

            for (int[] a : archerPoints) {
                int[] attackEnemy = null;
                int minDistance = Integer.MAX_VALUE;

                for (int[] e : aliveEnemies) {
                    int distance = calcaulateDistance(a, e);
                    if (distance > attackLimit || distance >= minDistance) {
                        continue;
                    }

                    minDistance = distance;
                    attackEnemy = e;
                }

                if (attackEnemy == null) {
                    continue;
                }

                attackEnemies.add(attackEnemy);
            }

            killedEnemyCount += attackEnemies.size();
            aliveEnemies.removeAll(attackEnemies);

            int aliveEnemyCount = aliveEnemies.size();
            for (int i = 0; i < aliveEnemyCount; i++) {
                int[] e = aliveEnemies.poll();
                e[0]++;
                if (e[0] >= rowSize) {
                    continue;
                }

                aliveEnemies.offer(e);
            }
        }

        maxKilledEnemyCount = Math.max(maxKilledEnemyCount, killedEnemyCount);
    }

    private static int calcaulateDistance(int[] a, int[] e) {
        return Math.abs(a[0] - e[0]) + Math.abs(a[1] - e[1]);
    }
}
