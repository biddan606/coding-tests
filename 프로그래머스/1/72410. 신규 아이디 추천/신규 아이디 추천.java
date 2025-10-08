class Solution {
    /*
    아이디 규칙에 맞지 않는 아이디를 입력했을 때, 규칙에 맞는 유사한 아이디를 추천해야 한다.
    */
    public String solution(String new_id) {
        // 1단계: 소문자 치환
        String id = new_id.toLowerCase();
        
        // 2단계: 허용되지 않는 문자 제거
        id = id.replaceAll("[^a-z0-9\\-_.]", "");
        
        // 3단계: 연속된 마침표를 하나로
        id = id.replaceAll("\\.{2,}", ".");
        
        // 4단계: 처음과 끝의 마침표 제거
        id = id.replaceAll("^\\.|\\.$", "");
        
        // 5단계: 빈 문자열이면 "a" 대입
        if (id.isEmpty()) {
            id = "a";
        }
        
        // 6단계: 16자 이상이면 15자로 자르고, 끝 마침표 제거
        if (id.length() >= 16) {
            id = id.substring(0, 15).replaceAll("\\.$", "");
        }
        
        // 7단계: 2자 이하면 마지막 문자를 길이 3이 될 때까지 반복
        if (id.length() <= 2) {
            char lastChar = id.charAt(id.length() - 1);
            id = id + String.valueOf(lastChar).repeat(3 - id.length());
        }
        
        return id;
    }
}
