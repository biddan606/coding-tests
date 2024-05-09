class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
        // 내림차순 정렬한다.
        Integer[] sorted = Arrays.stream(happiness)
                            .boxed()
                            .sorted((a, b) -> b - a)
                            .toArray(Integer[]::new);

        // 결과를 추가한다.
        // 추가할 때마다 넣었던 개수만큼 패널티를 준다.
        long result = 0;
        for (int i = 0; i < k; i++) {
            result += Math.max(sorted[i] - i, 0);
        }

        return result;
    }
}
