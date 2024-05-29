class Solution {
    public int numSteps(String s) {
        int stepCount = 0;
        boolean carry = false;

        for (int i = s.length() - 1; i > 0; i--) {
            char current = s.charAt(i);

            /**
             * 100 -> 10 -> 1
             * 101 -> 110 -> 11 -> 20, 100 -> 10 -> 1
             * 110 -> 11 -> 20, 100 -> 10 -> 1
             * 111 -> 120, 200, 1100 -> 110 -> 11 -> 20, 100 -> 10 -> 1
             */
            // 현재 값이 1인 경우, stepCount를 1개 더 증가시켜야 한다. 1 -> 10 -> 1로 2스텝을 진행하기 때문에
            if ((carry && current == '0') || (!carry && current == '1')) {
                stepCount++;
            }

            /**
             * carry = false, current = '1'인 경우, 홀수이므로 carry한다.
             * carry = false, current = '0'인 경우, 짝수이므로 2로 나누므로 아무것도 하지 않는다.
             * carry = true, current = '1'인 경우, 2가 되므로 carry한다.(2 -> 10이므로)
             * carry = true, current = '0'인 경우, 홀수이므로 carry한다.
             */
            if (carry || current == '1') {
                carry = true;
            }
            
            stepCount++;
        }
        // 마지막 값은 항상 1일 것이다. 1이고 carry가 되어 있다면, 2 -> 10 이므로 step을 한번 더 진행한다.
        if (carry) {
            stepCount++;
        }

        return stepCount;
    }
}
