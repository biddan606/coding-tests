import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    // 1. `record` 대신 private static 중첩 클래스 사용
    private static class Node {
        final int index; // 2. 필드를 final로 선언하여 불변성 확보
        final int value;

        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        Deque<Node> deque = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int currentValue = Integer.parseInt(st.nextToken());
            
            // 3. 메서드 추출 (이전과 동일)
            removeOutOfWindowNodes(deque, i, L);
            removeLargerValueNodes(deque, currentValue);

            deque.addLast(new Node(i, currentValue));

            // 결과 작성 (필드 직접 접근)
            bw.write(String.valueOf(deque.getFirst().value));
            bw.write(" ");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    private static void removeOutOfWindowNodes(Deque<Node> deque, int currentIndex, int windowSize) {
        if (!deque.isEmpty() && deque.getFirst().index <= currentIndex - windowSize) {
            deque.removeFirst();
        }
    }

    private static void removeLargerValueNodes(Deque<Node> deque, int currentValue) {
        while (!deque.isEmpty() && deque.getLast().value >= currentValue) {
            deque.removeLast();
        }
    }
}