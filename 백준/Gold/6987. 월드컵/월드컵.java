import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/*
# 문제
6개의 팀으로 이루어진 4개의 그룹이 있다.
1개의 그룹 안에서 6개의 팀은 다른 팀과 1번씩 경기를 진행한다.
진행 결과에 따라 승/무/패가 결정된다.

각 그룹의 결과가 가능하다면 1, 가능하지 않다면 0을 출력한다.
- ex) "1 1 0 0"

# 접근1
그룹 단위로 검사를 진행하면 된다.
각 팀당 결과가 총 5개인지 확인한다.
    - 그룹에서 치뤄야 할 경기와 팀이 치뤄야 할 경기는 검증 완료

DFS로 1경기씩 무작위로 결정한다.
모든 경기 결정을 다 했다면, 모든 팀들의 승/무/패가 0인지 확인한다.
- 결과는 그룹단위로 이루어지므로, 그룹 단위로 DFS를 진행

# 풀이1
1.
 */
public class Main {
    private static final int GROUP_SIZE = 4;
    private static final int TEAM_SIZE = 6;
    private static final int WIN = 0;
    private static final int DRAW = 1;
    private static final int LOSS = 2;

    static int[][][] record;
    static int[] result;

    public static void main(String[] args) throws IOException {
        readInput();
        validateRecord();
        printResult();
    }

    private static void printResult() {
        System.out.println(Arrays.stream(result)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" ")));
    }

    private static void validateRecord() {
        result = new int[GROUP_SIZE];

        for (int groupIndex = 0; groupIndex < GROUP_SIZE; groupIndex++) {
            result[groupIndex] = validateGroup(groupIndex);
        }
    }

    private static int validateGroup(int groupIndex) {
        return validateGroup(groupIndex, 0, 1);
    }

    private static int validateGroup(int groupIndex, int team1Index, int team2Index) {
        if (team1Index == TEAM_SIZE - 1) {
            // 모든 팀들의 승/무/패 0 확인
            for (int[] team : record[groupIndex]) {
                for (int j : team) {
                    if (j != 0) {
                        return 0;
                    }
                }
            }
            return 1;
        }

        int nextTeam1Index = team2Index == TEAM_SIZE - 1 ? team1Index + 1 : team1Index;
        int nextTeam2Index = team2Index == TEAM_SIZE - 1 ? nextTeam1Index + 1 : team2Index + 1;

        // team1 승, team2 패
        record[groupIndex][team1Index][WIN]--;
        record[groupIndex][team2Index][LOSS]--;
        if (validateGroup(groupIndex, nextTeam1Index, nextTeam2Index) == 1) {
            return 1;
        }
        record[groupIndex][team1Index][WIN]++;
        record[groupIndex][team2Index][LOSS]++;

        // team1 무, team2 무
        record[groupIndex][team1Index][DRAW]--;
        record[groupIndex][team2Index][DRAW]--;
        if (validateGroup(groupIndex, nextTeam1Index, nextTeam2Index) == 1) {
            return 1;
        }
        record[groupIndex][team1Index][DRAW]++;
        record[groupIndex][team2Index][DRAW]++;

        // team1 패, team2 승
        record[groupIndex][team1Index][LOSS]--;
        record[groupIndex][team2Index][WIN]--;
        if (validateGroup(groupIndex, nextTeam1Index, nextTeam2Index) == 1) {
            return 1;
        }
        record[groupIndex][team1Index][LOSS]++;
        record[groupIndex][team2Index][WIN]++;

        return 0;
    }

    private static void readInput() throws IOException {
        record = new int[GROUP_SIZE][TEAM_SIZE][3];
        StringTokenizer tokenizer;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (int groupIndex = 0; groupIndex < GROUP_SIZE; groupIndex++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int teamIndex = 0; teamIndex < TEAM_SIZE; teamIndex++) {
                record[groupIndex][teamIndex][WIN] = Integer.parseInt(tokenizer.nextToken());
                record[groupIndex][teamIndex][DRAW] = Integer.parseInt(tokenizer.nextToken());
                record[groupIndex][teamIndex][LOSS] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        reader.close();
    }
}
