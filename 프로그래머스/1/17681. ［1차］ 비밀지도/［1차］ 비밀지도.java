class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] result = new String[n];

        for (int i = 0; i < n; i++) {
            // 1. 두 지도를 비트 연산자(OR)로 한 번에 합칩니다.
            int combined = arr1[i] | arr2[i];

            // 2. 이진수 문자열로 변환합니다.
            String binaryString = Integer.toBinaryString(combined);

            // 3. 자릿수(n)를 맞추기 위해 앞쪽을 공백으로 채우고, 
            //    1은 '#', 0은 ' '으로 변환합니다.
            result[i] = String.format("%" + n + "s", binaryString)
                               .replace('1', '#')
                               .replace('0', ' ');
        }

        return result;
    }
}