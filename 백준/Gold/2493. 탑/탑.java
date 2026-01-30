import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

class Main {

    /*
    # 문제
    왼쪽 수평 방향으로 레이저 발사
    레이저를 수신하는 나보다 높은 층만 가능
    수신한 tower 층 수들을 반환

    # 접근
    스택을 이용해서 뒤에서부터 넣는다.
    현재 인덱스의 높이가 스택에 있는 값들보다 높다면 위치 갱신

    # 구현
    1. 뒤에서부터 순회
    2. 스택 peek 인덱스의 높이보다 현재 인덱스의 높이가 높거나 같다면, peek 인덱스값을 (현재 인덱스 + 1) 값으로 갱신
    3. 인덱스 갱신 배열 출력
     */
    public static void main(String args[]) throws Exception {
        // 입력
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int towerCount = Integer.parseInt(reader.readLine());
        int[] towers = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        reader.close();

        // 결과 갱신
        Deque<Integer> indexes = new ArrayDeque<>();
        int[] result = new int[towerCount];

        for (int index = towerCount - 1; index >= 0; index--) {
            int currentHeight = towers[index];
            while (!indexes.isEmpty() && towers[indexes.peekFirst()] <= currentHeight) {
                int indexToUpdate = indexes.removeFirst();
                result[indexToUpdate] = index + 1;
            }
            indexes.addFirst(index);
        }

        // 출력
        String output = Arrays.stream(result)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println(output);
    }
}
