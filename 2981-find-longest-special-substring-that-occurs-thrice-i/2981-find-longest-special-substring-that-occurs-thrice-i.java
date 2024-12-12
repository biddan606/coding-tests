class Solution {
    public int maximumLength(String str) {
        Map<String, Integer> counts = new HashMap<>();
        for (int s = 0; s < str.length(); s++) {
            for (int e = s; e < str.length(); e++) {
                if (str.charAt(s) != str.charAt(e)) {
                    break;
                }

                String substr = str.substring(s, e + 1);
                counts.put(substr, counts.getOrDefault(substr, 0) + 1);
            }
        }

        int result = -1;
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (entry.getValue() >= 3 && result < entry.getKey().length()) {
                result = entry.getKey().length();
            }
        }
        return result;
    }
}
