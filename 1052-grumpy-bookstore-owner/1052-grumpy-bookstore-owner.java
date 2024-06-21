class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        // 가장 많은 심술 고객이 방문하는 구간을 구합니다.
        // 해당 구간은 무적시간이라고 부릅니다.
        int initialGrumpyCustomers = 0;
        for (int t = 0; t < minutes; t++) {
            if (grumpy[t] == 1) {
                initialGrumpyCustomers += customers[t];
            }
        }

        int currentGrumpyCustomers = initialGrumpyCustomers;
        int startTimeOfMaxGrumpy = 0;
        int maxGrumpyCustomers = initialGrumpyCustomers;

        for (int t = minutes; t < customers.length; t++) {
            if (grumpy[t - minutes] == 1) {
                currentGrumpyCustomers -= customers[t - minutes];
            }
            if (grumpy[t] == 1) {
                currentGrumpyCustomers += customers[t];
            }

            if (currentGrumpyCustomers > maxGrumpyCustomers) {
                startTimeOfMaxGrumpy = t - minutes + 1;
                maxGrumpyCustomers = currentGrumpyCustomers;
            }
        }

        // 무적시간이거나 심술을 부리지 않는 시간에 고객을 셉니다.
        int countedCustomers = 0;
        for (int t = 0; t < customers.length; t++) {
            if (grumpy[t] == 0 || 
            (startTimeOfMaxGrumpy <= t && t < startTimeOfMaxGrumpy + minutes)) {
                countedCustomers += customers[t];
            }
        }

        // 카운팅한 고객을 반환합니다.
        return countedCustomers;
    }
}
