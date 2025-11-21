import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] numbers;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        numbers = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int add = Integer.parseInt(st.nextToken());
        int sub = Integer.parseInt(st.nextToken());
        int mul = Integer.parseInt(st.nextToken());
        int div = Integer.parseInt(st.nextToken());

        // 첫 번째 숫자를 초기값으로 설정하고, 인덱스 1부터 시작
        dfs(1, numbers[0], add, sub, mul, div);

        System.out.println(max);
        System.out.println(min);
    }

    // 연산자 개수를 개별 파라미터로 분리하여 상태 관리 단순화
    private static void dfs(int index, int sum, int add, int sub, int mul, int div) {
        // Base Case: 모든 숫자를 다 사용했을 때
        if (index == N) {
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }

        // Recursive Case: 각 연산자가 남아있을 경우에만 호출
        // 별도의 배열 복구(backtracking) 로직 없이, 파라미터에서 -1을 하여 넘김
        if (add > 0) {
            dfs(index + 1, sum + numbers[index], add - 1, sub, mul, div);
        }
        if (sub > 0) {
            dfs(index + 1, sum - numbers[index], add, sub - 1, mul, div);
        }
        if (mul > 0) {
            dfs(index + 1, sum * numbers[index], add, sub, mul - 1, div);
        }
        if (div > 0) {
            dfs(index + 1, sum / numbers[index], add, sub, mul, div - 1);
        }
    }
}