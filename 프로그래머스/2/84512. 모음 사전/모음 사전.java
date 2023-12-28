import java.util.ArrayList;
import java.util.List;

class Solution {

    private static char[] VOWELS = "AEIOU".toCharArray();

    public int solution(String word) {
        List<String> words = new ArrayList<>();
        generateWords("", words);

        return words.indexOf(word) + 1;
    }

    private void generateWords(String word, List<String> words) {
        if (word.length() == 5) {
            return;
        }

        for (char vowel : VOWELS) {
            String nextWord = word + vowel;

            words.add(nextWord);
            generateWords(nextWord, words);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.solution("I"));
    }
}


/*
"A",

"AA",

"AAA",

"AAAA",

"AAAAA",
"AAAAE",
"AAAAI",
"AAAAO",
"AAAAU",

"AAAE",
...
*/
