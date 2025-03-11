import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {

    /*
    # 문제 이해
    사다리가 주어짐
    시작 값과 결과 값이 있고, 중간에 빈 row가 있음
    빈 row에 해당하는 값을 반환해야 함, 만들 수 없다면 x.repeat(k-1) 반환

    # 접근
    가로의 수는 최대 1_000이고, 참가자의 수(k)는 26이다
    26을 보니 완전 탐색 풀이일 수도 있겠다는 느낌이 든다

    시작 값을 앎으로 빈 row까지의 값을 구할 수 있다
    결과 값을 앎으로 거꾸로 올라가 빈 row까지의 값을 구할 수 있다

    빈 row를 사이에 두고 위 아래를 구할 수 있으므로
    위 아래 값을 매칭하여 빈 row 값을 도출하자

    완전 탐색으로 해도 되고, 한칸 밖에 옆으로 못 가므로 직접 구현을 하면 시간을 더 줄일 수 있을 것이다

    # 구현 스텝(입력, 출력 설명 X)
    ## 입력
    1. participantCount를 입력 받는다
    2. ladderLineCount를 입력 받는다
    3. arrivalOrder를 입력받는다
    4. 사다리를 입력받는다
    ## 데이터 압축
    5. privateRow를 구한다
    6. 시작값으로 privateRow까지 진행한다 -> aboveResult
    7. 결과값으로 privateRow까지 거꾸로 진행한다 -> belowResult

    ## 결과 매칭
    8. aboveResult와 belowResult를 매칭한다
    9. 매칭되면 매칭된 row값을 반환, 없다면 x.repeat(k-1) 반환
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int participantCount = Integer.parseInt(reader.readLine());
        int ladderLineCount = Integer.parseInt(reader.readLine());
        String arrivalOrder = reader.readLine();

        String[] ladders = new String[ladderLineCount];
        for (int i = 0; i < ladderLineCount; i++) {
            ladders[i] = reader.readLine();
        }
        reader.close();

        StringBuilder departureOrderBuilder = new StringBuilder();
        for (int i = 0; i < participantCount; i++) {
            departureOrderBuilder.append((char)('A' + i));
        }
        String departureOrder = departureOrderBuilder.toString();

        int questionMarkRow = -1;
        for (int i = 0; i < ladderLineCount; i++) {
            if (ladders[i].charAt(0) == '?') {
                questionMarkRow = i;
                break;
            }
        }

        char[] aboveResult = playLadderUntilRow(ladders, departureOrder, questionMarkRow);
        char[] belowResult = playReverseLadderUntilRow(ladders, arrivalOrder, questionMarkRow);

        String questionMarkRowValue = createRow(aboveResult, belowResult);
        System.out.println(questionMarkRowValue);
    }

    private static char[] playLadderUntilRow(String[] ladders, String startOrder, int endRow) {
        char[] result = new char[startOrder.length()];
        for (int i = 0; i < startOrder.length(); i++) {
            result[i] = startOrder.charAt(i);
        }

        int colSize = ladders[0].length();
        for (int r = 0; r < endRow; r++) {
            for (int c = 0; c < colSize; c++) {
                if (ladders[r].charAt(c) == '-') {
                    swap(result, c, c + 1);
                }
            }
        }

        return result;
    }

    private static void swap(char[] result, int index1, int index2) {
        char temp = result[index1];
        result[index1] = result[index2];
        result[index2] = temp;
    }

    private static char[] playReverseLadderUntilRow(String[] ladders, String startOrder, int endRow) {
        char[] result = new char[startOrder.length()];
        for (int i = 0; i < startOrder.length(); i++) {
            result[i] = startOrder.charAt(i);
        }

        int colSize = ladders[0].length();
        for (int r = ladders.length - 1; r > endRow; r--) {
            for (int c = 0; c < colSize; c++) {
                if (ladders[r].charAt(c) == '-') {
                    swap(result, c, c + 1);
                }
            }
        }

        return result;
    }

    private static String createRow(char[] aboveResult, char[] belowResult) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i + 1 < aboveResult.length; i++) {
            if (aboveResult[i] == belowResult[i]) {
                result.append('*');
            } else if (aboveResult[i] == belowResult[i + 1]
                    && aboveResult[i + 1] == belowResult[i]) {
                result.append('-');
                if (i + 1 != aboveResult.length - 1) {
                    result.append('*');
                }
                i++;
            } else {
                return "x".repeat(aboveResult.length - 1);
            }
        }

        return result.toString();
    }
}
