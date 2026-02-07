import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * # 문제
 * 재료는 (신맛, 쓴맛)으로 구성되어 있다.
 * 여러 재료를 조합하여 abs(신맛 - 쓴맛)이 가장 작은 값을 반환해야 한다.
 * 조합할 떄, 신맛끼리는 곱으로 연산되고, 쓴맛끼리는 합으로 연산된다.
 *
 * 최소 1개 이상의 재료를 사용해야 한다.
 *
 * # 접근1
 * N의 최대값이 10이다. 모든 경우의 수를 살펴봐도 될 것 같다.
 * 모든 재료를 사용하였을 떄의 신맛과 쓴맛의 값은 1_000_000_000보다 작다. 오버플로우는 고려하지 않아도 된다.
 *
 * # 풀이1
 * 1. 1개의 재료를 첫 재료로 정해둔 상태로 이후 재료부터 dfs한다.
 * 2. dfs는 1개를 포함하거나 포함하지 않는다.
 * 3. index==size()가 되었다면, abs(신맛-쓴맛)을 계산하여, 최소값을 갱신한다.
 * 4. 최소값을 반환한다.
 */
class Main {

    public static void main(String args[]) throws Exception {
        // 입력
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // 재료를 개수를 입력
        int ingredientCount = Integer.parseInt(reader.readLine());

        // (신맛, 쓴맛) 입력
        int[][] ingredients = new int[ingredientCount][];
        for (int i = 0; i < ingredientCount; i++) {
            ingredients[i] = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int minAbsDiff = Integer.MAX_VALUE;

        // 1개의 재료를 선택하고 dfs를 돌린다.
        for (int selectedFirstIndex = 0; selectedFirstIndex < ingredientCount; selectedFirstIndex++) {
            int sourLevel = ingredients[selectedFirstIndex][0];
            int bitterLevel = ingredients[selectedFirstIndex][1];

            int currentMinAbsDiff = dfs(ingredients, selectedFirstIndex + 1, sourLevel, bitterLevel);

            minAbsDiff = Math.min(minAbsDiff, currentMinAbsDiff);
        }

        // 결과를 반환한다.
        System.out.println(minAbsDiff);
    }

    private static int dfs(int[][] ingredients, int selectedIndex, int sourLevel, int bitterLevel) {
        if (selectedIndex == ingredients.length) {
            return Math.abs(sourLevel - bitterLevel);
        }

        int selectedSourLevel = sourLevel * ingredients[selectedIndex][0];
        int selectedBitterLevel = bitterLevel + ingredients[selectedIndex][1];

        int minAbsDiff = dfs(ingredients, selectedIndex + 1, sourLevel, bitterLevel);
        minAbsDiff = Math.min(minAbsDiff, dfs(ingredients, selectedIndex + 1, selectedSourLevel, selectedBitterLevel));

        return minAbsDiff;
    }
}
