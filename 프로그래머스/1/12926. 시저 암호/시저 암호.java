class Solution {

    private static final int ALPHABET_COUNT = 26;

    public String solution(String source, int distance) {
        StringBuilder answer = new StringBuilder();

        for (char c : source.toCharArray()) {
            if (!Character.isAlphabetic(c)) {
                answer.append(c);
                continue;
            }

            char absoluteValue = 'A';
            if (Character.isLowerCase(c)) {
                absoluteValue = 'a';
            }

            int offset = c - absoluteValue;

            answer.append((char) (absoluteValue + ((offset + distance) % ALPHABET_COUNT)));
        }

        return answer.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        String result = solution.solution("a B z", 4);

        System.out.println(result);
    }
}
