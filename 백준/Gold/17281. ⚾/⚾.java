import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
# 문제
각 이닝별로 선수가 공을 친 결과를 알 수 있다.
선수의 타순을 내가 임의로 결정할 수 있다.
-> 1번 타자는 4번 타자로 고정

타순을 변경했을 때, 가장 높은 점수를 얻은 점수를 출력해야 한다.

# 접근
단순 구현 문제다.

## 베이스 볼 게임
1. 각 이닝의 결과를 바탕으로 이닝을 진행한다.
2. 3아웃이 되면 다음 이닝으로 넘어간다.

## 타순 지정
1번은 4번에 고정하고,
나머지 번호는 매번 다르게 한다.

# 풀이

## 베이스 볼
1. 입력: 진행할 이닝 수, 이닝당 타자의 결과
2. 이닝 진행: 타자의 결과대로 이닝 진행
    - 아웃: 0, 안타: 1, 2루타: 2, 3루타: 3, 홈런: 4
    - 결과에 따라 점수를 내거나 베이스를 진행해야 한다.
    - 아웃인 경우 아웃 카운트 증가, 아웃 카운트가 3이면 이닝 종료
3. 스코어를 반환한다.

## 타순 지정
1. 베이스 볼을 진행하기 전에 한다.
2. visited를 두고, 방문하지 않았으면서 가능한 숫자를 차례대로 배정한다.
3. 모두 배정이 끝났다면 게임을 진행한다.
 */
public class Main {

    private static final int BATTER_COUNT = 9;
    private static int inningCount;
    private static int[][] batterResultByInning;
    private static boolean[] visited;
    private static int[] lineup;

    public static void main(String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // 입력: 진행할 이닝 수, 이닝당 타자의 결과
        inningCount = Integer.parseInt(reader.readLine());
        batterResultByInning = new int[inningCount][];
        for (int i = 0; i < inningCount; i++) {
            batterResultByInning[i] = parseInts(reader.readLine());
        }
        reader.close();

        // 이닝 진행
        visited = new boolean[BATTER_COUNT];
        lineup = new int[BATTER_COUNT];
        visited[3] = true;
        lineup[0] = 3;
        int maxScore = playBaseBall(1);

        // 결과 출력: 획득한 점수
        System.out.println(maxScore);
    }

    private static int playBaseBall(int batterIndexToAssign) {
        if (batterIndexToAssign >= BATTER_COUNT) {
            return playBaseBall();
        }

        int maxScore = 0;
        for (int i = 0; i < BATTER_COUNT; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            lineup[i] = batterIndexToAssign;

            maxScore = Math.max(maxScore, playBaseBall(batterIndexToAssign + 1));

            visited[i] = false;
        }

        return maxScore;
    }

    private static int playBaseBall() {
        int score = 0;
        int cursor = 0;
        for (int currentInning = 0; currentInning < inningCount; currentInning++) {
            int[] batterResult = batterResultByInning[currentInning];
            boolean[] bases = new boolean[4];
            int outCount = 0;
            while (outCount < 3) {
                int hit = batterResult[lineup[cursor]];
                if (hit == 0) {
                    outCount++;
                } else {
                    for (int baseIndex = 3; baseIndex >= 1; baseIndex--) {
                        if (!bases[baseIndex]) {
                            continue;
                        }

                        bases[baseIndex] = false;
                        int toIndex = hit + baseIndex;

                        if (toIndex >= 4) {
                            score++;
                        } else {
                            bases[toIndex] = true;
                        }
                    }

                    if (hit >= 4) {
                        score++;
                    } else {
                        bases[hit] = true;
                    }
                }

                cursor = (cursor + 1) % BATTER_COUNT;
            }
        }

        return score;
    }

    private static int[] parseInts(String str) {
        return Arrays.stream(str.split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
