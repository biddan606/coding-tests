class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        Map<Character, Node> nodeMap = new HashMap<>();
        for (String word : dictionary) {
            add(nodeMap, word.toCharArray());
        }

        String[] derivatives = sentence.split(" ");
        List<String> result = new ArrayList<>();
        for (String derivative : derivatives) {
            String root = get(nodeMap, derivative.toCharArray());
            result.add(root);
        }

        return result.stream()
            .collect(Collectors.joining(" "));
    }

    private void add(Map<Character, Node> nodeMap, char[] chars) {
        char headKey = chars[0];
        
        if (!nodeMap.containsKey(headKey)) {
            nodeMap.put(headKey, new Node());
        }
        Node head = nodeMap.get(headKey);

        add(head, chars, 1);
    }

    private void add(Node node, char[] chars, int charsIndex) {
        if (chars.length == charsIndex) {
            node.completed = true;
            return;
        }

        char key = chars[charsIndex];
        if (!node.nodeMap.containsKey(key)) {
            node.nodeMap.put(key, new Node());
        }
        Node next = node.nodeMap.get(key);

        add(next, chars, charsIndex + 1);
    }

    private String get(Map<Character, Node> nodeMap, char[] chars) {
        char headKey = chars[0];

        if (!nodeMap.containsKey(headKey)) {
            return  new String(chars, 0, chars.length);
        }

        Node head = nodeMap.get(headKey);
        return get(head, chars, 1);
    }

    private String get(Node node, char[] chars, int charsIndex) {
        if (node.completed) {
            return new String(chars, 0, charsIndex);
        }

        if (chars.length == charsIndex
                || !node.nodeMap.containsKey(chars[charsIndex])) {
            return new String(chars, 0, chars.length);
        }

        Node next = node.nodeMap.get(chars[charsIndex]);
        return get(next, chars, charsIndex + 1);
    }

    private static class Node {
        final Map<Character, Node> nodeMap = new HashMap<>();
        boolean completed;
    }
}
