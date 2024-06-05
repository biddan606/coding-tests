class Solution {
    public int longestPalindrome(String s) {
        // 문자 개수를 센다.
        Map<Character, Integer> charCountMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }

        // 짝수들은 팰린드롬을 만들 수 있음
        // 홀수는 짝수로 변환 후 팰린드롬을 만들 수 있음
        int palindromeSize = 0;
        boolean hasOdd = false;

        for (int count : charCountMap.values()) {
            if (count % 2 == 1) {
                hasOdd = true;
                count--;
            }
            palindromeSize += count;
        }

        // 한가운데에 1개의 문자가 들어갈 수 있음
        if (hasOdd) {
            palindromeSize++;
        }

        return palindromeSize;
    }
}
