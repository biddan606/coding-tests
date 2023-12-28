import java.util.*;

class Solution {
    private static char[] VOWELS = {'A', 'E', 'I', 'O', 'U'};
    
    private int resultCount = -1;
    
    public int solution(String word) {
        int answer = findDictionaryOrder(word, "", 0);
        
        return resultCount;
    }
    
    private int findDictionaryOrder(String destinationWord, String startWord, int startCount) {    
        if (startWord.length() == 5) {
            return startCount;
        }
        
        int count = startCount;
        for (char vowel : VOWELS) {
            String nextWord = startWord + vowel;
            int nextCount =  count + 1;
            
            if (Objects.equals(destinationWord, nextWord)) {
                resultCount = nextCount;
            }
            
            count = findDictionaryOrder(destinationWord, nextWord, nextCount);
            if (resultCount != -1) {
                return -1;
            }
        }
        
        return count;
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