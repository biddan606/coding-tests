import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
# 문제
1개의 회의실에서 각 회의가 겹치지 않게, 가장 많이 회의가 열릴 수 있는 횟수를 반환
한 회의가 끝나는 순간 다음 회의가 바로 시작될 수 있다.

# 시뮬레이션
(1, 4)
(3, 5)
(0, 6)
(5, 7)
(3, 8)
(5, 9)
(6, 10)
(8, 11)
(8, 12)
(2, 13)
(12, 14)

다음 회의가 빨리 잡히기 위해서는 현재 회의가 빨리 끝나야 한다.

(1, 4) -> (5, 7) -> (8, 11) -> (12, 14) // 4

# 접근
회의는 끝나는 시간 오름차순으로 되어 있어야 하고, 앞에서부터 end_time을 갱신해나가면 될 것 같다.

# 풀이
1. 입력: 회의 개수, 회의 시간들을 입력 받는다.
2. 정렬: 회의들을 end_time 오름차순으로 정렬
3. 카운팅: end_time보다 start_time이 이후라면, end_time 갱신
    - start_time은 end_time을 바꿀 수 있는 조건
    - end_time이 새로운 end_time
4. 출력: 카운팅 출력

---

# 예상치 못한 반례

start_time == end_time이라면, start_time이 작은 것이 앞에 와야 함
(0, 1), (1, 1)이 있을 때,
(0, 1)이 먼저 오면 (1, 1)이 될 수 있지만, (1,1)이 먼저 오면 불가능

-> start_time도 정렬기준으로 삼아야 함
 */
public class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int meetingCount = Integer.parseInt(reader.readLine());
        List<int[]> meetings = new ArrayList<>();
        for (int i = 0; i < meetingCount; i++) {
            meetings.add(parseInts(reader.readLine()));
        }
        reader.close();

        meetings.sort((a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        int activeMeetingCount = 0;
        int currentEndTime = 0;
        for (int[] meeting : meetings) {
            int startTime = meeting[0];
            int endTime = meeting[1];
            if (startTime >= currentEndTime) {
                activeMeetingCount++;
                currentEndTime = endTime;
            }
        }

        System.out.println(activeMeetingCount);
    }

    private static int[] parseInts(String str) {
        return Arrays.stream(str.split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
