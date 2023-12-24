class Solution {

    public String solution(String source) {
        StringBuilder answer = new StringBuilder();

        int index = 0;
        for (char c : source.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                answer.append(c);
                index = 0;
                continue;
            }

            char charToAdd = Character.toLowerCase(c);
            if (index % 2 == 0) {
                charToAdd = Character.toUpperCase(c);
            }

            answer.append(charToAdd);

            index++;
        }

        return answer.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        String result = solution.solution("try hello world");

        System.out.println(result);
    }
}
