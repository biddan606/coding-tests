import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int lectureCount = Integer.parseInt(reader.readLine());

        List<Lecture> lectures = new ArrayList<>();
        for (int i = 0; i < lectureCount; i++) {
            int[] token = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            lectures.add(new Lecture(token[0], token[1]));
        }

        reader.close();

        // 시작 시간 오름차순으로 정렬, 시작 시간이 같을 경우 종료 시간 오름차순으로 정렬
        lectures.sort((a, b) -> {
            if (a.startTime == b.startTime) {
                return a.endTime - b.endTime;
            }
            return a.startTime - b.startTime;
        });

        int lecturesIndex = 0;
        int maxQueueSize = 0;
        PriorityQueue<Lecture> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.endTime));

        // 정렬된 수업들을 전부 순회
        while (lecturesIndex < lectures.size()) {
            int currentStartTime = lectures.get(lecturesIndex).startTime;

            // 현재 시작시간에 시작하는 모든 강의들을 큐에 삽입
            while (lecturesIndex < lectures.size() && lectures.get(lecturesIndex).startTime == currentStartTime) {
                queue.offer(lectures.get(lecturesIndex));
                lecturesIndex++;
            }

            // 현재 시작시간이나 이전에 종료한 강의들을 큐에서 제거
            while (!queue.isEmpty() && queue.peek().endTime <= currentStartTime) {
                queue.poll();
            }

            maxQueueSize = Math.max(maxQueueSize, queue.size());
        }

        System.out.println(maxQueueSize);
    }

    private static class Lecture {

        final int startTime;
        final int endTime;

        public Lecture(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
}
