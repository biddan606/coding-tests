class Solution {
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);

        int low = 1;
        int high = position[position.length - 1] - position[0] + 1;
        while (low < high) {
            int mid = (low + high) / 2;

            if (install(position, mid) >= m) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low - 1;
    }
    
    private int install(int[] position, int distance) {
        int count = 1;
        int lastInstalled = position[0];
        for (int i = 1; i < position.length; i++) {
            if (distance <= position[i] - lastInstalled) {
                count++;
                lastInstalled = position[i];
            }
        }

        return count;
    }
}
