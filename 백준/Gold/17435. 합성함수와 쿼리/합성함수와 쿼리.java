import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 이 클래스는 희소 배열(Sparse Table)을 사용하여 합성 함수 쿼리를 효율적으로 처리합니다.
 * f^n(x)는 함수 f를 x에 n번 적용한 결과를 의미합니다.
 * 희소 배열을 통해 O(M log N)의 전처리 후 각 쿼리를 O(log N) 시간에 해결할 수 있습니다.
 */
public class Main {

    // N의 최댓값 (문제의 제약 조건에 따라 설정)
    private static final int MAX_N = 500_000;
    private static int elementCount;
    private static int[][] sparseTable;
    private static int maxLog;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            elementCount = Integer.parseInt(reader.readLine());

            // 함수 f(x)의 값을 저장하는 배열
            int[] function = new int[elementCount + 1];
            StringTokenizer st = new StringTokenizer(reader.readLine());
            for (int i = 1; i <= elementCount; i++) {
                function[i] = Integer.parseInt(st.nextToken());
            }

            // 희소 배열 초기화
            initializeSparseTable(function);

            int queryCount = Integer.parseInt(reader.readLine());
            for (int i = 0; i < queryCount; i++) {
                st = new StringTokenizer(reader.readLine());
                int n = Integer.parseInt(st.nextToken()); // 함수 적용 횟수
                int x = Integer.parseInt(st.nextToken()); // 초기값

                // 쿼리 실행 및 결과 작성
                int result = executeQuery(n, x);
                writer.write(String.valueOf(result));
                writer.newLine();
            }
        }
    }

    /**
     * 희소 배열을 초기화합니다.
     * sparseTable[k][x]는 함수를 2^k 번 적용한 결과, 즉 f^(2^k)(x)를 저장합니다.
     * @param function 초기 함수 f(x) 값 배열
     */
    private static void initializeSparseTable(int[] function) {
        maxLog = (int) (Math.log(MAX_N) / Math.log(2));
        sparseTable = new int[maxLog + 1][elementCount + 1];

        // 0번째 행 초기화: sparseTable[0][x] = f(x) (2^0 = 1번 적용)
        for (int i = 1; i <= elementCount; i++) {
            sparseTable[0][i] = function[i];
        }

        // 나머지 행 채우기
        // f^(2^k)(x) = f^(2^(k-1))( f^(2^(k-1))(x) ) 점화식을 이용합니다.
        for (int k = 1; k <= maxLog; k++) {
            for (int x = 1; x <= elementCount; x++) {
                int prevValue = sparseTable[k - 1][x];
                sparseTable[k][x] = sparseTable[k - 1][prevValue];
            }
        }
    }

    /**
     * n과 x가 주어졌을 때 f^n(x) 값을 계산합니다.
     * n을 이진수로 분해하여 O(log n) 시간에 계산합니다.
     * @param n 함수 적용 횟수
     * @param x 초기값
     * @return f^n(x)의 결과
     */
    private static int executeQuery(int n, int x) {
        int currentValue = x;
        // n을 이진수로 표현했을 때, 1인 비트에 해당하는 2의 거듭제곱만큼 함수를 적용합니다.
        // 예를 들어 n=13(1101)이면, f^8(f^4(f^1(x)))를 계산합니다.
        for (int k = maxLog; k >= 0; k--) {
            if ((n & (1 << k)) > 0) { // n의 k번째 비트가 1이면
                currentValue = sparseTable[k][currentValue];
            }
        }
        return currentValue;
    }
}