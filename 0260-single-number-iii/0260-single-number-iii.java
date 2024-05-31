class Solution {
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }

        // 2의 보수로 and 연산 시 가작 작은 비트만 남음
        int lowestBit = xor & -xor;
        
        // 가장 작은 비트 num1과 아닌 num2를 분리하여 얻음
        int[] result = new int[2];
        for (int num : nums) {
            if ((lowestBit & num) == 0) {
                result[0] ^= num; 
            } else {
                result[1] ^= num; 
            }
        }

        return result;
    }
}
