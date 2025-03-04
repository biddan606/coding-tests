class Solution {
    /*
    # 문제 이해
    n이 3의 수의 제곱으로 표현될 수 있다면 true, 없다면 false를 반환한다

    # 접근
    n = 3^0 + 3^2 + 3^3으로 표현된다면
    n = 1 + 3(3^1 + 3^2)로 표현될 수 있다

    이 식을 이용하면 되지 않을까?

    n - 1 = 3(3^1 + 3^2)
        = 3(3(1 + 3^1))
        = 3(3(1 + 3(1)))

    # 구현 스텝
    1. 3으로 나눠지지 않으면 -1을 하고 3으로 나눈다, 이럼에도 나눠지지 않는다면 false
    2. 더이상 나눌 수 없을 때까지 반복한다
    3. 나눌 수 없는 값이 1이라면 true, 아니라면 false
    */
    public boolean checkPowersOfThree(int n) {
        int current = n;

        while (current > 1) {
            if (current % 3 != 0) {
                current--;
            }

            if (current % 3 != 0) {
                return false;
            }

            current /= 3;
        }

        return true;
    }
}
