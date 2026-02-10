import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * # 문제 첫째 열과 연결되는 마지막 열의 최대 개수를 반환해야 한다. 연결은 파이브를 통해서 할 수 있고, 파이브는 오른쪽, 오른쪽 위 대각, 오른쪽 아래 대각을 연결할 수 있다. - 파이프끼리는 겹칠 수
 * 없다.
 * <p>
 * # 접근1 DP 느낌으로 이전에 가능한 연결이면 계속 가져오면 마지막에 [RIGHT],[RIGHT_TOP], [RIGHT_BOTTOM] true/false로 남겨된다. 이를 이용해서 마지막 col의 row가
 * 해당 파이브를 연결할 수 있는지 없는지 체크할 수 있다.
 * <p>
 * 다만, 마지막 파이브 개수를 체크할 때, 인접한 row와 충돌이 날 수 있다. - [row]가 RIGHT_TOP을 사용하고, [row+1]이 RIGHT를 사용하는 경우
 * <p>
 * 이 충돌을 피하기 위해, 작은 row부터 큰 row 순으로 추가하고 - RIGHT_BOTTOM을 가장 먼저 사용한다. RIGHT_BOTTOM을 사용할 때는 상위 ROW가 RIGHT로 연결되어 있지 않아야 한다.
 * - RIGHT는 상위 ROW가 RIGHT_TOP을 사용하지 않았어야 한다. - RIGHT_TOP은 가장 마지막에 사용한다.(하위 ROW의 것을 가져다쓰므로)
 * <p>
 * # 필요한 정보1 - 이전 [row][col]의 파이프를 가져다쓸 수 있는지 - [RIGHT], [RIGHT_BOTTOM], [RIGHT_TOP] 어떤 걸 가져다 쓸 수 있는지
 * <p>
 * # 풀이1 1. col=0부터 col=length-1까지 사용 가능한 파이브를 true처리한다. - true 처리하기 위해서는 상위 row가 어떤 걸 사용하는지에 따라 false 처리가 될 수도 있다. 2.
 * 마지막 col에서 아래를 배려하면서, 내가 쓸 수 있는 것을 택한다.
 * <p>
 * !! [row-1][col] RIGHT_BOTTOM이 사용하고, [row][col]이 RIGHT_UP을 사용하는 경우 탐지할 수 없다.
 * <p>
 * # 접근2 이전 접근은 col=0이 모든 방향이 가능하니, col=1부터 이전 파이프를 연결하는 식으로 구현했다. 하지만 그럴 경우 문제는 이전 파이프를 동시에 사용할 수 있는 경우가 생긴다.
 * <p>
 * 앞에서부터 파이프를 사용하고, 연결된 다음 파이프를 used 처리를 하면 이 문제가 해결된다.
 * <p>
 * # 풀이2 1. col=0부터 col와 col+1를 매핑시킨다. - col를 사용하려면 reachable=true라면, 사용할 수 있다. - col+1은 reachable=true이고, used=false여야
 * 사용할 수 있다. - 사용하면 used=true 처리한다. - RIGHT_TOP, RIGHT, RIGHT_BOTTOM 순으로 사용한다. 위에것을 먼저 써야 아래가 더 많은 선택지가 주어진다. 2.
 * col=cols-1까지 완료되면, used[cols - 1]에 true 처리된 것들의 개수를 세어 반환한다.
 *
 * !! 4%에서 틀린다.
 *
 * # 접근3
 * col 단위로 지나가게 되면, 중간에 길이 막혔을 때, 해당 길을 못 쓰게 된다.
 * 가장 많은 파이프라인을 만들려면, 최대한 위로 붙여서 파이프라인을 만들어야 한다.
 * (직관적으로 떠올릴 수 있다면 쉬운 문젠데, 못 떠올리면 너무 돌아온다. 그래도 배웠으니 좋았다)
 *
 * # 풀이3
 * 1. 2차원 visited에 건물을 true처리한다.
 * 2. (0, 0), (1, 0) ... col=0인 값들을 모두 cols-1까지 진행한다.
 * 3. cols-1까지 진행이 되면 true를 반환하여 count를 증가시킨다.
 * 4. 개수를 출력한다.
 */
public class Main {

    static int rows;
    static int cols;
    static boolean[][] visited;

    private static final int[][] DIRECTIONS = {
            {-1, 1},
            {0, 1},
            {1, 1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] meta = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        rows = meta[0];
        cols = meta[1];

        char[][] buildings = new char[rows][];
        for (int row = 0; row < rows; row++) {
            buildings[row] = br.readLine().toCharArray();
        }
        br.close();

        visited = new boolean[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (buildings[row][col] == 'x') {
                    visited[row][col] = true;
                }
            }
        }

        int result = 0;
        for (int row = 0; row < rows; row++) {
            if (!visited[row][0] && dfs(row, 0)) {
                result++;
            }
        }

        System.out.println(result);
    }

    private static boolean dfs(int row, int col) {
        visited[row][col] = true;

        if (col == cols - 1) {
            return true;
        }

        for (int[] direction : DIRECTIONS) {
            int nextRow = row + direction[0];
            int nextCol = col + direction[1];

            if (nextRow < 0 || nextRow >= rows || nextCol >= cols) {
                continue;
            }
            if (visited[nextRow][nextCol]) {
                continue;
            }

            if (dfs(nextRow, nextCol)) {
                return true;
            }
        }

        return false;
    }
}
