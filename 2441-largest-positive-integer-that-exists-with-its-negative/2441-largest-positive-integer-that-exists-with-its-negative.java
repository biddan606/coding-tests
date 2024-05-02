class Solution {
    public int findMaxK(int[] nums) {
        // 양의 정수들을 얻는다.
        Set<Integer> positive = new HashSet<>();
        for (int n : nums) {
            if (n > 0) {
                positive.add(n);
            }
        }

        // 양의 정수로 존재하면서, 가장 작은 음수를 찾는다.
        int min = 1; // 기본값
        for (int n : nums) {
            if (!positive.contains(-n)) {
                continue;
            }

            min = Math.min(min, n);
        }

        return -min;
    }
}
