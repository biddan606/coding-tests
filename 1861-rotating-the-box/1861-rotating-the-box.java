class Solution {
    public char[][] rotateTheBox(char[][] box) {
        int rows = box.length;
        int cols = box[0].length;

        char[][] rotatedBox = new char[cols][rows];
        for (int r = 0; r < rotatedBox.length; r++) {
            Arrays.fill(rotatedBox[r], '.');
        }

        for (int r = 0; r < rows; r++) {
            int rotatedBoxRow = cols - 1;
            int rotatedBoxCol = rows - r - 1;

            for (int c = cols - 1; c >= 0; c--) {
                if (box[r][c] == '#') {
                    rotatedBox[rotatedBoxRow][rotatedBoxCol] = '#';
                    rotatedBoxRow--;
                } else if (box[r][c] == '*') {
                    rotatedBox[c][rotatedBoxCol] = '*';
                    rotatedBoxRow = c - 1;
                }
            }
        }

        return rotatedBox;
    }
}
