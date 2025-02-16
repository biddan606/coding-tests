class Solution {
    public int punishmentNumber(int n) {
        int result = 0;

        for (int c = 1; c <= n; c++) {
            int square = c * c;
            
            if (canConvertToTarget(square, c)) {
                result += square;
            }
        }

        return result;
    }

    private static boolean canConvertToTarget(int source, int target) {
        return matchBacktracking(source, target);
    }

    private static boolean matchBacktracking(int source, int target) {
        if (target == 0 && source == 0) {
            return true;
        }
        
        int splited = 0;

        for (int e = 0; source > 0; e++) {
            splited = (source % 10) * (int) Math.pow(10, e) + splited;
            source /= 10;
            int nextTarget = target - splited;
            
            if (nextTarget < 0) {
                return false;
            } else if (matchBacktracking(source, nextTarget)) {
                return true;
            }
        }

        return false;
    }
}
