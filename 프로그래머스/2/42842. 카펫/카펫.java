class Solution {
    public int[] solution(int brown, int yellow) {
        int resultHeight = -1;
        int resultWidth = -1;
        
        for (int height = 0; height < 2500; height++) {
            for (int width = height; width < 2500; width++) {
                if (2 * (height + width) - 4 == brown && 
                   (height - 2) * (width - 2) == yellow) {
                    resultHeight = height;
                    resultWidth = width;
                }
            }
        }
        
        return new int[]{resultWidth, resultHeight};
    }
}