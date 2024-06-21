class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int size = customers.length;
        int customerCount = 0;

        // 항상 가능한 고객들을 센다.
        for (int t = 0; t < size; t++) {
            if (grumpy[t] == 0) {
                customerCount += customers[t];
            }
        }

        // 초기 영역을 구한다.
        for (int t = 0; t < minutes; t++) {
            if (grumpy[t] == 1) {
                customerCount += customers[t];
            }
        }

        // 구간별 가능한 최대 고객 수를 구한다.
        int maxCustomerCount = customerCount;
        for (int t = 0; t + minutes < size; t++) {
            if (grumpy[t] == 1) {
                customerCount -= customers[t];
            }
            if (grumpy[t + minutes] == 1) {
                customerCount += customers[t + minutes];
            }

            maxCustomerCount = Math.max(maxCustomerCount, customerCount);
        }

        return maxCustomerCount;
    }
}
