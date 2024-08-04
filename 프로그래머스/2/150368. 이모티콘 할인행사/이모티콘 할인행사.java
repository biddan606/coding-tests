import java.util.Arrays;

class Solution {

    private static final int[] DISCOUNT_RATE = {10, 20, 30, 40};
    private int[] result = {0, 0};

    public int[] solution(int[][] users, int[] emoticons) {
        findOptimalDiscountStrategy(users, new int[users.length], emoticons, 0);
        return result;
    }

    private void findOptimalDiscountStrategy(int[][] users, int[] usersPurchaseAmount, int[] emoticons, int emoticonIndex) {
        if (emoticons.length == emoticonIndex) {
            int[] current = calculateResult(users, usersPurchaseAmount);
            if (result[0] < current[0] ||
                    (result[0] == current[0]) && result[1] < current[1]) {
                result = Arrays.copyOf(current, current.length);
            }
            return;
        }

        for (int currentDiscountRate : DISCOUNT_RATE) {
            int emoticonAmount = (int) (emoticons[emoticonIndex] - emoticons[emoticonIndex] * (currentDiscountRate / 100.0));

            for (int i = 0; i < users.length; i++) {
                if (users[i][0] > currentDiscountRate) {
                    continue;
                }

                usersPurchaseAmount[i] += emoticonAmount;
            }

            findOptimalDiscountStrategy(users, usersPurchaseAmount, emoticons, emoticonIndex + 1);

            for (int i = 0; i < users.length; i++) {
                if (users[i][0] > currentDiscountRate) {
                    continue;
                }

                usersPurchaseAmount[i] -= emoticonAmount;
            }
        }
    }

    private int[] calculateResult(int[][] users, int[] usersPurchaseAmount) {
        int[] result = new int[2];
        
        for (int i = 0; i < users.length; i++) {
            if (users[i][1] <= usersPurchaseAmount[i]) {
                result[0]++;
            } else {
                result[1] += usersPurchaseAmount[i];
            } 
        }

        return result;
    }
}
