import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {

    private static final int[][] STUDENT_ANSWERS = {
            {1, 2, 3, 4, 5},
            {2, 1, 2, 3, 2, 4, 2, 5},
            {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
    };

    public int[] solution(int[] answers) {
        // 각 학생들이 맞힌 개수를 구한다.
        int[] hitCounts = Arrays.stream(STUDENT_ANSWERS)
                .mapToInt(studentAnswer -> calculateHitCount(answers, studentAnswer))
                .toArray();

        // 가장 많이 맞힌 개수를 찾는다.
        int maxHitCount = IntStream.of(hitCounts).max().orElse(0);

        // 가장 많이 맞힌 개수만큼 맞힌 학생들의 번호를 얻는다.
        return IntStream.range(0, hitCounts.length)
                .filter(i -> hitCounts[i] == maxHitCount)
                .map(i -> i + 1)
                .toArray();
    }

    private int calculateHitCount(int[] answers, int[] studentAnswer) {
        int hitCount = 0;

        for (int j = 0; j < answers.length; j++) {
            int studentAnswerIndex = j % studentAnswer.length;
            if (answers[j] == studentAnswer[studentAnswerIndex]) {
                hitCount++;
            }
        }

        return hitCount;
    }
}
