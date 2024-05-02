class Solution {
    public int findMaxK(int[] nums) {
        // 음수들를 얻는다.
        Set<Integer> negative = new HashSet<>();
        for (int n : nums) {
            if (n < 0) {
                negative.add(n);
            }
        }

        // 음수가 존재하는 경우에만 최대값을 갱신한다.
        int max = -1; // 기본값
        for (int n : nums) {
            if (!negative.contains(-n)) {
                continue;
            }

            max = Math.max(max, n);
        }

        return max;
    }
}
