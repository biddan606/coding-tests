import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Deque<int[]> deque = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int currentValue = Integer.parseInt(st.nextToken());

            /*
            deque 원소를 제거한다.

            원소 index가 현재 index - L + 1 이상만 있어야 한다.
             */
            while (!deque.isEmpty() && deque.getFirst()[0] < i - L + 1) {
                deque.removeFirst();
            }

            /*
            deque 값을 갱신한다.

            현재 index의 value를 deque 값으로 추가한다.
            현재 value보다 작은 deque 값을 저장될 필요가 없다.(먼저 빠져야 하는데, 현재 value보다 크므로 사용되지 않음)
            현재 value보다 큰 value를 제거하고, 현재 value를 추가한다.
             */
            while (!deque.isEmpty() && deque.getLast()[1] >= currentValue) {
                deque.removeLast();
            }
            deque.addLast(new int[]{i, currentValue});

            // deque의 가장 작은 값으로 결과를 입력한다.
            bw.write(String.valueOf(deque.getFirst()[1]));
            bw.write(" ");
        }

        br.close();

        bw.flush();
        bw.close();
    }
}
