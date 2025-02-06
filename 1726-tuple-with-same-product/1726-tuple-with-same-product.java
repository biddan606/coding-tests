class Solution {
    public int tupleSameProduct(int[] nums) {
        Map<Integer, Integer> countsByProduct = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int product = nums[i] * nums[j];
                countsByProduct.put(product, countsByProduct.getOrDefault(product, 0) + 1);
            }
        }

        int result = 0;
        for (int count : countsByProduct.values()) {
            result += count * (count - 1) * 4;
        }

        return result;
    }
}
