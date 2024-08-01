import java.util.Arrays;

class Solution {
    /**
     * 라이언은 어피치보다 과녁에 1발 더 많이 맞춰야 해당 과녁 점수를 얻는다.
     * 둘 다 과녁을 맞추지 않은 점수는 아무도 점수를 획득하지 못한다.
     * 라이언은 어피치를 가장 큰 점수 차이로 이겨야 한다.
     * 가장 큰 점수 차이가 여러 개 존재할 경우, 가장 낮은 점수를 많이 맞힌 경우를 리턴한다.
     * 이길 수 없을 경우 [-1]을 리턴한다.
     */

    private int[] result = {-1};
    private int maxScoreDifference = 1;

    public int[] solution(int totalArrows, int[] apeachInfo) {
        // 특정 점수를 이기거나 이기지 않는 전략을 택합니다.
        // 이길 경우에는 상대방보다 1발을 더 맞추고, 이기지 않는 경우에는 0발을 맞춥니다.
        dfs(new int[11], 0, totalArrows, apeachInfo);

        return result;
    }

    private void dfs(int[] ryanInfo, int index, int arrows, int[] apeachInfo) {
        // index가 ryanInfo.length와 같거나 커질 경우, 더이상 진행할 수 없습니다.
        // ryanInfo와 apeachInfo를 비교하여 ryan이 이긴 경우, 결과를 수정할 수 있는지 검사합니다.
        // 결과를 수정할 수 있다면 결과를 수정합니다.
        // 수정 가능 경우: scoreDifference보다 큰 점수이거나 같은 점수차이지만 낮은 과녁을 더 많이 맞춘 경우
        if (index >= ryanInfo.length) {
            // 화살이 남았다면 0점에 몰아넣습니다.
            ryanInfo[10] = arrows;

            int scoreDifference = calculateScoreDifference(ryanInfo, apeachInfo);
            if (scoreDifference > maxScoreDifference ||
                    (scoreDifference == maxScoreDifference && hasMoreLowScores(ryanInfo))) {

                result = Arrays.copyOf(ryanInfo, ryanInfo.length);
                maxScoreDifference = scoreDifference;
            }

            ryanInfo[10] = 0;
            return;
        }

        // 현재 과녁 점수를 획득할 수 있다면 획득합니다.
        if (arrows > apeachInfo[index]) {
            ryanInfo[index] = apeachInfo[index] + 1;
            dfs(ryanInfo, index + 1, arrows - ryanInfo[index], apeachInfo);
        }

        // 현재 과녁을 포기합니다.
        ryanInfo[index] = 0;
        dfs(ryanInfo, index + 1, arrows, apeachInfo);
    }

    private int calculateScoreDifference(int[] ryanInfo, int[] apeachInfo) {
        int scoreDifference = 0;

        for (int i = 0; i < ryanInfo.length; i++) {
            if (ryanInfo[i] == 0 && apeachInfo[i] == 0) {
                continue;
            }

            int score = 10 - i;
            if (ryanInfo[i] <= apeachInfo[i]) {
                score = -score;
            }

            scoreDifference += score;
        }

        return scoreDifference;
    }

    private boolean hasMoreLowScores(int[] ryanInfo) {
        for (int i = result.length - 1; i >= 0; i--) {
            if (result[i] == ryanInfo[i]) {
                continue;
            } else if (result[i] > ryanInfo[i]) {
                return false;
            }
            break;
        }

        return true;
    }

    public static void main(String[] args) {
        int[] apeachInfo = {0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1};

        int[] result = new Solution().solution(9, apeachInfo);

        System.out.println(Arrays.toString(result));
    }
}
