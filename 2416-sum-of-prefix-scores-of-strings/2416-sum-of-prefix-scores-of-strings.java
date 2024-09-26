import java.util.Arrays;

class Solution {
    public int[] sumPrefixScores(String[] words) {
        CountingNode root = new CountingNode();
        for (String word : words) {
            addCount(word, root);
        }

        int[] result = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            result[i] = getCount(words[i], root);
        }

        return result;
    }

    private void addCount(String word, CountingNode root) {
        CountingNode current = root;
        for (char ch : word.toCharArray()) {
            current = current.getNext(ch);
            current.increaseCount();
        }
    }

    private int getCount(String word, CountingNode root) {
        CountingNode current = root;
        int sum = 0;

        for (char ch : word.toCharArray()) {
            current = current.getNext(ch);
            sum += current.getCount();
        }

        return sum;
    }

    private class CountingNode {
        private final CountingNode[] next = new CountingNode['z' - 'a' + 1];
        private int count = 0;

        public CountingNode getNext(char ch) {
            int index = this.getIndex(ch);
            if (next[index] == null) {
                next[index] = new CountingNode();
            }

            return next[index];
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
