class Solution {
    public int minFlipsMonoIncr(String s) {
        // minFlips: 현재까지의 최소 뒤집기 횟수
        int minFlips = 0;
        // onesCount: 현재까지 순회하면서 만난 '1'의 개수
        int onesCount = 0;

        for (char c : s.toCharArray()) {
            if (c == '1') {
                // '1'을 만나면 카운트만 증가시킨다.
                // 이 '1'은 나중에 '0'으로 뒤집힐 수도 있고, 그대로 유지될 수도 있다.
                onesCount++;
            } else { // c == '0'
                // '0'을 만났을 때 두 가지 선택이 가능
                // 1. 현재 '0'을 '1'로 뒤집는다. (비용: minFlips + 1)
                // 2. 현재 '0'을 그대로 둔다. 이 경우 이전에 나온 모든 '1'을 '0'으로 뒤집어야 한다. (비용: onesCount)
                // 두 선택 중 더 비용이 적은 쪽을 선택한다.
                minFlips = Math.min(minFlips + 1, onesCount);
            }
        }
        return minFlips;
    }
}
