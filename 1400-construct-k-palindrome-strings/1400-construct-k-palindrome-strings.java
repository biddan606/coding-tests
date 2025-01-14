class Solution {
    public boolean canConstruct(String s, int k) {
        boolean[] odded = new boolean['z' - 'a' + 1];
        for (char ch : s.toCharArray()) {
            odded[ch - 'a'] = !odded[ch - 'a'];
        }

        int min = 0;
        for (int i = 0; i < odded.length; i++) {
            if (odded[i]) {
                min++;
            }
        }
        min = Math.max(1, min);

        if (min <= k && k <= s.length()) {
            return true;
        }
        return false;
    }
}
