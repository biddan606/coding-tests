class Solution {
    /*
    아이디 규칙에 맞지 않는 아이디를 입력했을 때, 규칙에 맞는 유사한 아이디를 추천해야 한다.
    */
    public String solution(String new_id) {
        String changedId1 = new_id.toLowerCase();
        
        String changedId2 = changedId1.replaceAll("[^a-z0-9\\-_.]", "");
        
        String changedId3 = changedId2.replaceAll("\\.+", ".");
        
        String changedId4 = changedId3.replaceAll("^\\.|\\.$", "");
        
        String changedId5 = changedId4;
        if (changedId5.isEmpty()) {
            changedId5 = "a";
        }
        
        String changedId6 = changedId5;
        if (changedId6.length() >= 16) {
            changedId6 = changedId6.substring(0, 15);
            changedId6 = changedId6.replaceAll("\\.$", "");
        }
        
        String changedId7 = changedId6;
        while (changedId7.length() <= 2) {
            changedId7 += changedId7.charAt(changedId7.length() - 1);
        }
        
        return changedId7;
    }
}