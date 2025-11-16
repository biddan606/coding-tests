class Solution {
    public int minFlipsMonoIncr(String s) {
        // endWithZero: 현재 부분 문자열이 '0'으로 끝날 때의 최소 뒤집기 횟수
        int endWithZero = 0;
        // endWithOne: 현재 부분 문자열이 '1'으로 끝날 때의 최소 뒤집기 횟수
        int endWithOne = 0;

        for (char c : s.toCharArray()) {
            if (c == '0') {
                // 현재 문자가 '0'인 경우
                // '0'으로 끝나려면 이전 상태도 '0'이어야 하며, '0'을 뒤집을 필요가 없음
                // endWithZero는 그대로 유지

                // '1'로 끝나려면 이전 상태가 '0' 또는 '1'일 수 있으며,
                // 그 중 최소값에 현재 '0'을 '1'로 뒤집는 비용(1)을 더함
                endWithOne = Math.min(endWithZero, endWithOne) + 1;
            } else { // c == '1'
                // 현재 문자가 '1'인 경우
                // '1'로 끝나려면 이전 상태가 '0' 또는 '1'일 수 있으며,
                // 그 중 최소값에서 현재 '1'을 뒤집을 필요가 없음
                int newEndWithOne = Math.min(endWithZero, endWithOne);

                // '0'으로 끝나려면 이전 상태도 '0'이어야 하며,
                // 현재 '1'을 '0'으로 뒤집는 비용(1)을 더함
                endWithZero = endWithZero + 1;
                endWithOne = newEndWithOne;
            }
        }

        return Math.min(endWithZero, endWithOne);
    }
}