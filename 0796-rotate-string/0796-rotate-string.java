class Solution {
    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        
        return goal.repeat(2)
            .contains(s);
    }
}
