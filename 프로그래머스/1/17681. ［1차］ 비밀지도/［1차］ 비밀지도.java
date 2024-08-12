import java.util.Arrays;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        // int[] -> 지도로 변환한다.
        char[][] map1 = convertToMap(arr1);
        char[][] map2 = convertToMap(arr2);

        // 두 지도를 합친다.
        char[][] combined = combine(map1, map2);

        // 합친 지도를 String[]로 변환한다.
        return Arrays.stream(combined)
                .map(String::new)
                .toArray(String[]::new);
    }

    private char[][] convertToMap(int[] arr1) {
        int size = arr1.length;
        char[][] result = new char[size][size];

        for (int row = 0; row < size; row++) {
            int value = arr1[row];

            for (int col = size - 1; col >= 0; col--) {
                result[row][col] = value % 2 == 1 ? '#' : ' ';
                value /= 2;
            }
        }

        return result;
    }

    private char[][] combine(char[][] map1, char[][] map2) {
        int size = map1.length;
        char[][] result = new char[size][size];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (map1[row][col] == '#' || map2[row][col] == '#') {
                    result[row][col] = '#';
                } else {
                    result[row][col] = ' ';
                }
            }
        }

        return result;
    }
}
