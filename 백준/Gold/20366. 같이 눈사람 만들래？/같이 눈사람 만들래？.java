import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws Exception {

        // 입출력을 초기화한다.
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int numberOfHeight = Integer.parseInt(reader.readLine());

        int[] heights = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        // 키를 내림차순으로 정렬한다.(정렬해야 투포인터를 사용할 수 있음)
        Arrays.sort(heights);
        for (int i = 0; i < heights.length / 2; i++) {
            int tmp = heights[i];
            heights[i] = heights[heights.length - 1 - i];
            heights[heights.length - 1 - i] = tmp;
        }

        int minDifference = Integer.MAX_VALUE;
        for (int a1Index = 0; a1Index < heights.length; a1Index++) {
            int a1 = heights[a1Index];

            for (int b1Index = a1Index + 1; b1Index < heights.length; b1Index++) {
                /**
                 * a와 b의 차이가 적어야 하기 때문에 a1, b1, b2, a2 순으로 조합한다.
                 * a의 합이 크다면 b와 가까워져야 하므로, a2Index를 증가하고, b의 조합이 크다면 b2Index를 증가시킨다.
                 * a2Index는 b2Index와 겹치면 안되고 항상 커야하므로, b2Index를 증가시킬 때 a2Index와 같아진다면 a2Index도 증가시킨다.
                 * a2가 heights.length와 같아진다면 더이상 진행할 수 없으므로 종료한다.
                 * a == b일 때는 0으로 더이상 진행할 필요가 없으므로 0을 리턴한다.
                 */
                int b1 = heights[b1Index];
                int b2Index = b1Index + 1;
                int a2Index = b2Index + 1;
                while (a2Index < heights.length) {
                    int b2 = heights[b2Index];
                    int a2 = heights[a2Index];
                    int a = a1 + a2;
                    int b = b1 + b2;
                    int currentDifference = Math.abs(a - b);
                    if (currentDifference == 0) {
                        writer.write('0');

                        reader.close();
                        writer.close();
                        return;
                    }

                    minDifference = Math.min(minDifference, currentDifference);

                    if (a > b) {
                        a2Index++;
                    } else { // b > a
                        b2Index++;
                        if (a2Index == b2Index) {
                            a2Index++;
                        }
                    }
                }
            }
        }


        // 결과를 출력한다.
        writer.write(String.valueOf(minDifference));


        // 입출력 설정을 해제한다.
        reader.close();
        writer.close();
    }
}
