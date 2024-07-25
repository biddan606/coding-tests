import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    /**
     * # 조건
     * 3월 1일부터 11월 30일까지 꽃이 피어있어야 한다.
     *
     * # 입력
     * 꽃들의 개화 시기와 낙화 시기를 입력 받는데, 낙화 당일에는 꽃이 피어있지 않는 걸로 한다.
     *
     * # 출력
     * 꽃을 번갈아가며 교체할 때, 최소 교체 횟수를 구한다.
     * 만족하는 꽃이 존재하지 않는다면, 0을 반환해야 한다.
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        // 꽃들의 피는 날짜와 지는 날짜를 입력 받는다.
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int flowerCount = Integer.parseInt(reader.readLine());

        List<Flower> flowers = new ArrayList<>();
        for (int c = 0; c < flowerCount; c++) {
            int[] token = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::valueOf)
                    .toArray();

            int start = token[0] * 100 + token[1];
            int end = token[2] * 100 + token[3];
            flowers.add(new Flower(start, end));
        }

        // 첫번째 시작 꽃을 정한다. 3월 1일이나 이전에 피면서, 지는 날짜가 가장 늦은 꽃
        int currentEnd = 0;

        for (Flower flower : flowers) {
            if (flower.start > 301) {
                continue;
            }

            currentEnd = Math.max(currentEnd, flower.end);
        }

        // 첫번째 꽃을 찾을 수 없다면, 0을 리턴하고 종료한다.
        if (currentEnd == 0) {
            System.out.println(0);
            return;
        }

        // 11월 30일까지 연속해서 꽃이 필 수 있게 교체한다.
        // 다음 꽃은 현재 지는 날짜보다 먼저 피면서, 11월 30일 이후에 지는 꽃
        int replacementCount = 1;

        while (currentEnd <= 1130) {
            int nextEnd = currentEnd;

            for (Flower flower : flowers) {
                if (currentEnd < flower.start) {
                    continue;
                }

                nextEnd = Math.max(nextEnd, flower.end);
            }

            // 꽃의 교체되지 않았다면, 교체할 꽃이 없으므로 0을 리턴하고 종료한다.
            if (currentEnd == nextEnd) {
                System.out.println(0);
                return;
            }

            currentEnd = nextEnd;
            replacementCount++;
        }

        // 꽃의 교체 횟수를 출력한다.
        System.out.println(replacementCount);
    }

    private static class Flower {

        final int start;
        final int end;

        public Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
