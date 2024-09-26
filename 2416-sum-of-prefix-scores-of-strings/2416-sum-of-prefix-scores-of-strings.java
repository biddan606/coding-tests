import java.util.Arrays;

class Solution {

    public int[] sumPrefixScores(String[] words) {
        CountingNode root = new CountingNode();
        for (String word : words) {
            insertWord(word, root);
        }

        int[] result = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            result[i] = calculateWordScore(words[i], root);
        }

        return result;
    }

    private void insertWord(String word, CountingNode root) {
        CountingNode current = root;
        for (char ch : word.toCharArray()) {
            current = current.getChild(ch);
            current.increaseCount();
        }
    }

    private int calculateWordScore(String word, CountingNode root) {
        CountingNode current = root;
        int score = 0;

        for (char ch : word.toCharArray()) {
            current = current.getChild(ch);
            score += current.getCount();
        }

        return score;
    }

    private class CountingNode {

        private final static int ALPHABET_SIZE = 'z' - 'a' + 1;

        private final CountingNode[] children = new CountingNode[ALPHABET_SIZE];
        private int count = 0;

        public CountingNode getChild(char ch) {
            int index = this.getIndex(ch);
            if (children[index] == null) {
                children[index] = new CountingNode();
            }

            return children[index];
        }

        public void increaseCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        private int getIndex(char ch) {
            return ch - 'a';
        }
    }

    public static void main(String[] args) {
        String[] words = {"abc", "ab", "bc", "b"};
        Solution solution = new Solution();

        int[] result = solution.sumPrefixScores(words);

        System.out.println(Arrays.toString(result));
    }
}
