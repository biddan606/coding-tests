class Solution {
    public int[] solution(int brown, int yellow) {    
        for (int height = 3; height < 2500; height++) {
            for (int width = height; width < 2500; width++) {
                int currentBrown = 2 * (height + width) - 4;
                int currentYellow = (height - 2) * (width - 2);
                
                if (currentBrown == brown && currentYellow == yellow) {
                    return new int[]{width, height};
                }
            }
        }
        
        return null;
    }
}