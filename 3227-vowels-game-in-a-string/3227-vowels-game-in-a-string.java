/*
두 명의 플레이어가 s(문자열)을 가지고 게임을 진행한다.
- 첫번째 플레이어는 모음 수가 홀수인 substring을 제거할 수 있다.
- 두번째 플레이어는 모음 수가 짝수인 substring을 제거할 수 있다.
- 첫번째 플레이어가 이기면 true, 지면 false를 반환한다.

---

모음은 'a', 'e', 'i', 'o', 'u'

---

문자열이 아닌, 모음의 개수로 좁혀볼 수 있다. 그렇더라도 모음의 최대 개수는 10^5이다. 재귀나 브루트 포스를 사용할 수 없다.

먼저, 모음의 개수가 없다면 첫번째 플레이어는 패배한다(0은 짝수).
그 다음 모음의 개수가 홀수라면 첫번째 플레이어가 모두 지울 수 있고, 짝수라면 홀수개만 지워서 홀수개를 남겨둘 수 있다.
항상 첫번째 플레이어가 이긴다.

*/
class Solution {
    public boolean doesAliceWin(String s) {
        var vowelCount = countVowels(s);
        if (vowelCount == 0) {
            return false;
        }


        return true;
    }

    private int countVowels(String str) {
        int vowelCount = 0;

        for (char c : str.toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vowelCount++;
            }
        }

        return vowelCount;
    }
}
