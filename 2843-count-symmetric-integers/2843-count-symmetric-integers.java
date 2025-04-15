class Solution {
    /*
    # 문제 이해
    low <= x <= high, x의 대칭 수의 개수를 반환해야 한다

    * 대칭 수:
        대칭 수는 0~n, n+1~2*n 자릿수까지의 합이 일치하는 것을 의미한다
        예) 1203 -> 12 // 03 -> 3 3 대칭수!
            1200 -> 12 // 00 -> 3 0 대칭수 아님

    # 접근
    최대 범위가 low = 1, high = 10^4일 때이다. 최대 10^4 범위가 될 수 있다
    모든 경우의 수를 살펴보아도 10^4밖에 되지 않으므로 가능하다

    # 구현 스텝
    1. low -> high까지 순회한다
    2. x를 자릿수를 구한다 만약 자릿수가 홀수라면 대칭수가 절대 아니다
    3. x의 자릿수가 짝수라면 대칭수를 체크한다
        - 대칭수 체크: x를 문자열로 바꾼다
            x의 2/size까지 각 자릿수를 더한다
            2/size -> size까지 각 자릿수를 뺸다
            result=0이라면 대칭수이다
    4. 대칭수라면 count++
    5. 총 count를 반환한다
    */
    public int countSymmetricIntegers(int low, int high) {
        int totalCount = 0;

        for (int x = low; x <= high; x++) {
            if (isSymmetricNumber(x)) {
                totalCount++;
            }
        }

        return totalCount;
    }

    private static boolean isSymmetricNumber(int number) {
        String numberString = String.valueOf(number);
        if (numberString.length() % 2 == 1) {
            return false;
        }

        int result = 0;
        int halfSize = numberString.length() / 2;
        for (int i = 0; i < halfSize; i++) {
            result += numberString.charAt(i) - '0';
        }

        for (int i = halfSize; i < numberString.length(); i++) {
            result -= numberString.charAt(i) - '0';
        }

        return result == 0;
    }
}
