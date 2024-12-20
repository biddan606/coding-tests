class Solution {
    public long findScore(int[] nums) {
        // nums[i] <= nums[i + 1] 이라면, nums[i]이 선택된다.
        Deque<Integer> stack = new ArrayDeque<>();
        long score = 0;

        for (int num : nums) {
            // 스택이 비어있거나, 앞의 값보다 뒤의 값이 작으면 삽입
            if (stack.isEmpty() || stack.peek() > num) {
                stack.push(num);
            } else {
                // 앞의 값이 뒤의 값보다 작거나 같다면 score로 추가된다.
                while (!stack.isEmpty()) {
                    score += stack.pop();
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                }
            }
        }

        while (!stack.isEmpty()) {
            score += stack.pop();
            if (!stack.isEmpty()) {
                stack.pop();
            }
        }

        return score;
    }
}
