class Solution {

    public String solution(String id) {
        // 소문자로 치환
        id = id.toLowerCase();

        // 알파벳 소문자, 숫자, 빼기, 밑줄, 마침표를 제외한 문자 제거
        id = id.replaceAll("[^\\w\\d-_.]", "");

        // 마침표가 2번 이상 연속된다면 하나의 마침표로 치환
        id = id.replaceAll("\\.{2,}", ".");
        
        // id의 양 끝이 마침표로 끝난다면 제거
        if (id.startsWith(".")) {
            id = id.substring(1);
        }
        if (id.endsWith(".")) {
            id = id.substring(0, id.length() - 1);
        }
        
        // 빈 문자열이라면 "a"를 대입
        if (id.isEmpty()) {
            id = "a";
        }
        
        // id 길이가 16자 이상이면, 첫 15개의 문자를 제외한 문자 모두 제거
        if (id.length() >= 16) {
            id = id.substring(0, 15);
        }
        // id의 양 끝이 마침표로 끝난다면 제거
        if (id.startsWith(".")) {
            id = id.substring(1);
        }
        if (id.endsWith(".")) {
            id = id.substring(0, id.length() - 1);
        }

        // id 길이가 2자 이하라면, 이어 붙여서 3자로 만듦
        if (id.length() <= 2) {
            StringBuilder builder = new StringBuilder(id);
            while (builder.length() < 3) {
                builder.append(id.charAt(id.length() - 1));
            }
            id = builder.toString();
        }

        return id;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        String recommendedId = solution.solution("hello");

        System.out.println(recommendedId);
    }
}
