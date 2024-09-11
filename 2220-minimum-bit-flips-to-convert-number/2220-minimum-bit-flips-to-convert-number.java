import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Solution {
    public int minBitFlips(int start, int goal) {
        List<Integer> startBinary = convertToBinary(start);
        List<Integer> goalBinary = convertToBinary(goal);

        int differenceCount = 0;
        int i = 0;
        while (i < startBinary.size() && i < goalBinary.size()) {
            if (!Objects.equals(startBinary.get(i), goalBinary.get(i))) {
                differenceCount++;
            }
            i++;
        }

        int oneCount = 0;
        while (i < startBinary.size()) {
            if (startBinary.get(i) == 1) {
                oneCount++;
            }
            i++;
        }

        while (i < goalBinary.size()) {
            if (goalBinary.get(i) == 1) {
                oneCount++;
            }
            i++;
        }
        
        return differenceCount + oneCount;
    }

    private List<Integer> convertToBinary(int number) {
        List<Integer> result = new ArrayList<>();

        while (number > 0) {
            int remainder = number % 2;
            int quotient = number / 2;

            result.add(remainder);
            number = quotient;
        }
        return result;
    }

    public static void main(String[] args) {
        new Solution().minBitFlips(10, 82);
    }
}
