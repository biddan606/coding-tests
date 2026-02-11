import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

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
        // 사전 검증: 각 팀의 승+무+패 == 5, 총 승 == 총 패, 총 무승부 짝수
        int totalWin = 0, totalDraw = 0, totalLoss = 0;
        for (int t = 0; t < TEAM_SIZE; t++) {
            int w = record[groupIndex][t][WIN];
            int d = record[groupIndex][t][DRAW];
            int l = record[groupIndex][t][LOSS];
            if (w + d + l != 5 || w < 0 || d < 0 || l < 0) return 0;
            totalWin += w;
            totalDraw += d;
            totalLoss += l;
        }
        if (totalWin != totalLoss || totalDraw % 2 != 0) return 0;

        return dfs(groupIndex, 0, 1);
    }

    private static int dfs(int gi, int t1, int t2) {
        if (t1 == TEAM_SIZE - 1) {
            return 1;
        }

        int nt1 = t2 == TEAM_SIZE - 1 ? t1 + 1 : t1;
        int nt2 = t2 == TEAM_SIZE - 1 ? nt1 + 1 : t2 + 1;

        // team1 승, team2 패
        if (tryMatch(gi, t1, t2, WIN, LOSS, nt1, nt2) == 1) return 1;
        // team1 무, team2 무
        if (tryMatch(gi, t1, t2, DRAW, DRAW, nt1, nt2) == 1) return 1;
        // team1 패, team2 승
        if (tryMatch(gi, t1, t2, LOSS, WIN, nt1, nt2) == 1) return 1;

        return 0;
    }

    private static int tryMatch(int gi, int t1, int t2, int t1Type, int t2Type, int nt1, int nt2) {
        record[gi][t1][t1Type]--;
        record[gi][t2][t2Type]--;

        int res = 0;
        if (record[gi][t1][t1Type] >= 0 && record[gi][t2][t2Type] >= 0) {
            res = dfs(gi, nt1, nt2);
        }

        record[gi][t1][t1Type]++;
        record[gi][t2][t2Type]++;
        return res;
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