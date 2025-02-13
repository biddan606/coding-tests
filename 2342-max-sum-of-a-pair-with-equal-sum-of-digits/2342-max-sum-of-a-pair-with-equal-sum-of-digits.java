class Solution {
    /*
    # 문제
    다른 두 수를 합쳐 최대값을 반환한다

    # 가능 동작
    - 각 자릿수의 합이 같은 다른 두 수를 합할 수 있다

    # 직관
    1. 자릿수의 합이 같은 수끼리 묶는다
    2. 자릿수가 같은 수들을 정렬한다
    3. 자릿수가 같은 수들 중 가장 큰 두 수를 합친다
    4. 1-3을 반복하면서 최대값을 갱신한다
    */
    public int maximumSum(int[] nums) {
        Map<Integer, List<Integer>> byDigit = new HashMap<>();

        for (int num : nums) {
            int digit = 0;

            for (char c : String.valueOf(num).toCharArray()) {
                digit += c - '0';
            }

            byDigit.computeIfAbsent(digit, k -> new ArrayList<>())
                .add(num);
        }

        int max = -1;
        
        for (List<Integer> values : byDigit.values()) {
            if (values.size() < 2) {
                continue;
            }

            values.sort((a, b) -> b - a);
            
            max = Math.max(max, values.get(0) + values.get(1));
        }

        return max;
    }
}
