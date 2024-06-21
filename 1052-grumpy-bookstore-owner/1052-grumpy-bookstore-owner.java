class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int size = customers.length;
        int customerCount = 0;

        // 항상 가능한 고객들을 센다.
        for (int t = 0; t < size; t++) {
            customerCount += (1 - grumpy[t]) * customers[t];
        }

        // 초기 영역을 구한다.
        for (int t = 0; t < minutes; t++) {
            customerCount += grumpy[t] * customers[t];
        }

        // 구간별 가능한 최대 고객 수를 구한다.
        int maxCustomerCount = customerCount;
        for (int t = 0; t + minutes < size; t++) {
            customerCount -= grumpy[t] * customers[t];
            customerCount += grumpy[t + minutes] * customers[t + minutes];

            maxCustomerCount = Math.max(maxCustomerCount, customerCount);
        }

        return maxCustomerCount;
    }
}
