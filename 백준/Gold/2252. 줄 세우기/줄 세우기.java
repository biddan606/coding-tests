import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


/*
# 문제
특정 숫자 앞에 등장해야 하는 숫자들이 있다.
이 규칙을 지켜 출력해야 한다.

# 접근
단순한 위상 정렬 문제이다.
next를 연결시키고, 참조가 0인 값을 BFS 처리하자.

# 구현
1. 입력: 숫자 크기, 관계 입력 크기, 관계 정의
2. 뒤에 와야 하는 숫자를 등록, 뒤에 숫자는 referCount 증가
3. 참조가 0인 것들을 BFS 처리하며 출력
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int numberCount = Integer.parseInt(tokenizer.nextToken());
        int relationCount = Integer.parseInt(tokenizer.nextToken());

        Number[] numbers = new Number[numberCount + 1];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = new Number();
        }

        for (int i = 0; i < relationCount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int prev =  Integer.parseInt(tokenizer.nextToken());
            int next = Integer.parseInt(tokenizer.nextToken());
            numbers[prev].next.add(next);
            numbers[next].referCount++;
        }
        reader.close();

        Queue<Integer> queue = new ArrayDeque<>();
        for (int n = 1; n < numbers.length; n++) {
            if (numbers[n].referCount == 0) {
                queue.add(n);
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        while (!queue.isEmpty()) {
            int number = queue.poll();

            stringBuilder.append(number).append(" ");

            for (int nextNumber : numbers[number].next) {
                Number next = numbers[nextNumber];
                next.referCount--;
                if (next.referCount == 0) {
                    queue.add(nextNumber);
                }
            }
        }

        System.out.println(stringBuilder.toString().trim());
    }

    private static class Number {

        List<Integer> next;
        int referCount;

        Number() {
            next = new ArrayList<>();
            referCount = 0;
        }

    }

}
