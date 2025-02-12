class Solution {
    /*
    # 문제
    s 안에 part를 순차적으로 지워야 한다
    -> part가 없는 문자열을 반환해야 한다

    # 직관
    - s의 길이와 part의 길이가 최대 1000이므로, O(n^2)까지 가능
    - 문자열들이 소문자로만 이루어져 있으므로 변환 필요 없음
    - s 문자열을 제거하면 새로운 문자열이 만들어지므로 해당 자리부터 다시 검증해야 한다

    # 풀이
    1. s 안에 part가 있는지 찾는다
        1-1 있다면 지우고 다시 s를 검사
        1-2 없다면 반환
    */
    public String removeOccurrences(String s, String part) {
        String result = s;
        boolean deleted = true;

        while (deleted) {
           deleted = false;

           for (int resultIndex = 0; resultIndex < result.length(); resultIndex++) {
                int partIndex = 0;

                while (partIndex < part.length()
                        && resultIndex + partIndex < result.length()
                        && part.charAt(partIndex) == result.charAt(resultIndex + partIndex)) {
                    partIndex++;
                }

                if (partIndex == part.length()) {
                    result = result.substring(0, resultIndex) 
                        + result.substring(resultIndex + partIndex);
                    deleted = true;
                    break;
                }
           }
        }

        return result;
    }
}
