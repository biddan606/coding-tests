class Solution {
    public int maxCount(int[] banned, int n, int maxSum) {
        boolean[] isBanned = new boolean[n + 1];
        for (int i = 0; i < banned.length; i++) {
            int bannedNumber = banned[i];
            if (bannedNumber <= n) {
                isBanned[bannedNumber] = true;
            }
        }

        int count = 0;

        for (int number = 1; number <= n; number++) {    
            if (number > maxSum) {
                break;
            }
            
            if (isBanned[number]) {
                continue;
            }

            count++;
            maxSum -= number;
        }
        return count;
    }
}
