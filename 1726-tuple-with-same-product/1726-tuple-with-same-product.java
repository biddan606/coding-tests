class Solution {
    public int tupleSameProduct(int[] nums) {
        Map<Integer, List<Pair<Integer, Integer>>> byProduct = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int product = nums[i] * nums[j];
                byProduct.computeIfAbsent(product, k -> new ArrayList<>())
                    .add(new Pair(i, j));
            }
        }

        int result = 0;
        for (int key : byProduct.keySet()) {
            int valuesSize = byProduct.get(key).size();
            result += valuesSize * (valuesSize - 1) * 4;
        }

        return result;
    }
}
