class Solution {
    /**
     * 1. 문자 등장 횟수 맵을 생성한다.
        - 초기값은 (단어의 최대 개수 * 단어의 최대 길이)이다.
     * 2. 단어들을 순회하며, 단어별 문자 등장 횟수를 구한다.
     * 3. 단어의 문자 횟수로 맵의 카운트를 최소값으로 수정한다.
     */
    public List<String> commonChars(String[] words) {
        Map<Character, Integer> charCountMap = new HashMap<>();

        for (char c = 'a'; c <= 'z'; c++) {
            charCountMap.put(c, 100 * 100);
        }

        for (String word : words) {
            Map<Character, Integer> map = new HashMap<>();

            for (char c : word.toCharArray()) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }

            for (Map.Entry<Character, Integer> e : charCountMap.entrySet()) {
                char key = e.getKey();
                int originalValue = e.getValue();
                
                int wordCharValue = map.getOrDefault(key, 0);

                charCountMap.put(key, Math.min(originalValue, wordCharValue));
            }
        }

        List<String> result = new ArrayList<>();
        for (Map.Entry<Character, Integer> e : charCountMap.entrySet()) {
            char ch = e.getKey();
            int count = e.getValue();

            for (int i = 0; i < count; i++) {
                result.add(Character.toString(ch));
            }
        }
    
        return result;
    }
}
