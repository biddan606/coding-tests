class Solution {

    public int solution(String source) {
        int answer = Integer.MAX_VALUE;

        for (int countToParse = 1; countToParse <= source.length(); countToParse++) {
            int currentAnswer = parse(source, countToParse);

            answer = Math.min(currentAnswer, answer);
        }

        return answer;
    }

    private int parse(String source, int countToCompress) {
        StringBuilder compressedString = new StringBuilder();

        int index = 0;
        while (index + countToCompress <= source.length()) {
            int count = 1;
            int indexToCompare = index + countToCompress;
            while (indexToCompare + countToCompress <= source.length() &&
                    source.substring(index, index + countToCompress)
                    .equals(source.substring(indexToCompare, indexToCompare + countToCompress))) {
                count++;
                index += countToCompress;
                indexToCompare += countToCompress;
            }

            if (count != 1) {
                compressedString.append(count);
            }

            compressedString.append(source, index, index + countToCompress);
            index += countToCompress;
        }

        return compressedString.length() + source.length() - index;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int result = solution.solution("xababcdcdababcdcd");

        System.out.println(result);
    }
}
