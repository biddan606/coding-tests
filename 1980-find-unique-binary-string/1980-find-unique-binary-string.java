class Solution {
    /*
    문제
    길이가 n인 문자열 nums가 있다
    nums는 바이너리이다
    nums의 문자열에 존재하지 않는 바이너리를 반환해야 한다

    접근
    - n의 최대 길이는 16이다
    - 사용된 바이너리 문자열를 체크할 수 있으면 좋을 것 같다

    구현 스텝
    1. 바이너리 문자열를 체크할 boolean[]을 만든다
        - 바이너리 문자 -> 데시말로 변환해서 체크할 예정
    2. num를 순회하며 사용된 바이너리 문자열을 체크한다
        - 데시말로 변환 후 체크
    3. 사용되지 않은 데시말을 찾는다
    4. 데시말을 바이너리로 변환하여 반환한다
    */
    public String findDifferentBinaryString(String[] nums) {
        int size = nums.length;
        int binaryLength = nums[0].length();

        boolean[] used = new boolean[(int)Math.pow(2, size)];

        for (String binaryString : nums) {
            int decimal = convertBinaryToDecimal(binaryString);
            used[decimal] = true;
        }

        for (int i = 0; i < used.length; i++) {
            if (!used[i]) {
                return convertDecimalToBinaryString(i, binaryLength);
            }
        }

        return null;
    }

    private int convertBinaryToDecimal(String binaryString) {
        int decimal = 0;

        for (char digit : binaryString.toCharArray()) {
            decimal = decimal * 2 + (digit - '0');
        }

        return decimal;
    }

    private String convertDecimalToBinaryString(int decimal, int size) {
        StringBuilder binaryString = new StringBuilder();

        while (decimal > 0) {
            binaryString.append((char) (decimal % 2 + '0'));
            decimal /= 2;
        }

        return binaryString.append("0".repeat(size - binaryString.length()))
            .reverse().toString();
    }
}
