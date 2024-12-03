class Solution {
    public String addSpaces(String s, int[] spaces) {
        int beginIndex = 0;
        List<String> words = new ArrayList<>();

        for (int endIndex : spaces) {
            words.add(s.substring(beginIndex, endIndex));
            beginIndex = endIndex;
        }
        words.add(s.substring(beginIndex, s.length()));

        return String.join(" ", words);
    }
}
