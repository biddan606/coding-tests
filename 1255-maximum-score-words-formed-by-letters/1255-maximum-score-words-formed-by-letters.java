class Solution {
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        Map<Character, Integer> validChars = new HashMap<>();
        for (char c : letters) {
            validChars.put(c, validChars.getOrDefault(c, 0) + 1);
        }

        return backtracking(words, 0, validChars, score);
    }

    private int backtracking(String[] words, int index, Map<Character, Integer> validChars, int[] score) {
        if (index == words.length) {
            return 0;
        }

        int result1 = backtracking(words, index + 1, validChars, score);
        
        int result2 = 0;
        if (canMake(words[index], validChars)) {
            result2 += combine(words[index], validChars, score);
            result2 += backtracking(words, index + 1, validChars, score);
            separate(words[index], validChars);
        }

        return Math.max(result1, result2);
    }

    private boolean canMake(String str, Map<Character, Integer> chars) {
        Map<Character, Integer> strToChars = new HashMap<>();
        for (char c : str.toCharArray()) {
            strToChars.put(c, strToChars.getOrDefault(c, 0) + 1);
        }

        for (Map.Entry<Character, Integer> e : strToChars.entrySet()) {
            if (chars.getOrDefault(e.getKey(), 0) < e.getValue()) {
                return false;
            }
        }
        return true;
    }

    private int combine(String str, Map<Character, Integer> chars, int[] score) {
        int result = 0;
        
        for (char c : str.toCharArray()) {
            chars.put(c, chars.get(c) - 1);
            result += score[c - 'a'];
        }
        
        return result;
    }

    private void separate(String str, Map<Character, Integer> chars) {
        for (char c : str.toCharArray()) {
            chars.put(c, chars.get(c) + 1);
        }
    }
}
