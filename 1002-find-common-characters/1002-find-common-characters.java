class Solution {
    public List<String> commonChars(String[] words) {
        // 각 문자의 최소 등장 횟수를 저장할 배열을 초기화합니다. ('a' ~ 'z')
        int[] charCountMap = new int[26];
        Arrays.fill(charCountMap, Integer.MAX_VALUE);

        // 각 단어를 순회하면서 문자 등장 횟수를 계산합니다.
        for (String word : words) {
            int[] tempCount = new int[26];
            
            // 단어 내 각 문자의 등장 횟수를 계산합니다.
            for (char c : word.toCharArray()) {
                tempCount[c - 'a']++;
            }

            // 최소 등장 횟수를 업데이트합니다.
            for (int i = 0; i < 26; i++) {
                charCountMap[i] = Math.min(charCountMap[i], tempCount[i]);
            }
        }

        // 각 문자의 최소 등장 횟수만큼 결과 리스트에 추가합니다.
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < charCountMap[i]; j++) {
                result.add(Character.toString('a' + i));
            }
        }

        return result;
    }
}
