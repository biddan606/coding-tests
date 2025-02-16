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
        String sourceString = String.valueOf(source);
        return matchBacktracking(sourceString, 0, 0, target);
    }

    private static boolean matchBacktracking(String source, int startIndex, int sum, int target) {
        if (startIndex == source.length()) {
            return sum == target;
        }

        for (int chunk = 1; startIndex + chunk <= source.length(); chunk++) {
            int chunkedNumber = Integer.parseInt(source.substring(startIndex, startIndex + chunk));

            if (matchBacktracking(source, startIndex + chunk, sum + chunkedNumber, target)) {
                return true;
            }
        }

        return false;
    }
}
