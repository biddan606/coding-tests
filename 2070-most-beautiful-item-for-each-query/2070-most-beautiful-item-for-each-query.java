class Solution {
    public int[] maximumBeauty(int[][] items, int[] queries) {
        int[] maxBeauties = new int[100_000 + 1];
        
        for (int[] item : items) {
            int price = item[0];
            int beauty = item[1];

            maxBeauties[price] = Math.max(maxBeauties[price], beauty);
        }

        int currentMax = 0;
        
        for (int i = 0; i < maxBeauties.length; i++) {
            currentMax = Math.max(currentMax, maxBeauties[i]);
            maxBeauties[i] = currentMax;
        }

        int[] result = new int[queries.length];
        
        for (int i = 0; i < result.length; i++) {
            result[i] = maxBeauties[queries[i]];
        }
        return result;
    }
}
