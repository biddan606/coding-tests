class Solution {
    /**
     * wonderful string은 최대 1개의 문자가 홀수번 등장하는 것을 말한다.
     * 고로 경우의 수는 두 가지이다.
     * 0개의 문자가 홀수번 등장하는 경우 => 모두 짝수번 등장하는 경우
     * 1개의 문자가 홀수번 등장하는 경우
     */
    public long wonderfulSubstrings(String word) {
        // abcdefghij의 문자가 올 수 있다. 총 10개의 문자이므로 1개의 비트씩 담당한다고 했을 때 2^10 - 1 = 1023까지 올 수 있다.
        long[] counts = new long[1024];
        counts[0] = 1;
        int mask = 0;
        long totalCount = 0;

        for (char c : word.toCharArray()) {
            int digit = c - 'a';
            mask ^= 1 << digit;
            
            /** 짝수 문자 조합의 개수를 더한다.
             * 현재 mask값이 이전에 등장했다는 건 이전mask값과 현재mask값 사이의 짝수 문자 조합이 존재한다는 것을 의미한다.
             */
            totalCount += counts[mask];

            for (int i = 1; i < 1024; i *= 2) {
                /**
                 * 특정 비트를 토글한다. 해당 비트는 0이라면 -> 1, 1이었다면 -> 0이 된다.
                 * 변환된 mask인 곳에서 현재 마스크까지는 해당 비트만 다르고 나머지 값은 같다.
                 * 해당 비트(문자)만 홀수번 등장하고 나머지는 짝수 또는 0번 등장한 것이다.
                 */
                totalCount += counts[mask ^ i];
            }

            counts[mask]++;
        }

        return totalCount;
    }
}
