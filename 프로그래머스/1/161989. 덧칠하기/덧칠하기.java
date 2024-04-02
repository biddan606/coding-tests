class Solution {
    public int solution(int n, int m, int[] section) {
        int currentIndex = 0;
        int count = 0;
        
        while (currentIndex < section.length) {
            int nextIndex = paint(section, currentIndex, m);
            
            currentIndex = nextIndex;
            count++;
        }
        
        return count;
    }
    
    private int paint(int[] section, int index, int range) {
        int nextIndex = index;
        int paintedEnd = section[index] + range - 1;
        
        while (nextIndex < section.length && section[nextIndex] <= paintedEnd) {
            nextIndex++;
        }
        
        return nextIndex;
    }
}