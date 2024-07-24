import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.text.Segment;

public class Main {

    /**
     * (x1 - y1), (x2 - y2), ..., (xN - yN) 선들이 존재한다.
     * 각 선들을 중첩했을 때의 총 길이를 구해 출력해야 한다.
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        // 선들을 입력 받는다.
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int segmentCount = Integer.parseInt(reader.readLine());

        List<Segment> totalSegments = new ArrayList<>();
        for (int c = 0; c < segmentCount; c++) {
            int[] token = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            totalSegments.add(new Segment(token[0], token[1]));
        }

        // 선들을 시작점 기준 오름차순으로 정렬한다.
        totalSegments.sort((a, b) -> {
            int startCompare = a.start - b.start;
            if (startCompare == 0) {
                return a.end - b.end;
            }
            return startCompare;
        });

        // 선들을 중첩한다.
        List<Segment> nestedSegments = new ArrayList<>();
        int currentStart = -1_000_000_000;
        int currentEnd = -1_000_000_000;

        for (Segment s : totalSegments) {
            if (s.start > currentEnd) {
                nestedSegments.add(new Segment(currentStart, currentEnd));
                currentStart = s.start;
            }

            currentEnd = Math.max(currentEnd, s.end);
        }
        nestedSegments.add(new Segment(currentStart, currentEnd));

        // 중첩한 선들의 합을 구한 뒤, 출력한다.
        int sumOfSegmentLength = 0;
        for (Segment s : nestedSegments) {
            sumOfSegmentLength += s.end - s.start;
        }

        System.out.println(sumOfSegmentLength);
    }

    private static class Segment {

        final int start;
        final int end;

        public Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
