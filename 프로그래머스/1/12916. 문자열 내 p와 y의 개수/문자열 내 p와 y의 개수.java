class Solution {
    boolean solution(String s) {
        String lowerS = s.toLowerCase();
 
        String removedP = lowerS.replace("p", "");
        String removedY = lowerS.replace("y", "");
        
        return removedP.length() == removedY.length();
    }
}