class Solution {
    private List<List<String>> palindromePartitions;
    private boolean[][] palindromeMemory;

    public List<List<String>> partition(String s) {
        palindromePartitions = new ArrayList<>();
        
        palindromeMemory = new boolean[s.length()][s.length()];
        // 1, 2글자 palindrome 처리
        for (int i = 0; i < s.length(); i++) {
            palindromeMemory[i][i] = true;
            if (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
                palindromeMemory[i][i + 1] = true;
            }
        }

        // 3글자 이상 palindrome 처리
        for (int len = 3; len <= s.length(); len++) {
            for (int start = 0; start <= s.length() - len; start++) {
                int end = start + len - 1;
                if (s.charAt(start) == s.charAt(end) && palindromeMemory[start + 1][end - 1]) {
                    palindromeMemory[start][end] = true;
                }
            }
        }

        findPartitions(s, 0, new ArrayList<>());
        return palindromePartitions;
    }

    private void findPartitions(String str, int begin, List<String> currentPartition) {
        if (str.length() == begin) {
            palindromePartitions.add(new ArrayList<>(currentPartition));
            return;
        }

        // palindromeMemory 기준으로 for문 순회
        for (int end = begin; end < str.length(); end++) {
            if (!palindromeMemory[begin][end]) {
                continue;
            }

            currentPartition.add(str.substring(begin, end + 1));
            findPartitions(str, end + 1, currentPartition);
            currentPartition.remove(currentPartition.size() - 1);
        }
    }
}
