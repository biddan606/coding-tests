import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        // 입력을 받는다.
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLine = reader.readLine().split(" ");
        int time = Integer.parseInt(firstLine[0]);
        int maxMove = Integer.parseInt(firstLine[1]);

        int[] treeLocation = new int[time + 1];
        for (int i = 1; i <= time; i++) {
            treeLocation[i] = Integer.parseInt(reader.readLine());
        }
        reader.close();

        /*
        스코어의 최대값을 구해야 한다.
        time번 돌아가야 한다.
        time + 1은 time에서 그대로 오거나 move하여 다른 나무로 이동할 수 있다.
        move 횟수는 정해져 있다.
        추가: m번 움직인 1번, 2번 트리의 스코어 값이 같다면 둘 다 가지고 있어야 한다.
         */
        int[][][] scores = new int[time + 1][maxMove + 1][3];
        for (int m = 0; m <= maxMove; m++) {
            int tree = (m % 2) + 1;
            if (tree == treeLocation[1]) {
                scores[1][m][tree] = 1;
            }
        }

        for (int t = 2; t <= time; t++) {
            scores[t][0][1] = scores[t - 1][0][1] + match(1, treeLocation[t]);
            scores[t][0][2] = scores[t - 1][0][2] + match(2, treeLocation[t]);

            for (int m = 1; m <= maxMove; m++) {
                scores[t][m][1] = Math.max(scores[t - 1][m - 1][2], scores[t - 1][m][1]) + match(1, treeLocation[t]);
                scores[t][m][2] = Math.max(scores[t - 1][m - 1][1], scores[t - 1][m][2]) + match(2, treeLocation[t]);
            }
        }

        // 최대값을 구하고 결과를 출력한다.
        int result = 0;
        for (int m = 0; m <= maxMove; m++) {
            result = Math.max(result, scores[time][m][1]);
            result = Math.max(result, scores[time][m][2]);
        }

        System.out.println(result);
    }

    private static int match(int tree1, int tree2) {
        if (tree1 == tree2) {
            return 1;
        }
        return 0;
    }
}
