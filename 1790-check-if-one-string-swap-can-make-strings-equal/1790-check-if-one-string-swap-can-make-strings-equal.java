class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        int size = s1.length();
        int differences = 0;

        for (int i = 0; i < size; i++) {
            if (s1.	charAt(i) != s2.charAt(i)) {
                differences++;
            }
        }
        if (differences > 2) {
            return false;
        }

        int alphabetCount = 'z' - 'a' + 1;

        int[] alphabetFrequencyOfS1 = new int[alphabetCount];
        for (char c : s1.toCharArray()) {
            alphabetFrequencyOfS1[c - 'a']++;
        }
        
        int[] alphabetFrequencyOfS2 = new int[alphabetCount];
        for (char c : s2.toCharArray()) {
            alphabetFrequencyOfS2[c - 'a']++;
        }

        for (int i = 0; i < alphabetCount; i++) {
            if (alphabetFrequencyOfS1[i] != alphabetFrequencyOfS2[i]) {
                return false;
            }
        }
        return true;
    }
}
