class Solution {
    public int maximumLength(String s) {
        // 단일 문자로 이루어진 제일 길면서, 최소 3번이상 등장하는 문자열을 반환한다.
        // 존재하지 않는다면, -1 반환한다.

        // n개로 이루어진 단일 문자열 -> n-1개로 이루어진 단일 문자열 2개를 만들 수 있다.
        
        // 1. 순회하며, 단일 문자열로 이루어진 문자열들의 개수를 구한다.
        int[][] counts = new int['z' - 'a' + 1][s.length() + 1];
        int start = 0;
        while (start < s.length()) {
            int end = start + 1;
            while (end < s.length() && s.charAt(start) == s.charAt(end)) {
                end++;
            }

            counts[s.charAt(start) - 'a'][end - start]++;
            start = end;
        }

        // 2. n개로 이루어진 문자열당 n-1개로 이루어진 문자열 2개를 추가한다.
        // 0개는 카운트하지 않는다.
        for (int i = 0; i < counts.length; i++) {
            for (int j = counts[i].length - 1; j > 1; j--) {
                counts[i][j - 1] += counts[i][j] << 1;
            }
        }

        // 3. 최소 3번이상 등장하는 제일 긴 문자열 길이를 반환한다.
        int max = -1;
        for (int i = 0; i < counts.length; i++) {
            for (int j = counts[i].length - 1; j > max; j--) {
                if (counts[i][j] >= 3) {
                    max = j;
                }
            }
        }
        return max;
    }
}
